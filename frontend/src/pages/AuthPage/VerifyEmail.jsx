import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import authbg from "../../assets/authbg.png";
import "./VerifyEmail.css";

function VerifyEmail() {
  const navigate = useNavigate();
  const [otp, setOtp] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!otp.trim()) {
      alert("Please enter OTP!");
      return;
    }

    // Later: Verify OTP with backend
    console.log("Entered OTP:", otp);

    alert("Email verified successfully!");
    navigate("/login");
  };

  const handleResendOtp = () => {
    // API call later
    alert("OTP resent successfully!");
  };

  return (
    <div
      className="login-background"
      style={{ backgroundImage: `url(${authbg})` }}
    >
      <div className="login-card">
        <h2 className="form-title">Verify Email</h2>

        <form className="verify-form" onSubmit={handleSubmit}>
          {/* OTP Input */}
          <div className="form-group otp-group">
            <label>Enter OTP</label>
            <div className="otp-field">
              <input
                type="text"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
                placeholder="Enter OTP"
                required
              />
              <button
                type="button"
                className="secondary-btn resend-btn"
                onClick={handleResendOtp}
              >
                Resend OTP
              </button>
            </div>
          </div>

          {/* Submit */}
          <button type="submit" className="login-btn">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default VerifyEmail;
