import React, { useState } from "react";
import { FaEye } from "react-icons/fa";
import { FaEyeSlash } from "react-icons/fa";
import { RegisterUser } from "../services/api";
import { useNavigate } from "react-router-dom";
function Register() {
  const navigate=useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const[firstName,setFirstName]=useState("");
  const [lastName,setLastName]=useState("");
  const[number,setNumber]=useState("");
  const[email,setEmail]=useState("");
  const[firstPassword,setFirstPassword]=useState("");
  const[confirmPassword,setConfirmPassword]=useState("");

  const handleRegister=async(e)=>{
   e.preventDefault();
  if (firstPassword !== confirmPassword) {
    alert("Passwords do not match!");
    setConfirmPassword(""); 
    return;
  }

   try{
    const response =await RegisterUser({
      firstName,
      lastName,
      number,
      email,
      password: firstPassword,
      roles: ["ROLE_USER"], 
    });
    console.log("register Success:", response.data);
    localStorage.setItem("email", email);
    navigate("/otp-verify")
   }catch(error){
    console.error("Error register in:", error.response?.data || error.message);
   }
  };


  return (
    <div className="register-div">
      <div className="otp-container">
        <h2 className="form-title">Register</h2>
        <form className="register-form" onSubmit={handleRegister}>
          <div className="form-group">
            <label>First Name</label>
            <input 
            type="text"
            value={firstName}
            onChange={(e)=> setFirstName(e.target.value)}
            placeholder="Enter first name" 
            required />
          </div>

          <div className="form-group">
            <label>Last Name</label>
            <input 
            type="text"
            value={lastName}
            onChange={(e)=>setLastName(e.target.value)}
            placeholder="Enter last name"
            required />
          </div>

          <div className="form-group">
            <label>Number</label>
            <input 
            type="text" 
            value={number}
            onChange={(e)=>setNumber(e.target.value)}
            placeholder="Enter your number" 
            required />
          </div>

          <div className="form-group">
            <label>Email</label>
            <input 
            type="email" 
            placeholder="Enter your email" 
            value={email}
            onChange={(e)=>setEmail(e.target.value)}
            required />
          </div>

          <div className="form-group">
            <label>Password</label>
            <div style={{ position: "relative" }}>
              <input
                type={showPassword ? "text" : "password"}
                value={firstPassword}
                onChange={(e)=>setFirstPassword(e.target.value)}
                placeholder="Enter your password"
                required
                style={{ paddingRight: "30px" }}
              />
              <span
                onClick={() => setShowPassword(!showPassword)}
                style={{
                  position: "absolute",
                  right: "10px",
                  top: "50%",
                  transform: "translateY(-50%)",
                  cursor: "pointer",
                  userSelect: "none",
                }}
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>
          </div>

          <div className="form-group">
            <label>Confirm Password</label>
            <div style={{ position: "relative" }}>
              <input
                type={showConfirmPassword ? "text" : "password"}
                value={confirmPassword}
                onChange={(e)=>setConfirmPassword(e.target.value)}
                placeholder="Enter confirm password"
                required
                style={{ paddingRight: "30px" }}
              />
              <span
                onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                style={{
                  position: "absolute",
                  right: "10px",
                  top: "50%",
                  transform: "translateY(-50%)",
                  cursor: "pointer",
                  userSelect: "none",
                }}
              >
                {showConfirmPassword ? <FaEyeSlash />  :<FaEye />}
              </span>
            </div>
          </div>

          <div className="form-group">
            <button type="submit" className="login-btn">
              Register
            </button>
          </div>
        </form>

        <div className="extra-links">
          <p>
            Already a user? <a href="/login">Login to your account</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Register;
