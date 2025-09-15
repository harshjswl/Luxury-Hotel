import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./component/Navbar";
import Home from "./pages/HomePage/Home";
import Amenites from "./pages/HomePage/Amenites";
import Contact from "./pages/HomePage/Contact";
import Location from "./pages/HomePage/Location";
import AboutPage from "./pages/AboutPage/AboutPage";
import RoomsPage from "./pages/RoomsPage/RoomsPage";
import Login from "./pages/AuthPage/Login";
import Register from "./pages/AuthPage/Register";
import ForgotPassword from "./pages/AuthPage/ForgotPassword";
import SetPassword from "./pages/AuthPage/SetPassword";
import VerifyEmail from "./pages/AuthPage/VerifyEmail";
import Footer from "./component/Footer";  

function App() {
  return (
    <Router>
      <Navbar />  
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/aboutPage"  element={<AboutPage />} />
        <Route path="/roomsPage" element={<RoomsPage />} />
        <Route path="/amenities" element={<Amenites/>} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/location" element={<Location />} />
        <Route path="/login" element={<Login />} />  
        <Route path="/register" element={<Register/>}></Route>
        <Route path="/forgot-password" element={<ForgotPassword/>}></Route>
        <Route path="/set-password" element={<SetPassword/>}></Route>
        <Route path="/verify-email" element={<VerifyEmail/>}></Route>
      </Routes>
      <Footer/>
    </Router>
  );
}

export default App;

