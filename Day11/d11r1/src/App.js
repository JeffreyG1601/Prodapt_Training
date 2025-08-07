import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React from 'react';
import './App.css';
import Home from './components/Home';
import Navbar from './components/Header';
import Footer from './components/Footer';
import About from './components/About';
import Login from './components/Login';
import Contact from './components/Contact';
import Signup from './components/Signup';
import SierpinskiTriangle from './components/SierpinskiTriangle';
import MandelbrotSet from './components/MandelbrotSet';
import KochSnowflake from './components/KochSnowflake';
import DragonCurve from './components/DragonCurve';
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
          <Route path="/sierpinski-triangle" element={<SierpinskiTriangle />} />
          <Route path="/mandelbrot-set" element={<MandelbrotSet />} />
          <Route path="/koch-snowflake" element={<KochSnowflake />} />
          <Route path="/dragon-curve" element={<DragonCurve />} />
        </Routes>
        <Footer/>
      </div>
    </Router>
  );
}

export default App;
