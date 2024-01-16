// RoutePath.jsx

import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import MainPage from "../pages/MainPage";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import EditorPage from "../pages/EditorPage";
import './MenuBar.css';
import Logout from "./Logout"; // Import the styles
import TextEditor from './TextEditor';
import CreateDocument from "../pages/CreateDocument";

import AboutUsPage from "../pages/AboutUsPage";
import SearchBar from './Searchbar';
import SearchPage from '../pages/SearchPage';


const UserMenu = () => {
    return (
        <div className="user-menu">
            {/* Add user-related menu items or components here */}
            <Link to="/profile" className="menu-item">Profile</Link>
            <Link to="/register" className="menu-item">Register</Link>
        </div>
    );
};

const MenuBar = () => {
    return (
        <div className="menu-bar">
            <div className="menu-items">
                <Link to="/edit" className="menu-item">Edit</Link>
                <Link to="/" className="menu-item">Home</Link>
            </div>
            <UserMenu /> {/* Render the UserMenu component */}
        </div>
    );
};

// resources: https://stackoverflow.com/questions/58334185/react-router-changes-the-url-but-the-component-is-not-rendered
// read this later about scrolling to top when navigating:
// https://stackoverflow.com/questions/36904185/react-router-scroll-to-top-on-every-transition
const RoutePath = () => {
    return (
        // commented out router here and encompassed App into it in the index.js file
        // that way, everything within the app is in the context of Router
        // <Router> 
            <div>
                {/* commented out for now, bring it back later
                <MenuBar /> */}

                <Routes>
                    {/* exact keyword is a thing in the new routing version */}
                    <Route exact path="/" element={<MainPage />} />
                    <Route path="/document/:id" exact element={<EditorPage />} />
                    <Route path="/register" exact element={<RegisterPage />} />
                    <Route path="/about" exact element={<AboutUsPage />} />
                    <Route path="login" exact element={<LoginPage />} />
                    <Route path="/logout" exact element={<Logout/>} />
                    <Route path="/edit" exact element={<CreateDocument/>} />
                    <Route path="/search/:search" exact element={<SearchPage/>} />
                </Routes>

            </div>
        // </Router> 


    );
};

export default RoutePath;
