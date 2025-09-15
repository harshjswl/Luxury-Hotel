import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import authbg from "../../assets/authbg.png";
import "./SetPassword.css";

function SetPassword() {
  const navigate = useNavigate();
  const [otp, setOtp] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!otp.trim()) {
      alert("Please enter OTP!");
      return;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    // Later: Call backend API
    console.log("OTP:", otp);
    console.log("New Password:", password);

    alert("Password set successfully!");
    navigate("/login");
  };

  const handleResendOtp = () => {
    // API call for resend later
    alert("OTP resent successfully!");
  };

  return (
    <div
      className="login-background"
      style={{ backgroundImage: `url(${authbg})` }}
    >
      <div className="login-card">
        <h2 className="form-title">Set New Password</h2>

        <form className="set-password-form" onSubmit={handleSubmit}>
          {/* OTP */}
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

          {/* New Password */}
          <div className="form-group">
            <label>New Password</label>
            <div className="password-field">
              <input
                type={showPassword ? "text" : "password"}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter new password"
                required
              />
              <span onClick={() => setShowPassword(!showPassword)}>
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>
          </div>

          {/* Confirm Password */}
          <div className="form-group">
            <label>Confirm New Password</label>
            <div className="password-field">
              <input
                type={showConfirmPassword ? "text" : "password"}
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Confirm new password"
                required
              />
              <span onClick={() => setShowConfirmPassword(!showConfirmPassword)}>
                {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>
          </div>

          {/* Submit Button */}
          <button type="submit" className="login-btn">
            Set Password
          </button>
        </form>

        {/* Extra link */}
        <div className="extra-links">
          <p>
            <a href="/forgot-password">Change the details</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default SetPassword;
