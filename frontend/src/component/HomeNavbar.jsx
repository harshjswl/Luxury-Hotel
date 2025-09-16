import React from "react";
import logo from "../assets/logo.png";
import "./Navbar.css";
import { FaRegUserCircle } from "react-icons/fa";
import { Link } from "react-scroll";

function HomeNavbar() {
  return (
    <nav className="navbar">
      <div className="logo-div">
        <img src={logo} alt="logo" />
      </div>
      <div className="list-div">
        <ul>
          <li>
            <Link
              to="home"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
            >
              <a href="/">Home</a>
            </Link>
          </li>
          <li>
            <Link
              to="about"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
            >
              About
            </Link>
          </li>
          <li>
            <Link
              to="rooms"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
              href="/roomsPage"
            >
              Rooms
            </Link>
          </li>

          <li>
            <Link
              to="amenities"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
            >
              Amenities
            </Link>
          </li>

          <li>
            <Link
              to="contact"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
            >
              Contact
            </Link>
          </li>

              <li>
            <Link
              to="location"
              spy={true}
              smooth={true}
              offset={-100}
              duration={500}
              activeClass="active"
            >
              Location
            </Link>
          </li>

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

export default HomeNavbar;
