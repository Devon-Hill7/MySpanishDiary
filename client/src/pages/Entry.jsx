import { useLocation } from "react-router-dom";
import { useState } from "react"
import HomeButton from "../components/HomeButton/HomeButton";
import ButtonCard from "../components/ButtonCard/ButtonCard";
import GrammarLessonCard from "../components/GrammarLessonCard/GrammarLessonCard";
import axios from "axios";
function Entry () {
    const location = useLocation();
    const [prompt, setPrompt] = useState(location.state);
    const [entryText, setEntryText] = useState("");
    const [apiResponse, setApiResponse] = useState(null);

    const gradeEntry = async () => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/grade`, {
                text: entryText
            })
            setApiResponse(res.data);
            console.log(res.data);
        } catch (err) {
            console.log(err)
        }
    }


    return (
        <div className='entryPage'>
            <div className="entryPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <div className="writing-section">
                 <span className="promptTitle">{prompt}</span>
                 <textarea className="entryTextArea" placeholder="Escribe tu anotación aquí..." value={entryText}  onChange={(e) => setEntryText(e.target.value)} ></textarea>
            </div>
            <div className="grammarLesson-section">
                <h2 className="grammarTitle">Grammar Errors</h2>
                <div className="grammarLessons-container">
                   { apiResponse? (
                            apiResponse.map((error, index) => (
                            <GrammarLessonCard key={index} title={error.title} incorrectSentence={error.incorrectSentence} suggestions={error.suggestions.slice(0, 3).join(" ")} videoTitle={error.videoTitle}/>
                            ))
                        )
                    :
                        (<span>there are no erros</span>)
                    }
                </div>
                <div className="button-container">
                    <ButtonCard text="Check" size="small" clickEvent={gradeEntry} />
                    <ButtonCard text="Submit" size="small" />
                </div>
            </div>
        </div>
    )
}

export default Entry