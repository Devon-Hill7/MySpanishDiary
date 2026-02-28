import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Prompts from './pages/Prompts'
import Entry from './pages/Entry'
import Entries from './pages/Entries'
import './App.css'
import ProtectedRoute from './components/ProtectedRoute'
function App() {

  return (
    <main className="App">
      <Routes>
        <Route path="/" element={<ProtectedRoute><Home /></ProtectedRoute>} />
        <Route path="/login" element={<Login />} />
        <Route path="/prompts" element={<ProtectedRoute><Prompts /></ProtectedRoute>} />
        <Route path="/entry" element={<ProtectedRoute><Entry /></ProtectedRoute>} />
        <Route path="/entries" element={<ProtectedRoute><Entries /></ProtectedRoute>} />
      </Routes>
    </main>
  )
}

export default App
