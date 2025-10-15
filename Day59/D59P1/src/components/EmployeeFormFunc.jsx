// src/components/EmployeeFormFunc.jsx
import { useContext, useState } from "react";
import { EmployeeContext } from "../context/EmployeeContext";

function EmployeeFormFunc() {
  const { addEmployee } = useContext(EmployeeContext);
  const [formData, setFormData] = useState({
    name: "",
    design: "",
    age: "",
    salary: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addEmployee(formData);
    setFormData({ name: "", design: "", age: "", salary: "" });
  };

  return (
    <div style={{ border: "2px solid green", padding: "10px", margin: "10px" }}>
      <h2>Functional Component - Add Employee</h2>
      <form onSubmit={handleSubmit}>
        <input name="name" value={formData.name} onChange={handleChange} placeholder="Name" />
        <input name="design" value={formData.design} onChange={handleChange} placeholder="Designation" />
        <input name="age" value={formData.age} onChange={handleChange} placeholder="Age" />
        <input name="salary" value={formData.salary} onChange={handleChange} placeholder="Salary" />
        <button type="submit">Add</button>
      </form>
    </div>
  );
}

export default EmployeeFormFunc;
