import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React from 'react';
import './App.css';
import Home from './components/Home';
import Navbar from './components/Header';
import Footer from './components/Footer';
import About from './components/About';
import Login from './components/Login'; // Assuming you have a Login component
import Contact from './components/Contact'; // Assuming you have a Contact component
import Signup from './components/Signup'; // Assuming you have a Signup component
function App() {
  return (
    <Router>
      <div className="App">
        <Navbar/>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
        <Footer/>
      </div>
    </Router>
  );
}

export default App;
