import React, { useState } from "react";
import axios from "axios";

const UserRegister = ({}) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");

  const handleRegistraition = async (e) => {
    e.preventDefault();
    
  };

  return (
    <div className="container">
    <div className="center-container" style={{border: '2px solid black',padding:'2rem',borderRadius:' 1rem'}}>
      <form className="row g-5 pt-5" onSubmit={handleRegistraition}>
        <div className="col-md-12">
          <label className="form-label">
            <h3>Registration</h3>
          </label>
        </div>
        <div className="col-md-12">
          <label className="form-label">
            <h6>Username</h6>
          </label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter Username"
            onChange={(e) => setUsername(e.target.value)}
            name="name"
          />
        </div>
        <div className="col-md-12">
          <label className="form-label">
            <h6>Password</h6>
          </label>
          <input
            type="password"
            name="password"
            className="form-control"
            placeholder="Enter your password"
            onChange={(e) => setPassword(e.target.value)}
            id="password"
          />
        </div>
        <div className="col-12">
          <button
            type="submit"
            className="btn btn-primary"
          >
            Register
          </button>
        </div>
      </form>
    </div>
    </div>
  );
};

export default UserRegister;
