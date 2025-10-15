import React from "react";
import { useNavigate } from "react-router-dom";
import "./home.css";

export default function Home() {
  const navigate = useNavigate();

  return (
    <section className="home-container">
      <h2 className="home-title">Employee Management System</h2>
      <p className="home-subtitle">
        Welcome! Use the buttons below to manage employee data.
      </p>

      <div className="home-buttons">
        <button className="btn insert-btn" onClick={() => navigate("/add-employee")}>
          ➕ Insert Employee
        </button>
        <button className="btn display-btn" onClick={() => navigate("/employees")}>
          📋 Display Employees
        </button>
      </div>
    </section>
  );
}
