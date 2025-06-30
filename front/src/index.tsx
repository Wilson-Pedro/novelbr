import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { QueryClientProvider, QueryClient } from '@tanstack/react-query';

const queryClient = new QueryClient();

const rootElement = document.getElementById('root');

if (!rootElement) throw new Error('Elemento com id "root" não encontrado');

const root = ReactDOM.createRoot(rootElement as HTMLElement);

root.render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <App />
    </QueryClientProvider>
  </React.StrictMode>
);

reportWebVitals();
