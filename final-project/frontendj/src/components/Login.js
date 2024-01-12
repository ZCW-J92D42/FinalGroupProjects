// Login.js (LoginMenu component)

import React, { useState } from 'react';
import Cookies from 'js-cookie';

const Login = ({ onLogin }) => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');

    const refreshPage = () => {
        window.location.reload();
    }

    const handleLogin = async () => {
        try {
            const response = await fetch('http://localhost:8080/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ userName, password }),
            });

            if (response.ok) {
                const userData = await response.json();

                // Save user session in localStorage
                localStorage.setItem('userId', JSON.stringify(userData.id));
                localStorage.setItem('userName', JSON.stringify(userData.userName));
                localStorage.setItem('userInfo', JSON.stringify(userData));

                // Saving token into cookie
                // there is an expires field for cookie. if omitted, it's a session cookie. if not, the number is how many days it lasts
                // setting secure to false bc we're on localhost
                Cookies.set('token', userData.accessToken, {expires: 1, secure:false});

                // Notify the parent component about the login
                onLogin();


                console.log(localStorage.getItem('userInfo'));


                // Redirect or show success message
                console.log('Login successful');
                {refreshPage()}
            } else {
                console.error('Login failed');
                // Handle login failure
            }
        } catch (error) {
            console.error('Error during login:', error.message);
        }
    };

    return (
        <div className="login-menu">
            <input
                type="text"
                placeholder="Username"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
                style={{display: 'block', marginBottom: '10px'}}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                style={{display: 'block', marginBottom: '10px'}}
            />
            <button onClick={handleLogin}>Login</button>
        </div>

    );
};

export default Login;
