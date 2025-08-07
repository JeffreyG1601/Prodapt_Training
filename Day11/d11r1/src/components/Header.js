import React from 'react';
import '../styles/Header.css'; // Correct path for styles
import About from './About';
import logo from '../logo.svg'; // Corrected path for logo
function Navbar() {
    return (
        <nav className="navbar">
            <a href="/" className="navbar-logo">
                <img src={logo} alt="Logo" style={{ height: '40px', verticalAlign: 'middle', marginRight: '8px' }} />
            </a>
            <div className="navbar-links">
                <a href="/about" className="navbar-link">About</a>
                <a href="/contact" className="navbar-link">Contact</a>
                <a href="/login" className="navbar-link">Login</a>
            </div>
        </nav>
    );
}

export default Navbar;