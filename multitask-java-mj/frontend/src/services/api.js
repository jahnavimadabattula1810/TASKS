import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Notes API
export const notesAPI = {
  getNotes: (email) => api.get(`/notes/${encodeURIComponent(email)}`),
  createNote: (note) => api.post('/notes', note),
  updateNote: (id, note) => api.put(`/notes/${id}`, note),
  deleteNote: (id) => api.delete(`/notes/${id}`),
};

// Issues API
export const issuesAPI = {
  getIssues: (params) => api.get('/issues', { params }),
  createIssue: (issue) => api.post('/issues', issue),
  getIssueById: (id) => api.get(`/issues/${id}`),
  deleteIssue: (id) => api.delete(`/issues/${id}`),
};

// Residents API
export const residentsAPI = {
  getResidents: (params) => api.get('/residents', { params }),
  createResident: (resident) => api.post('/residents', resident),
  getResidentById: (id) => api.get(`/residents/${id}`),
  updateResident: (id, resident) => api.put(`/residents/${id}`, resident),
  deleteResident: (id) => api.delete(`/residents/${id}`),
};

export default api;

