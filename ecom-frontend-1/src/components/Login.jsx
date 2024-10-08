import React, { useState } from "react";
import axios from "axios";

const Login = ({ setAuthenticated }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", { username, password });
      localStorage.setItem("token", response.data.jwt);
      setAuthenticated(true);
    } catch (error) {
      console.error("Login failed", error);
    }
  };

  return (
    <div className="container">
    <div className="center-container" style={{border: '2px solid black',padding:'2rem',borderRadius:' 1rem'}}>
      <form className="row g-5 pt-5" onSubmit={handleLogin}>
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
            Login
          </button>
        </div>
      </form>
    </div>
    </div>
  );
};

export default Login;
