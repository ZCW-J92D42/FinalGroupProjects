import React, { useState, useEffect } from 'react';
import './Register.css';

const Register = () => {
    const [name, setName] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [registrationSuccess, setRegistrationSuccess] = useState(false);

    useEffect(() => {
        // Check user login status here
        const userLoggedIn = localStorage.getItem('userInfo') !== null;
        setIsLoggedIn(userLoggedIn);
    }, []);

    const handleRegister = async () => {
        // Check user login status again to prevent registration if the user logs in during the registration process
        const userLoggedIn = localStorage.getItem('userInfo') !== null;
        if (userLoggedIn) {
            alert('User is already logged in. Registration not allowed.');
            //
            return;
        }

        try {
            const response = await fetch ('http://localhost:8080/users/register', {
                method: 'POST', //Post method to take name, username, and password to MySQL database
                headers: {
                    'Content-Type' : 'application/json',
                },
                body: JSON.stringify({name, userName, password}),
            });
            console.log('Registration success');

            setRegistrationSuccess(true);
        } catch (error) {
            console.error('Error during registration:', error.message);
        }
    };

    if (isLoggedIn) {
        return <p>User is already logged in. Please log out current account before registering.</p>;
    }

    return (
        <div>
            <h2>Registration Form</h2>
            {registrationSuccess ? (
                <p>Registration successful! You can now log in.</p>
            ) : (
                <>
                    <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
                    <br />
                    <input type="text" placeholder="Username" value={userName} onChange={(e) => setUserName(e.target.value)} />
                    <br />
                    <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                    <br />
                    <p><button onClick={handleRegister}>Register</button></p>

                </>
            )}
        </div>
    );
};

export default Register;
