import React from 'react';
import { Link } from 'react-router-dom'
import { Navbar, Nav } from 'react-bootstrap';;

function NavigationBar() {

    return (
        <Navbar bg="light" variant="light" style={{ color: 'white' }}>
            <Link to="/" className="navbar-brand">Species</Link>
            <Nav className="mr-auto" >
                <Link to="/" className="nav-link"> Home</Link>
                <Link to="/all-species" className="nav-link">All species</Link>
                <Link to="/species-form" className="nav-link">Add species</Link>
            </Nav>
        </Navbar>
    );
}
export default NavigationBar;