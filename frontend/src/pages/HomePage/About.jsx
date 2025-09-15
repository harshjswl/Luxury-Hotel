import React from 'react'
import './About.css'

function About() {

    const handleMoreClick = () => {
    window.location.href = "/aboutpage";
  };


  return (
    <div className='about-us'>
      <div className="about-text">
        <h1>About</h1>
        <p>
          Esse dolorum voluptatum ullam est sint nemo et est ipsa porro placeat
          quibusdam quia assumenda numquam molestias.
        </p>
         <button className="more-btn" onClick={handleMoreClick}>More</button>
      </div>

      <div className='boxes'>
        <div>
          <h3>132</h3>
          <p>LUXURY ROOMS</p>
        </div>
        <div>
          <h3>98%</h3>
          <p>GUEST SATISFACTION</p>
        </div>
        <div>
          <h3>1892</h3>
          <p>ESTABLISHED</p>
        </div>
        <div>
          <h3>27</h3>
          <p>AWARDS WON</p>
        </div>
      </div>
    </div>
  )
}

export default About
