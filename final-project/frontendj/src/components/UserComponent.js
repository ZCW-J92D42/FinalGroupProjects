import React, { useState } from 'react';
import Login from './Login'; // Create a Login component
import user from '../user.png'; // Import your login icon
import './UserComponent.css'; // Import a CSS file for stylin


const UserComponent = () => {
    const isLoggedIn = () => {
        const userId = localStorage.getItem('userInfo');
        const userName = localStorage.getItem('userName');
        return localStorage.getItem('userInfo') !== null;
    };
    const jsonString = localStorage.getItem('userInfo');

    const jsonObject = JSON.parse(jsonString);

    const refreshPage = () => {
        window.location.reload();
    }

    const [showLoginMenu, setShowLoginMenu] = useState(false);
    const [showRegister, setShowRegister] = useState(false);

    const handleIconClick = () => {
        setShowLoginMenu(!showLoginMenu);
    };

    const handleLogin = () => {
        setShowLoginMenu(false); // Close the login menu after successful login
    };

    const handleLogout = () => {
        // Perform logout actions, e.g., clearing local storage
        localStorage.removeItem('userInfo');
        localStorage.removeItem('userName');
        localStorage.removeItem('userId');
        // Update the state to reflect the logout
        setShowLoginMenu(false);
        {refreshPage()}

    };

    const handleRegister = () => {
        setShowRegister(!showRegister);
        setShowLoginMenu(false); // Close the login menu when switching to register

    };

    return (
        <div className="navbar">
            {/* Your other navbar elements */}
            <div className={`dropdown-container ${showLoginMenu ? 'open' : ''}`}>
                {/* Display user session information */}
                <img
                    src={user}
                    alt="Login"
                    onClick={handleIconClick}
                    className="user-icon"
                />
                <div className={`dropdown-menu ${showLoginMenu ? 'show' : ''}`}>
                    {isLoggedIn() ? (
                        <div className="user-details">
                            <p>Welcome to ZipDocs, {jsonObject.name}!</p>
                            <p>User ID: {jsonObject.id}</p>
                            <button onClick={handleLogout}>Logout</button>
                        </div>
                    ) : (
                        <>
                            <Login onLogin={handleLogin}/>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default UserComponent;