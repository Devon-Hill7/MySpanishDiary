import { useLocation } from "react-router-dom"
import HomeButton from "../components/HomeButton/HomeButton";
function Entry () {
    const prompt = useLocation().state;

    return (
        <div className='entryPage'>
            <div className="entryPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <div className="writing-section">
                 <span className="promptTitle">{prompt}</span>
                 <textarea className="entryTextArea" placeholder="Write your entry here..."></textarea>
            </div>
            <div className="grammarLesson-section">
                <span>this is a test</span>
            </div>
        </div>
    )
}

export default Entry