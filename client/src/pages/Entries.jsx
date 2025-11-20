import TitleLabel from "../components/TitleLabel/TitleLabel"
import HomeButton from "../components/HomeButton/HomeButton";
function Entries() {
    return (
        <div className="main-page">
            <div className="entriesPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <TitleLabel title="My Entries" />
        </div>
    )
}

export default Entries