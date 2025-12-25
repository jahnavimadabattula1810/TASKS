import React, { useState } from 'react';
import { notesAPI } from '../services/api';

const NotesPage = () => {
  const [email, setEmail] = useState('');
  const [loggedInEmail, setLoggedInEmail] = useState(null);
  const [notes, setNotes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [editingNote, setEditingNote] = useState(null);
  const [formData, setFormData] = useState({ title: '', description: '' });

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!email.trim()) {
      setError('Please enter a valid email address');
      return;
    }
    setLoading(true);
    setError(null);
    try {
      const response = await notesAPI.getNotes(email);
      setNotes(response.data);
      setLoggedInEmail(email);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to fetch notes. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleCreateNote = async (e) => {
    e.preventDefault();
    if (!formData.title.trim()) {
      setError('Title is required');
      return;
    }
    setLoading(true);
    setError(null);
    try {
      const newNote = {
        userEmail: loggedInEmail,
        title: formData.title,
        description: formData.description,
      };
      const response = await notesAPI.createNote(newNote);
      setNotes([...notes, response.data]);
      setFormData({ title: '', description: '' });
      setShowForm(false);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create note. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleUpdateNote = async (e) => {
    e.preventDefault();
    if (!formData.title.trim()) {
      setError('Title is required');
      return;
    }
    setLoading(true);
    setError(null);
    try {
      const updatedNote = {
        userEmail: loggedInEmail,
        title: formData.title,
        description: formData.description,
      };
      const response = await notesAPI.updateNote(editingNote.id, updatedNote);
      setNotes(notes.map(note => note.id === editingNote.id ? response.data : note));
      setFormData({ title: '', description: '' });
      setEditingNote(null);
      setShowForm(false);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to update note. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteNote = async (id) => {
    if (!window.confirm('Are you sure you want to delete this note?')) return;
    setLoading(true);
    setError(null);
    try {
      await notesAPI.deleteNote(id);
      setNotes(notes.filter(note => note.id !== id));
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to delete note. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleEditClick = (note) => {
    setEditingNote(note);
    setFormData({ title: note.title, description: note.description || '' });
    setShowForm(true);
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString();
  };

  if (!loggedInEmail) {
    return (
      <div className="max-w-md mx-auto mt-20">
        <div className="bg-white rounded-lg shadow-md p-8">
          <h2 className="text-2xl font-bold mb-6 text-gray-800">Login to View Notes</h2>
          <form onSubmit={handleLogin}>
            <div className="mb-4">
              <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                Email Address
              </label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="Enter your email"
                required
              />
            </div>
            {error && <div className="mb-4 text-red-600 text-sm">{error}</div>}
            <button
              type="submit"
              disabled={loading}
              className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 disabled:opacity-50 transition-colors"
            >
              {loading ? 'Loading...' : 'Login'}
            </button>
          </form>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-3xl font-bold text-gray-800">Notes for {loggedInEmail}</h2>
        <button
          onClick={() => {
            setShowForm(true);
            setEditingNote(null);
            setFormData({ title: '', description: '' });
          }}
          className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition-colors"
        >
          Add Note
        </button>
      </div>

      {error && (
        <div className="mb-4 bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
          {error}
        </div>
      )}

      {showForm && (
        <div className="bg-white rounded-lg shadow-md p-6 mb-6">
          <h3 className="text-xl font-semibold mb-4">
            {editingNote ? 'Edit Note' : 'Create New Note'}
          </h3>
          <form onSubmit={editingNote ? handleUpdateNote : handleCreateNote}>
            <div className="mb-4">
              <label htmlFor="title" className="block text-sm font-medium text-gray-700 mb-2">
                Title *
              </label>
              <input
                type="text"
                id="title"
                value={formData.title}
                onChange={(e) => setFormData({ ...formData, title: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                required
              />
            </div>
            <div className="mb-4">
              <label htmlFor="description" className="block text-sm font-medium text-gray-700 mb-2">
                Description
              </label>
              <textarea
                id="description"
                value={formData.description}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                rows={4}
              />
            </div>
            <div className="flex space-x-2">
              <button
                type="submit"
                disabled={loading}
                className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 disabled:opacity-50 transition-colors"
              >
                {loading ? 'Saving...' : editingNote ? 'Update' : 'Create'}
              </button>
              <button
                type="button"
                onClick={() => {
                  setShowForm(false);
                  setEditingNote(null);
                  setFormData({ title: '', description: '' });
                }}
                className="bg-gray-300 text-gray-700 px-4 py-2 rounded-md hover:bg-gray-400 transition-colors"
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}

      {notes.length === 0 ? (
        <div className="text-center py-12 bg-white rounded-lg shadow-md">
          <p className="text-gray-500">No notes found. Create your first note!</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {notes.map((note) => (
            <div key={note.id} className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
              <div className="flex justify-between items-start mb-3">
                <h3 className="text-xl font-semibold text-gray-800">{note.title}</h3>
                <div className="flex space-x-2">
                  <button
                    onClick={() => handleEditClick(note)}
                    className="text-blue-600 hover:text-blue-800 text-sm"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDeleteNote(note.id)}
                    className="text-red-600 hover:text-red-800 text-sm"
                  >
                    Delete
                  </button>
                </div>
              </div>
              {note.description && (
                <p className="text-gray-600 mb-4 whitespace-pre-wrap">{note.description}</p>
              )}
              <p className="text-xs text-gray-400">
                Last updated: {formatDate(note.lastUpdated)}
              </p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default NotesPage;

