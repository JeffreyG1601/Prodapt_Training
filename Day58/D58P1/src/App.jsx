import { NavLink, Routes, Route } from "react-router-dom";
import Home from "./components/Home.jsx";
import About from "./components/About.jsx";
import Services from "./components/Services.jsx";
import Gallery from "./components/Gallery.jsx";
import Contact from "./components/Contact.jsx";
import EmployeeList from "./components/EmployeeList.jsx";
import AddEmployee from "./components/AddEmployee.jsx";
import "./styles.css";

export default function App() {
  return (
    <div className="app">
      <nav className="nav">
        <h1 className="brand">My React App</h1>
        <ul>
          <li><NavLink to="/" end>Home</NavLink></li>
          <li><NavLink to="/about">About</NavLink></li>
          <li><NavLink to="/services">Services</NavLink></li>
          <li><NavLink to="/gallery">Gallery</NavLink></li>
          <li><NavLink to="/contact">Contact</NavLink></li>
          <li><NavLink to="/employees">Employees</NavLink></li>
          <li><NavLink to="/add-employee">Add Employee</NavLink></li>
        </ul>
      </nav>

      <main className="content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/services" element={<Services />} />
          <Route path="/gallery" element={<Gallery />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/employees" element={<EmployeeList />} />
          <Route path="/add-employee" element={<AddEmployee />} />
          <Route path="*" element={<h2>Page Not Found</h2>} />
        </Routes>
      </main>
    </div>
  );
}
