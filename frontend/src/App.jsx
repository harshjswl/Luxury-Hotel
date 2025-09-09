import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./component/Header"
import Banner from "./pages/Banner"
import Login from './pages/Login';
import Register from './pages/Register';
import Footer from './component/Footer';
import SlidingBanner from './component/SlidingBanner';
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
        </Routes>
      </div>
      </Router>
      <Footer/>
    </>
  )
}

export default App
