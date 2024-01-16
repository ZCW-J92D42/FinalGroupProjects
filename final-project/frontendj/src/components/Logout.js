// Logout.js
import React from 'react';

const Logout = ({ onLogout }) => {
    const handleLogoutClick = () => {
        // Additional logout actions specific to the Logout component
        console.log('Successful Log out');
        // Call the onLogout callback passed from UserComponent
        onLogout();
    };

    return (
        <div className="logout-component">
            {/* Your logout component UI here */}
            <button onClick={handleLogoutClick}>Logout</button>
        </div>
    );
};

export default Logout;
