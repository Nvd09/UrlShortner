import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ShortUrlForm from './ShortUrlForm';
import RedirectHandler from './RedirectHandler';

function App() {
  return (
    <Router>
      <Routes>
        {/* Main page for URL shortening */}
        <Route path="/" element={<ShortUrlForm />} />

        {/* Redirect handler for shortened URLs */}
        <Route path="/:shortUrl" element={<RedirectHandler />} />
      </Routes>
    </Router>
  );
}

export default App;
