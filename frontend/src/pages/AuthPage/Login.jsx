import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import authbg from "../../assets/authbg.png";
import "./Login.css";

function Login() {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);

  return (
    <div 
      className="login-background"
      style={{ backgroundImage: `url(${authbg})` }}
    >
      <div className="login-card">
        <h2 className="form-title">Login</h2>

        <form className="login-form">
          {/* Email/Number */}
          <div className="form-group">
            <label>Email or Number</label>
            <input
              type="text"
              placeholder="Enter email or number"
              required
            />
          </div>

          {/* Password */}
          <div className="form-group">
            <label>Password</label>
            <div className="password-field">
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Enter password"
                required
              />
              <span
                className="toggle-password"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>
          </div>

          {/* Button */}
          <button type="submit" className="login-btn">
            Login
          </button>
        </form>

        {/* Links */}
        <div className="extra-links">
          <p>
            <a href="/forgot-password">Forgot Password?</a>
          </p>
          <p>
            New to website? <a href="/register">Create an account</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;
