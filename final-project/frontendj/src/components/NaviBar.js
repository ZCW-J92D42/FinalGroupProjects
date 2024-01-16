import './NaviBar.css'
import {useEffect, useState} from "react";
function NaviBar() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Check user login status here, login and register does not appear
        const userLoggedIn = localStorage.getItem('userInfo') !== null;
        setIsLoggedIn(userLoggedIn);

    }, []);


    const handleLogout = () => {
        // Perform logout actions, e.g., clearing local storage
        localStorage.removeItem('userInfo');
        localStorage.removeItem('userName');
        localStorage.removeItem('userId');
        // Update the state to reflect the logout
        setIsLoggedIn(false);
        // Add any additional logout actions here

        // Redirect to the home page or perform any other necessary actions
        window.location.href = '/';
    };



    return(
        <div className="navbar">
            <a href="/">Home</a>
            {!isLoggedIn && <a href="/login">Login</a>}
            {!isLoggedIn && <a href="/register">Register</a>}
            {isLoggedIn && <a onClick={handleLogout}>Logout</a>}
            <div className="dropdown">
                <button className="dropbtn">Info
                    <i className="fa fa-caret-down"></i>
                </button>
                <div className="dropdown-content">
                    <a href="/about">About Us</a>
                    <a href="https://github.com/ZCW9-2-Peponapis/FinalGroupProjects">GitHub Link</a>
                </div>
            </div>
        </div>
    );
}

export default NaviBar;