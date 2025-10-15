import { useContext, useState } from "react";
import { EmployeeContext } from "../context/EmployeeContext";

function EmployeeListFunc() {
  // ✅ Extract all context values at the top level
  const { employees, updateEmployee, deleteEmployee } = useContext(EmployeeContext);

  const [selected, setSelected] = useState(null);        // For highlighting selected employee
  const [editingId, setEditingId] = useState(null);      // Track which employee is being edited
  const [editForm, setEditForm] = useState({             // Form state for editing
    name: "",
    design: "",
    age: "",
    salary: "",
  });

  // Highlight employee on click
  const handleClick = (id) => setSelected(id);

  // Start editing an employee
  const startEdit = (emp) => {
    setEditingId(emp.id);
    setEditForm({
      name: emp.name,
      design: emp.design,
      age: emp.age,
      salary: emp.salary,
    });
  };

  // Update form values while typing
  const handleEditChange = (e) => {
    setEditForm({ ...editForm, [e.target.name]: e.target.value });
  };

  // Save edited employee
  const submitEdit = () => {
    updateEmployee(editingId, editForm); // call context function
    setEditingId(null);                  // exit edit mode
  };

  return (
    <div style={{ border: "2px solid blue", padding: "10px", margin: "10px" }}>
      <h2>Functional Component - Employee List</h2>
      {employees.map((emp) => (
        <div
          key={emp.id}
          style={{
            border: "1px solid #ccc",
            padding: "8px",
            margin: "6px 0",
            backgroundColor: selected === emp.id ? "#edf2f7" : "white",
          }}
          onClick={() => handleClick(emp.id)}
        >
          {editingId === emp.id ? (
            // Edit form for this employee
            <div>
              <input
                name="name"
                value={editForm.name}
                onChange={handleEditChange}
                placeholder="Name"
              />
              <input
                name="design"
                value={editForm.design}
                onChange={handleEditChange}
                placeholder="Designation"
              />
              <input
                name="age"
                value={editForm.age}
                onChange={handleEditChange}
                placeholder="Age"
              />
              <input
                name="salary"
                value={editForm.salary}
                onChange={handleEditChange}
                placeholder="Salary"
              />
              <button onClick={submitEdit}>Save</button>
              <button onClick={() => setEditingId(null)}>Cancel</button>
            </div>
          ) : (
            // Normal display
            <>
              {emp.name} - {emp.design} - {emp.age} - ₹{emp.salary}
              <button onClick={() => startEdit(emp)}>Edit</button>
              <button onClick={() => deleteEmployee(emp.id)}>Delete</button>
            </>
          )}
        </div>
      ))}
    </div>
  );
}

export default EmployeeListFunc;
