import React, { useState } from "react";
import { FaEye } from "react-icons/fa";
import { FaEyeSlash } from "react-icons/fa";
function Register() {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  return (
    <div className="register-div">
      <div className="register-container">
        <h2 className="form-title">Register</h2>
        <form className="register-form">
          <div className="form-group">
            <label>First Name</label>
            <input type="text" placeholder="Enter first name" required />
          </div>

          <div className="form-group">
            <label>Last Name</label>
            <input type="text" placeholder="Enter last name" required />
          </div>

          <div className="form-group">
            <label>Number</label>
            <input type="text" placeholder="Enter your number" required />
          </div>

          <div className="form-group">
            <label>Email</label>
            <input type="email" placeholder="Enter your email" required />
          </div>

          <div className="form-group">
            <label>Password</label>
            <div style={{ position: "relative" }}>
              <input
                type={showPassword ? "text" : "password"}
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
