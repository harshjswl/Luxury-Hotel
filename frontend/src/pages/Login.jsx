import React, { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { loginUser } from "../services/api";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate=useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const [emailOrNumber, setEmailOrNumber] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser({ emailOrNumber, password });
      console.log("Login Success:", response.data);

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("refreshToken", response.data.refreshToken);
      localStorage.setItem("user", JSON.stringify(response.data.user));
      navigate("/")    
    } catch (error) {
      console.error("Error logging in:", error.response?.data || error.message);
    }
  };

  return (
    <div className="login-div">
      <div className="form-container">
        <h2 className="form-title">Login</h2>
        <form className="login-form" onSubmit={handleLogin}>
          <div className="form-group">
            <label>Email or Number</label>
            <input
              type="text"
              placeholder="Enter email or number"
              value={emailOrNumber}
              onChange={(e) => setEmailOrNumber(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <div style={{ position: "relative" }}>
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Enter password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
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
