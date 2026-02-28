import TitleLabel from "../components/TitleLabel/TitleLabel"
import ButtonCard from "../components/ButtonCard/ButtonCard"
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
function Home() {
    const [username, setUsername] = useState("");

    let navigate = useNavigate();

    const extractUsernameFromToken = async () => {
      try {
        const token = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/auth/me`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          }
        });
        setUsername(token.data.username);
      } catch (err) {
        if(err.response?.status === 401) {
          navigate("/login");
        }
        else {
          console.log(err);
        }
      }
    }

    const handleLogout = () => {
      localStorage.removeItem("token");
      navigate("/login");
    }

    useEffect(() => {
        extractUsernameFromToken();
    }, []);

    return (
    <div className="main-page">
      <div className="homePage-background"></div>
      <div className="nameplate-container">
        <ButtonCard text="Sign out" clickEvent={handleLogout} size="small"/>
        <h3 className="nameplate">Hi, {username}!</h3>
      </div>
      <TitleLabel title="Home" />
      <div className="button-container">
        <ButtonCard text="Create Entry" linkTo="/prompts" size="large" disabled={false}/>
        <ButtonCard text="My Entries" linkTo="/entries" size="large" disabled={false}/>
      </div>
    </div>
  );
}

export default Home