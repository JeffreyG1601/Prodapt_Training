import React from "react";
import { EmployeeContext } from "../context/EmployeeContext";

class EmployeeListClass extends React.Component {
  static contextType = EmployeeContext;

  state = { selected: null, editingId: null, editForm: { name: "", design: "", age: "", salary: "" } };

  handleClick = (id) => this.setState({ selected: id });

  startEdit = (emp) => {
    this.setState({
      editingId: emp.id,
      editForm: { name: emp.name, design: emp.design, age: emp.age, salary: emp.salary },
    });
  };

  handleEditChange = (e) => {
    this.setState({ editForm: { ...this.state.editForm, [e.target.name]: e.target.value } });
  };

  submitEdit = () => {
    this.context.updateEmployee(this.state.editingId, this.state.editForm);
    this.setState({ editingId: null });
  };

  render() {
    const { employees, deleteEmployee } = this.context;
    const { selected, editingId, editForm } = this.state;

    return (
      <div style={{ border: "2px solid red", padding: "10px", margin: "10px" }}>
        <h2>Class Component - Employee List</h2>
        {employees.map((emp) => (
          <div
            key={emp.id}
            style={{
              cursor: "pointer",
              backgroundColor: selected === emp.id ? "#ffdede" : "white",
              padding: "5px",
              margin: "5px",
            }}
            onClick={() => this.handleClick(emp.id)}
          >
            {editingId === emp.id ? (
              <div>
                <input name="name" value={editForm.name} onChange={this.handleEditChange} />
                <input name="design" value={editForm.design} onChange={this.handleEditChange} />
                <input name="age" value={editForm.age} onChange={this.handleEditChange} />
                <input name="salary" value={editForm.salary} onChange={this.handleEditChange} />
                <button onClick={this.submitEdit}>Save</button>
              </div>
            ) : (
              <>
                {emp.name} - {emp.design} - {emp.age} - ₹{emp.salary}
                <button onClick={() => this.startEdit(emp)}>Edit</button>
                <button onClick={() => deleteEmployee(emp.id)}>Delete</button>
              </>
            )}
          </div>
        ))}
      </div>
    );
  }
}

export default EmployeeListClass;
