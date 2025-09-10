import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { forgotPassword } from "../services/api";

function ForgotPassword() {
  const navigate=useNavigate();
  const[emailOrNumber,setEmailOrNumber]=useState("");

  const handleSendOtp=async(e)=>{
     e.preventDefault();
     try{
      const response= await forgotPassword({emailOrNumber});
      console.log("response: ",response.data);

      localStorage.setItem("emailOrNumber",emailOrNumber);
      navigate("/")
     }
     catch (error) {
      console.error("Error:", error.response?.data || error.message);
    }
  };

  return (
    <div className="forgot-password-div">
      <div className="forgot-password-container">
        <h2 className="form-title">Forgot Password</h2>
        <form className="forgot-password-form" onSubmit={handleSendOtp}>
          <div className="form-group">
            <label>Email or Number</label>
            <input
              type="text"
              value={emailOrNumber}
              onChange={(e)=>setEmailOrNumber(e.target.value)}
              placeholder="Enter email or number"
              required
            />
          </div>
          <button type="submit" className="verification-btn">
            Submit
          </button>
          <div className="login-links">
            <p>
              <a href="/login">Back to login</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ForgotPassword;
