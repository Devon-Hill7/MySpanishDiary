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
function App() {

  return (
    <main className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/prompts" element={<Prompts />} />
        <Route path="/entry" element={<Entry />} />
        <Route path="/entries" element={<Entries />} />
      </Routes>
    </main>
  )
}

export default App
