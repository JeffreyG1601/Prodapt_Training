import React, { useState } from "react";
import axios from "axios";

export default function AddEmployee() {
  const [employee, setEmployee] = useState({
    name: "",
    design: "",
    age: "",
    salary: ""
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setEmployee({ ...employee, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const response = await axios.post("http://localhost:8080/api/employees", employee);
      setMessage(`Employee added successfully! ID: ${response.data.id}`);
      setEmployee({ name: "", design: "", age: "", salary: "" }); // reset form
    } catch (err) {
      console.error("Error adding employee:", err);
      setMessage("Failed to add employee. Try again.");
    }
  };

  return (
    <section>
      <h2>Add Employee</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name: </label>
          <input name="name" value={employee.name} onChange={handleChange} required />
        </div>
        <div>
          <label>Designation: </label>
          <input name="design" value={employee.design} onChange={handleChange} required />
        </div>
        <div>
          <label>Age: </label>
          <input type="number" name="age" value={employee.age} onChange={handleChange} required />
        </div>
        <div>
          <label>Salary: </label>
          <input type="number" name="salary" value={employee.salary} onChange={handleChange} required />
        </div>
        <button type="submit">Add Employee</button>
      </form>

      {message && <p style={{ marginTop: "10px", color: "green" }}>{message}</p>}
    </section>
  );
}
