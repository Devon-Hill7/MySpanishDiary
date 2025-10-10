import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Prompts from './pages/Prompts'
import GrammarLesson from './pages/GrammarLesson'
import Entry from './pages/Entry'
import Entries from './pages/Entries'
import './App.css'

//delete when done testing
import api from './api/axios'
function App() {

  //delete when done testing
  const [message, setMessage] = useState('')

  //delete when done testing
  useEffect(() => {
    api.get(`${import.meta.env.VITE_API_BASE_URL}/prompt`)
      .then(res => {
        console.log("Backend response:", res.data);
        setMessage(res.data)})
      .catch(console.error);
  }, []);

  return (
    <main className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/prompts" element={<Prompts />} />
        <Route path="/grammar-lesson" element={<GrammarLesson />} />
        <Route path="/entry" element={<Entry />} />
        <Route path="/entries" element={<Entries />} />
      </Routes>
    </main>
  )
}

export default App
