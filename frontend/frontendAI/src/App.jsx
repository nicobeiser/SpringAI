import React, { useState } from 'react';
import './App.css'; // Opcional, para estilos básicos

function App() {
  const [prompt, setPrompt] = useState('');
  const [response, setResponse] = useState('');
  const [loading, setLoading] = useState(false);
  const API_URL = 'http://localhost:8080/api/groqai/';

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!prompt.trim()) return;

    setLoading(true);
   
    try {
      const url = `${API_URL}${encodeURIComponent(prompt)}`;
      console.log("url: " + url);
      const res = await fetch(url, {
        method: 'GET',
      });
      const data = await res.text();
      setResponse(data); // Ajusta según tu backend
    } catch (error) {
      setResponse('Error al enviar el prompt');
    }
    setLoading(false);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSubmit(e);
    }
  };

  return (
    <div className="App" style={{
      padding: '20px',
      fontFamily: 'Arial, sans-serif',
      backgroundColor: '#f0f8ff', // Fondo azul claro
      minHeight: '100vh',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center'
    }}>
      <h1 style={{ color: '#007bff', marginBottom: '20px' }}>Envía tu Prompt a la IA</h1>
      
      {/* Foto de hamster */}
      <img 
        src="https://images.unsplash.com/photo-1425082661705-1834bfd09dca?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=80" 
        alt="Hamster adorable" 
        style={{ 
          width: '200px', 
          height: '200px', 
          borderRadius: '50%', 
          marginBottom: '20px', 
          border: '5px solid #007bff' 
        }} 
      />
      
      <form onSubmit={handleSubmit} style={{ width: '100%', maxWidth: '600px' }}>
        <textarea
          value={prompt}
          onChange={(e) => setPrompt(e.target.value)}
          onKeyDown={handleKeyDown} // Agregado para enviar con Enter
          placeholder="Escribe tu prompt aquí..."
          rows="5"
          cols="50"
          style={{ 
            width: '100%', 
            marginBottom: '10px', 
            padding: '10px', 
            borderRadius: '10px', 
            border: '2px solid #007bff', 
            fontSize: '16px' 
          }}
        />
        <button 
          type="submit" 
          disabled={loading} 
          style={{ 
            padding: '10px 20px', 
            backgroundColor: loading ? '#6c757d' : '#007bff', // Gris cuando cargando, azul cuando no
            color: 'white', 
            border: 'none', 
            borderRadius: '10px', 
            cursor: loading ? 'not-allowed' : 'pointer', 
            fontSize: '16px' 
          }}
        >
          {loading ? 'Pensando...' : 'Enviar Prompt'}
        </button>
      </form>
      
      {response && (
        <div style={{ 
          marginTop: '20px', 
          padding: '20px', 
          border: '2px solid #007bff', 
          borderRadius: '10px', 
          backgroundColor: '#e6f7ff', // Fondo azul claro para la respuesta
          width: '100%', 
          maxWidth: '600px' 
        }}>
          <h2 style={{ color: '#007bff' }}>Respuesta de la IA:</h2>
          <p style={{ color: '#333' }}>{response}</p>
        </div>
      )}
    </div>
  );
}

export default App;