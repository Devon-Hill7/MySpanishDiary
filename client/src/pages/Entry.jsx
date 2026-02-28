import { useLocation } from "react-router-dom";
import { useState } from "react"
import HomeButton from "../components/HomeButton/HomeButton";
import ButtonCard from "../components/ButtonCard/ButtonCard";
import GrammarLessonCard from "../components/GrammarLessonCard/GrammarLessonCard";
import SubmitEntryDialogBubble from "../components/SubmitEntryDialogBubble/SubmitEntryDialogBubble";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Entry () {
    const location = useLocation();
    const [promptId, setPromptId] = useState(location.state.id);
    const [prompt, setPrompt] = useState(location.state.prompt);
    const [entryText, setEntryText] = useState(location.state.text || "");
    const [entryId, setEntryId] = useState(location.state.entryId || null);
    const [apiResponse, setApiResponse] = useState(null);
    const [submitBtnState, setSubmitBtnState] = useState(false);
    const [checkBtnState, setCheckBtnState] = useState(false);
    const [submitEntryResponseCode, setSubmitEntryResponseCode] = useState(null);

    let navigate = useNavigate();

    const gradeEntry = async () => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/grade`, {
                text: entryText
            }, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            if(res.data != null) {
                setApiResponse(res.data);
                determineSubmitBtnState(res.data, entryText, true);
                setCheckBtnState(false);
            }

            console.log(apiResponse);
            console.log(submitBtnState);
        } catch (err) {
             if(err.response?.status === 401) {
                navigate("/login");
            }
            else {
                console.log(err)
            }
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

    const submitEntry = async () => {
        try {
            const userId = await getUserId();
            let res;
            if(entryId) {
                res = await axios.put(`${import.meta.env.VITE_API_BASE_URL}/entries/${entryId}`, {
                promptId: promptId,
                text: entryText,
                date: new Date().toISOString(),
                userId: userId
                }, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`
                    }
                });
            }
            else {
                res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/entries`, {
                promptId: promptId,
                text: entryText,
                date: new Date().toISOString(),
                userId: userId
                }, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`
                    }
                });
            }

            setSubmitEntryResponseCode(res.status);
        } catch (err) {
            console.log("This is the error:",err)
            setSubmitEntryResponseCode(err.response.status);
        }
        
    }

    const getUserId = async () => {
        try {
            const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/auth/getUserId`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            return res.data.userId;
        } catch (err) {
            console.log(err);
        }
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
                    <ButtonCard text="Submit" size="small" clickEvent={submitEntry} disabled={!submitBtnState}/>
                </div>
            </div>
            <SubmitEntryDialogBubble responseCode={submitEntryResponseCode} clickEvent={()=>setSubmitEntryResponseCode(null)}/>
        </div>
    )
}

export default Entry