import { useEffect, useState } from "react"
import ButtonCard from "../components/ButtonCard/ButtonCard"
import TitleLabel from "../components/TitleLabel/TitleLabel"
import HomeButton from "../components/HomeButton/HomeButton";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Prompts() {
        const [prompt, setPrompt] = useState('')
        const [promptId, setPromptId] = useState(null);

    let navigate = useNavigate();
    
    const fetchData = async () => {
        try {
            const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/prompt`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = res.data;
            setPrompt(data.text);
            setPromptId(data.id);
        } catch (err) {
            if(err.response?.status === 401) {
                navigate("/login");
            }
            else {
                console.log(err)
                setPrompt("Error fetching prompt");
            }
        }

    };

    useEffect(() => {
        fetchData();
    }, []);



    return (
        <div className="main-page">
            <div className="promptsPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <TitleLabel title="Choose a prompt!" />
            <h2 className='promptText'>{prompt}</h2>
            <div className="button-container fixed-pos">
                <ButtonCard text="Give me another!" clickEvent={fetchData} size="medium" disabled={false}/>
                <ButtonCard text="I like this one!" linkTo="/entry" size="medium" state={{id: promptId, prompt: prompt }}/>
            </div>
        </div>
    )
}

export default Prompts