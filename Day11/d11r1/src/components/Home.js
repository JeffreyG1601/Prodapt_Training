import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Home.css';

function Home() {
  const navigate = useNavigate();
  const fractals = [
    {
      name: 'Sierpinski Triangle',
      desc: 'A famous fractal formed by recursively removing triangles.',
      route: '/sierpinski-triangle',
    },
    {
      name: 'Mandelbrot Set',
      desc: 'A complex set of points with beautiful, intricate boundaries.',
      route: '/mandelbrot-set',
    },
    {
      name: 'Koch Snowflake',
      desc: 'A fractal curve known for its infinite perimeter.',
      route: '/koch-snowflake',
    },
    {
      name: 'Dragon Curve',
      desc: 'A recursive non-intersecting curve with a distinctive shape.',
      route: '/dragon-curve',
    },
  ];

  return (
    <div className="home-bg">
      <div className="fractal-info">
        <h1>Fractal Patterns</h1>
        <p>
          Fractals are infinitely complex patterns that are self-similar across different scales. They are created by repeating a simple process over and over in an ongoing feedback loop. Explore some famous fractal patterns below:
        </p>
      </div>
      <div className="fractal-cards">
        {fractals.map((f, idx) => (
          <div
            key={f.name}
            className="fractal-card"
            onClick={() => navigate(f.route)}
          >
            <h3>{f.name}</h3>
            <p>{f.desc}</p>
            <div className="fractal-img-placeholder">
              {/* If image exists, show it. Otherwise, show fallback text. */}
              {/* You can replace this logic with a real image check later */}
              <span style={{color:'#888',fontSize:'15px'}}>Sample image for {f.name} will appear here</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;