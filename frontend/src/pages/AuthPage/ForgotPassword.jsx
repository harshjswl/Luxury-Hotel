import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import authbg from "../../assets/authbg.png";
import "./ForgotPassword.css";

function ForgotPassword() {
  const navigate = useNavigate();
  const [emailOrNumber, setEmailOrNumber] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!emailOrNumber.trim()) {
      alert("Please enter your email or number!");
      return;
    }

    // Later: API call to send reset link / OTP
    console.log("Forgot Password Request:", emailOrNumber);

    alert("Reset instructions sent to your email/number!");
    navigate("/login");
  };

  const handleChangeDetails = () => {
    setEmailOrNumber(""); // clear the input
  };

  return (
    <div
      className="login-background"
      style={{ backgroundImage: `url(${authbg})` }}
    >
      <div className="login-card">
        <h2 className="form-title">Forgot Password</h2>

        <form onSubmit={handleSubmit} className="forgot-form">
          <div className="form-group">
            <label>Email or Number</label>
            <input
              type="text"
              value={emailOrNumber}
              onChange={(e) => setEmailOrNumber(e.target.value)}
              placeholder="Enter email or number"
              required
            />
          </div>

          {/* Change details button (just clears input, not submit) */}
          <button
            type="button"
            className="secondary-btn"
            onClick={handleChangeDetails}
          >
            Change Details
          </button>

          {/* Send OTP button (submits form) */}
          <button type="submit" className="login-btn">
            Send OTP
          </button>
        </form>

        <div className="extra-links">
          <p>
            <a href="/login">Back to Login</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default ForgotPassword;
