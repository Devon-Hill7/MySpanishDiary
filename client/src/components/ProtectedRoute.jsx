import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children }) {
  const token = localStorage.getItem("token"); // check if user is logged in

  if (!token) {
    // not logged in → redirect to login page
    return <Navigate to="/login" replace />;
  }

  // user is logged in → render the page
  return children;
}