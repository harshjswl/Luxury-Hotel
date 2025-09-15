import React from 'react'
import holl from '../assets/holl.png'
import './Banner.css'
import { FaStar } from "react-icons/fa";
import { FaLocationDot } from "react-icons/fa6";
import { FaAward } from "react-icons/fa";
function Banner() {
  return (
    <div className='banner'>
      <img src={holl} alt="holl" />
      <div className='banner-contant'>
        <h1>Discover Elegance and Comfort</h1>
        <p>Experience unparalleled luxury in the heart of the city with world-class amenities and personalized service that exceeds every expectation.</p>
        <h4><FaStar />5-Star Luxury</h4>
        <h4><FaLocationDot />Prime Location</h4>
        <h4><FaAward />Award winning</h4>


        <div className='booking-div'>
          <form>
            <label>check-in</label>
            <input 
            type="date" 
            placeholder='enter'
            />
            <label>check-out</label>
            <input 
            type="date"
            />
            <label>location</label>
            <input 
            type="text"
            />
            <label >Guests</label>
            <input type="text" />
            <label >Rooms</label>
            <input type="text" />
            <button>Book Now</button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default Banner
