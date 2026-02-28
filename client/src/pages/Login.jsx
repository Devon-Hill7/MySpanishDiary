import TitleLabel from "../components/TitleLabel/TitleLabel";
import ButtonCard from "../components/ButtonCard/ButtonCard";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    let navigate = useNavigate();
    const handleLogin = async () => {
        try {

            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/auth/login`, {
                username: username,
                password: password
            });

                localStorage.setItem("token", res.data.token);
                navigate("/");
        } catch (error) {
            if (error.response?.status === 401) {
                setErrorMessage("Login failed. Please check your username and password.");
                console.log(error);
            }
            else {
                setErrorMessage("An error occurred during login. Please try again.");
                console.log(error);
            } 
        }
    }

    const handleRegister = async () => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/auth/register`, {
                username: username,
                password: password
            });
            setErrorMessage("Registration successful! You can now log in.");
        } catch (error) {
            if (error.response?.status === 400) {
                setErrorMessage("Registration failed. Username may already be taken.");
                console.log(error);
            }
            else {
                setErrorMessage("An error occurred during registration. Please try again.");
                console.log(error);
            }
        }
    }

    return (
    <div className="main-page">
      <div className="loginPage-background"></div>
      <TitleLabel title="My Spanish Diary" />
      <input
        className="login-input"
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
      />

      <input
        className="login-input"
        type="text"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
      />
      <div className="button-container">
        <ButtonCard text="Login" clickEvent={handleLogin} size="small" disabled={false}/>
        <ButtonCard text="Register" clickEvent={handleRegister} size="small" disabled={false}/>
      </div>
      <div className="error-message">{errorMessage}</div>
    </div>
  );
}

export default Login