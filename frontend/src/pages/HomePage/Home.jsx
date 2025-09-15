import React from 'react'
import Banner from '../../contant/Banner'
import About from './About'
import Rooms from './Rooms'
import Amenites from './Amenites'
import Contact from './Contact'
import Location from './Location'

function Home() {
  return (
    <div>
      <section id="home">
        <Banner />
      </section>

      <section id="about">
        <About />
      </section>

      <section id="rooms">
        <Rooms />
      </section>

        <section id="amenities">
        <Amenites />
      </section>

      <section id="contact">
        <Contact />
      </section>

      <section id="location">
        <Location />
      </section>
     


    </div>
  )
}

export default Home
