import TitleLabel from "../components/TitleLabel/TitleLabel";
import ButtonCard from "../components/ButtonCard/ButtonCard";
import { useState } from "react";



function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleLogin = async () => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/auth/login`, {
                username: username,
                password: password
            });
                localStorage.setItem("token", res.data);
        } catch (error) {
            if (error.response && error.response.code === 401) {
                setErrorMessage("Login failed. Please check your username and password.");
            }
            else {
                setErrorMessage("An error occurred during login. Please try again.");
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
        <ButtonCard text="Login" onClick={handleLogin} size="small" disabled={false}/>
        <ButtonCard text="Register" size="small" disabled={false}/>
      </div>
      <div className="error-message">{errorMessage}</div>
    </div>
  );
}

export default Login