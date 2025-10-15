import React, { useEffect, useState } from "react";
import axios from "axios";

export default function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/api/employees")
      .then(response => {
        setEmployees(response.data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Error fetching employees:", err);
        setError("Failed to load employees");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading employees...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <section>
      <h2>Employees</h2>
      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Designation</th>
            <th>Age</th>
            <th>Salary</th>
          </tr>
        </thead>
        <tbody>
          {employees.length === 0 ? (
            <tr><td colSpan="5">No employees found</td></tr>
          ) : (
            employees.map(emp => (
              <tr key={emp.id}>
                <td>{emp.id}</td>
                <td>{emp.name}</td>
                <td>{emp.design}</td>
                <td>{emp.age}</td>
                <td>{emp.salary}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </section>
  );
}
