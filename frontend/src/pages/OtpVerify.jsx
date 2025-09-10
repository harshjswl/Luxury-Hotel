import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { OtpVerification } from '../services/api'  // ✅ correct import name

function OtpVerify() {
  const navigate = useNavigate();
  const [otp, setOtp] = useState("");
  const email = localStorage.getItem("email");

  const handleVerification = async (e) => {
    e.preventDefault(); // ✅ prevent reload
    try {
      const response = await OtpVerification({ otp, email }); // ✅ use correct function
      console.log("Email is verified:", response.data);

      localStorage.removeItem("email");
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("refreshToken", response.data.refreshToken);
      localStorage.setItem("user", JSON.stringify(response.data.user));

      navigate("/");
    } catch (error) {
      console.error("Error verifying:", error.response?.data || error.message);
    }
  };

  return (
    <div className='Otp-verify-div'>
      <div className='form-container'>
        <h2 className="form-title">Email Verification</h2>
        <form className='otp-form' onSubmit={handleVerification}>
          <div className="form-group">
            <label>OTP</label>
            <input
              type="text"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="verification-btn">
            Verify
          </button>
        </form>
      </div>
    </div>
  );
}

export default OtpVerify;
