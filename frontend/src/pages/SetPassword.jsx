import React from 'react'

function SetPassword() {
  return (
    <div className='setpassword-div'>
      <div className='form-container'>
        <h2 className='from-title'>Set Password</h2>
        <form className='setpassword-form'>
           <div className="form-group">
            <label>OTP</label>
            <input
              type="text"
              placeholder="Enter OTP"
              required
            />
          </div>
           <div className="form-group">
            <label>New Password</label>
            <input
              type="password"
              placeholder="Enter new password"
              required
            />
          </div>
           <div className="form-group">
            <label>Confirm Password</label>
            <input
              type="password"
              placeholder="Enter confirm password"
              required
            />
          </div>
          <button type="submit" className="verification-btn">
            change
          </button>
          <div className="Goback-links">
          <p>
            <a href="/forgot-password">change the details</a>
          </p>
        </div>
        </form>
      </div>
    </div>
  )
}

export default SetPassword
