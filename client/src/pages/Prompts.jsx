import { useEffect, useState } from "react"
import ButtonCard from "../components/ButtonCard/ButtonCard"
import TitleLabel from "../components/TitleLabel/TitleLabel"
import HomeButton from "../components/HomeButton/HomeButton";

function Prompts() {
        const [prompt, setPrompt] = useState('')

    const fetchData = async () => {
        try {
            const res = await fetch(`${import.meta.env.VITE_API_BASE_URL}/prompt`);
            const data = await res.json();
            setPrompt(data.text);
            console.log(prompt)
        } catch (err) {
            console.log(err)
            setPrompt("Error fetching prompt");
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
                <ButtonCard text="Give me another!" clickEvent={fetchData} size="medium"/>
                <ButtonCard text="I like this one!" linkTo="/entry" size="medium" state={prompt}/>
            </div>
        </div>
    )
}

export default Prompts