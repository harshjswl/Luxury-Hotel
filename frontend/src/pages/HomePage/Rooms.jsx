import React from 'react';
import delsux from '../../assets/delsux.png';
import cozye from '../../assets/cozye.png';
import family from '../../assets/family.png';
import gardenview from '../../assets/gardeview.png';
import './Rooms.css';

function Rooms() {

  const handleMoreClick =()=>{
    window.location.href = '/roomspage';
  }

  return (
    <section className="rooms-section">
      <div className="rooms-header">
        <h1>Our Rooms</h1>
        <p>
          Esse dolorum voluptatum ullam est sint nemo et est ipsa porro placeat
          quibusdam quia assumenda numquam molestias.
        </p>
      </div>

      <div className="rooms-grid">
        <div className="room-card">
          <img src={delsux} alt="Deluxe Ocean Suite" />
          <h3>Deluxe Ocean Suite Hotel Room</h3>
        </div>

        <div className="room-card">
          <img src={cozye} alt="Cozy Elegant Hotel Room" />
          <h3>Cozy Elegant Hotel Room</h3>
        </div>

        <div className="room-card">
          <img src={family} alt="Luxury Family Suite" />
          <h3>Luxury Family Suite Room</h3>
        </div>

        <div className="room-card">
          <img src={gardenview} alt="Luxury Garden View Double Room" />
          <h3>Luxury Garden View Double Room</h3>
        </div>
      </div>

      <div className="rooms-footer">
        <button className="watch-more-btn" onClick={handleMoreClick}>Watch More</button>
      </div>
    </section>
  );
}

export default Rooms;
