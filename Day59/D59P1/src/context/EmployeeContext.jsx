import { createContext, useState, useEffect } from "react";
import axios from "axios";

export const EmployeeContext = createContext();

export const EmployeeProvider = ({ children }) => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    const res = await axios.get("http://localhost:8080/api/employees");
    setEmployees(res.data);
  };

  const addEmployee = async (employee) => {
    await axios.post("http://localhost:8080/api/employees", employee);
    fetchEmployees();
  };

  const updateEmployee = async (id, updatedEmployee) => {
    await axios.put(`http://localhost:8080/api/employees/${id}`, updatedEmployee);
    fetchEmployees();
  };

  const deleteEmployee = async (id) => {
    await axios.delete(`http://localhost:8080/api/employees/${id}`);
    fetchEmployees();
  };

  return (
    <EmployeeContext.Provider
      value={{ employees, addEmployee, updateEmployee, deleteEmployee }}
    >
      {children}
    </EmployeeContext.Provider>
  );
};
