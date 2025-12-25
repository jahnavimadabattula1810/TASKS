import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Navigation = () => {
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path ? 'bg-blue-600 text-white' : 'text-gray-700 hover:bg-gray-100';
  };

  return (
    <nav className="bg-white shadow-md">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center">
            <h1 className="text-xl font-bold text-gray-800">MultiTask Management System</h1>
          </div>
          <div className="flex space-x-1">
            <Link
              to="/notes"
              className={`px-4 py-2 rounded-md transition-colors ${isActive('/notes')}`}
            >
              Notes
            </Link>
            <Link
              to="/support"
              className={`px-4 py-2 rounded-md transition-colors ${isActive('/support')}`}
            >
              Support
            </Link>
            <Link
              to="/residents"
              className={`px-4 py-2 rounded-md transition-colors ${isActive('/residents')}`}
            >
              Residents
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navigation;

