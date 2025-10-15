// src/App.jsx
import EmployeeListFunc from "./components/EmployeeListFunc";
import EmployeeFormFunc from "./components/EmployeeFormFunc";
import EmployeeListClass from "./components/EmployeeListClass";
import EmployeeFormClass from "./components/EmployeeFormClass";
import { EmployeeProvider } from "./context/EmployeeContext";
import "./App.css";
function App() {
  return (
    <EmployeeProvider>
      <div style={{ textAlign: "center" }}>
        <h1>Employee Management Demo</h1>
        <EmployeeFormFunc />
        <EmployeeListFunc />
        <EmployeeFormClass />
        <EmployeeListClass />
      </div>
    </EmployeeProvider>
  );
}

export default App;
