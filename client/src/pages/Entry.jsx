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
    const [submitBtnState, setSubmitBtnState] = useState(false);
    const [checkBtnState, setCheckBtnState] = useState(false);


    const gradeEntry = async () => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/grade`, {
                text: entryText
            })
            if(res.data != null) {
                setApiResponse(res.data);
                determineSubmitBtnState(res.data, entryText, true);
                setCheckBtnState(false);
            }

            console.log(apiResponse);
            console.log(submitBtnState);
        } catch (err) {
            console.log(err)
        }
    }

    const determineSubmitBtnState = (response, text, checked) => {
        const resLength = response ? response.length : 0;
        const textLength = text ? text.length : entryText.length;
        if(textLength > 0 && resLength === 0 && checked) {
            setSubmitBtnState(true);  
        }
        else (setSubmitBtnState(false));
    }


    return (
        <div className='entryPage'>
            <div className="entryPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <div className="writing-section">
                 <span className="promptTitle">{prompt}</span>
                 <textarea 
                    className="entryTextArea" 
                    placeholder="Escribe tu anotación aquí..." 
                    value={entryText}  
                    onChange={(e) => {
                        if(e.target.value.length > 0) {
                            setCheckBtnState(true);
                            determineSubmitBtnState(apiResponse, e.target.value, false);
                        }
                        else {
                            setCheckBtnState(false);  
                            setSubmitBtnState(false);
                        };
                        setEntryText(e.target.value);
                        }} >
                    </textarea>
            </div>
            <div className="grammarLesson-section">
                <h2 className="grammarTitle">Grammar Errors</h2>
                <div className="grammarLessons-container">
                   { (apiResponse && apiResponse.length > 0) ? (
                            apiResponse.map((error, index) => (
                            <GrammarLessonCard 
                            key={index} 
                            title={error.title} 
                            incorrectSentence={error.incorrectSentence} 
                            suggestions={error.suggestions.slice(0, 3).join(", ")} 
                            videoTitle={error.videoTitle} errorStart={error.errorStartPos} 
                            errorEnd={error.errorEndPos} 
                            text={entryText} 
                            removeCard={() => {
                                setApiResponse(prev => {
                                    const updated = prev.filter((_, i) => i !== index)
                                    determineSubmitBtnState(updated, entryText, true);
                                    return updated;
                                });
                                console.log(apiResponse);
                                console.log(submitBtnState);
                                }
                            }
                            />
                            ))
                        )
                    :
                        (<span className="noErrorsText">You have no errors! &#128077;</span>)
                    }
                </div>
                <div className="button-container">
                    <ButtonCard text="Check" size="small" clickEvent={gradeEntry} disabled={!checkBtnState}/>
                    <ButtonCard text="Submit" size="small" clickEvent={gradeEntry} disabled={!submitBtnState}/>
                </div>
            </div>
        </div>
    )
}

export default Entry