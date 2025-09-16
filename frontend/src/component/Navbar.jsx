import React from "react";
import logo from "../assets/logo.png";
import "./Navbar.css";
import { FaRegUserCircle } from "react-icons/fa";
import { Link } from "react-scroll";

function Navbar() {
  return (
    <nav className="navbar">
      <div className="logo-div">
        <img src={logo} alt="logo" />
      </div>
      <div className="list-div">

        <ul>
          <li><a href="/" className={location.pathname === "/" ? "active" : ""}>Home</a></li>
          <li><a href="/aboutpage" className={location.pathname === "/aboutpage" ? "active" : ""}>About</a></li>
          <li><a href="/roomspage" className={location.pathname === "/roomspage" ? "active" : ""}>Rooms</a></li>
          <li><a href="/amenities" className={location.pathname === "/amenities" ? "active" : ""}>Amenities</a></li>
          <li><a href="/location" className={location.pathname === "/location" ? "active" : ""}>Location</a></li>
          <li><a href="/contact" className={location.pathname === "/contact" ? "active" : ""}>Contact</a></li>
        </ul>

      </div>
      <div className="right-side">
        <div className="user-icon">
          <a href="/login"><FaRegUserCircle/></a>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
