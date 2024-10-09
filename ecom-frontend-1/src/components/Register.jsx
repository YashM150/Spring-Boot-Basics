import React, { useState } from "react";
import { Link } from "react-router-dom";

const Register = () => {
  return (
    <div className="container">
    <div className="center-container" style={{border: '2px solid black',padding:'2rem',borderRadius:' 1rem'}}>

        <div className="col-md-12">
          <label className="form-label">
            <h2>Register</h2>
            <h3>As a..</h3>
          </label>
        </div>
        <div className="col-md-12">
          <label className="form-label">
            <h6>Admin, click <Link to="/admin">here!</Link></h6>
          </label>
        </div>
        <div className="col-md-12">
          <label className="form-label">
            <h6>User, click <Link to="/user">here!</Link></h6>
          </label>
        </div>
        
    </div>
    </div>
  );
};

export default Register;
