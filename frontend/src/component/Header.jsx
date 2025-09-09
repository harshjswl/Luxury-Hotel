import React, { useState } from 'react'
import logo from '../assets/logo.png'
import { FaBars, FaTimes } from "react-icons/fa";
import { useNavigate } from "react-router-dom"; 

function Header() {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate(); 

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const tokenCheck = () => {
    const token = localStorage.getItem("token");
    if (!token) {
    alert('Login first')
      navigate("/login");
    } else {
      alert("Welcome to Luxury Hotel!");
    }
  };

  return (
    <nav className='Navbar'>
      <div className='logo-div'>
        <img src={logo} alt="Hotel logo" />
      </div>

      
      <div className={`list ${isOpen ? "active" : ""}`}>
        <li><a href="/">Home</a></li>
        <li><a href="">About</a></li>
        <li><a href="">Rooms</a></li>
        <li><a href="">Amenities</a></li>
        <li><a href="">Location</a></li>
        <li><a href="">My account</a></li>
        <li><a href="">Contact</a></li>

        
        <div className="mobile-button">
          <button className="book-button" onClick={tokenCheck}>
            Book Now
          </button>
        </div>
      </div>

      
      <div className='button-div'>
        <button 
          className='book-button'
          onClick={tokenCheck}
        >
          Book Now
        </button>
      </div>

      
      <div className="menu-icon" onClick={toggleMenu}>
        {isOpen ? <FaTimes /> : <FaBars />}
      </div>
    </nav>
  )
}

export default Header
