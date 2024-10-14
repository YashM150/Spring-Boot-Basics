import React, { useState } from "react";
import { Link } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Username:", username);
    console.log("Password:", password);

    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
        credentials: 'include',
      });

      if (response.ok) {
        // Successful login (200 OK)
        const message = await response.text();
        alert("Login successful: " + message);
        localStorage.setItem("User",message);
        localStorage.setItem("isAuthenticated", "true");
        window.location.href = '/'; // Redirect to home page
      } else if (response.status === 401) {
        // Unauthorized (Invalid credentials)
        alert("Invalid credentials. Please try again.");
      } else if (response.status === 500) {
        // Internal server error
        alert("An error occurred while logging in. Please try again later.");
      }
    } catch (error) {
      console.error("Error during login:", error);
      alert("Failed to login. Please check your network connection.");
    }
  };

  return (
    <div className="container">
      <div className="center-container" style={{ border: '2px solid black', padding: '2rem', borderRadius: '1rem' }}>
        <form className="row g-5 pt-5" onSubmit={handleLogin}>
          <div className="col-md-12">
            <label className="form-label">
              <h3>Login</h3>
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
              value={username}
              required
            />
          </div>
          <div className="col-md-12">
            <label className="form-label">
              <h6>Password</h6>
            </label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter your password"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              required
            />
          </div>
          <div className="col-md-12">
            <div className="form-label">
              Don't have an account? Register <Link to="/register">here</Link>!
            </div>
          </div>
          <div className="col-12">
            <button type="submit" className="btn btn-primary">
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
