import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./component/Header"
import Banner from "./pages/Banner"
import Login from './pages/Login';
import Register from './pages/Register';
import Footer from './component/Footer';
import SlidingBanner from './component/SlidingBanner';
import OtpVerify from './pages/OtpVerify';
function App() {


  return (
    <>
    <Router>
      <Header/>
      <SlidingBanner/>
      <div className="container-fluid">
        <Routes>
          <Route path="/" element={<Banner/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route  path='/register' element={<Register/>}/>
          <Route path='/otp-verify' element={<OtpVerify/>}/>
        </Routes>
      </div>
      </Router>
      <Footer/>
    </>
  )
}

export default App
