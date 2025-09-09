import React, { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";

function Login() {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <div className="login-div">
      <div className="form-container">
        <h2 className="form-title">Login</h2>
        <form className="login-form">
          <div className="form-group">
            <label>Email or Number</label>
            <input type="text" placeholder="Enter email or number" required />
          </div>

          <div className="form-group">
            <label>Password</label>
            <div style={{ position: "relative" }}>
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Enter password"
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
            <button type="submit" className="login-btn">
              Login
            </button>
          </div>
        </form>

        <div className="extra-links">
          <p>
            <a href="#">Forgot Password?</a>
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
