import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom";
import { createTheme, CssBaseline, ThemeProvider } from "@mui/material";

import BrowserPage from "../BrowserPage/BrowserPage";
import LoginPage from "../LoginPage/LoginPage";

import './App.css'

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/browse/*",
    element: <BrowserPage />,
  },
  {
    path: "*",
    element: <Navigate to="/login" />,
  }
]);

const theme = createTheme({
  palette: {
    mode: "dark"
  }
});

const App = () => {
  return (
    <div style={{ height: '100vh' }}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <RouterProvider router={router} />
      </ThemeProvider>
    </div>
  )
}

export default App;