import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navigation from './components/Navigation';
import NotesPage from './pages/NotesPage';
import SupportPage from './pages/SupportPage';
import ResidentsPage from './pages/ResidentsPage';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <Navigation />
        <main className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<NotesPage />} />
            <Route path="/notes" element={<NotesPage />} />
            <Route path="/support" element={<SupportPage />} />
            <Route path="/residents" element={<ResidentsPage />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;

