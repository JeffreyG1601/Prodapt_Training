// src/components/EmployeeFormClass.jsx
import React from "react";
import { EmployeeContext } from "../context/EmployeeContext";

class EmployeeFormClass extends React.Component {
  static contextType = EmployeeContext;

  state = { name: "", design: "", age: "", salary: "" };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.context.addEmployee(this.state);
    this.setState({ name: "", design: "", age: "", salary: "" });
  };

  render() {
    return (
      <div style={{ border: "2px solid purple", padding: "10px", margin: "10px" }}>
        <h2>Class Component - Add Employee</h2>
        <form onSubmit={this.handleSubmit}>
          <input name="name" value={this.state.name} onChange={this.handleChange} placeholder="Name" />
          <input name="design" value={this.state.design} onChange={this.handleChange} placeholder="Designation" />
          <input name="age" value={this.state.age} onChange={this.handleChange} placeholder="Age" />
          <input name="salary" value={this.state.salary} onChange={this.handleChange} placeholder="Salary" />
          <button type="submit">Add</button>
        </form>
      </div>
    );
  }
}

export default EmployeeFormClass;
