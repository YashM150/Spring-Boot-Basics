import React, { useState } from "react";
import { Link } from "react-router-dom";

const Login = ({setAuthenticated}) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Username:",username);
    console.log("Password:",password);
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),
      credentials: 'include',
    });
  
    if (response.status==200) {
      const message = await response.text();
      alert(message);
      // setAuthenticated(true);
      localStorage.setItem("isAuthenticated", "true"); 
      window.location.href = '/';  // Redirect to /api/
      
    } else {
      console.log("Username:",username);
      console.log("Password:",password);
      alert('Invalid credentials');
    }
  };

  return (
    <div className="container">
    <div className="center-container" style={{border: '2px solid black',padding:'2rem',borderRadius:' 1rem'}}>
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
        <div className="col-md-12">
          <div className="form-label">If not have a account register <Link to="/register">here</Link> !</div>
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
