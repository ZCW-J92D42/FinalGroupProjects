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
                method: 'POST', // POST Method to UserController username and password must match
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

                // https://medium.com/@amavictor/storing-tokens-in-cookies-with-react-93a5a818c3a8
                // Saving token into cookie
                // there is an expires field for cookie. if omitted, it's a session cookie. if not, the number is how many days it lasts
                // setting secure to false bc we're on localhost
                Cookies.set('token', userData.accessToken, {expires: 1, secure:false});

                // Notify the parent component about the login
                onLogin();


                console.log(localStorage.getItem('userInfo'));



                console.log('Login successful');
                {refreshPage()} //Refresh page for user local Storage to set items for other components
            } else {
                console.error('Login failed');
            }
        } catch (error) {
            console.error('Error during login:', error.message);
        }
    };

    return (
        <div className ="login-container">

            <input
                type="text"
                placeholder="Username"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}

            /><br/>

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}

            />
            <br/>
            <button onClick={handleLogin}>Login</button>

        </div>

    );
};

export default Login;
