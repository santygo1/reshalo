import { useState } from "react";

import "./App.css";
import { Form } from "./components/Form";
import { Solution } from "./components/Solution";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <div className="main">
        <Form />
        <Solution />
      </div>
    </QueryClientProvider>
  );
}

export default App;
