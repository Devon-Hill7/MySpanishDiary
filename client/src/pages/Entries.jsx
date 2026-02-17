import { useEffect, useState } from "react"
import ButtonCard from "../components/ButtonCard/ButtonCard"
import TitleLabel from "../components/TitleLabel/TitleLabel"
import HomeButton from "../components/HomeButton/HomeButton";


function Entries() {
    const [entries, setEntries] = useState([])

    const fetchEntries = async () => {
        try {
            const res = await fetch(`${import.meta.env.VITE_API_BASE_URL}/entries`);
            const data = await res.json();
            setEntries(data);
        } catch (err) {
            console.log(err)
            setEntries("Error fetching entries");
        }

    };

    useEffect(() => {
        fetchEntries();
    }, []);

    return (
        <div className="main-page">
            <div className="entriesPage-background"></div>
            <div className="homeBtn-container"><HomeButton /></div>
            <TitleLabel title="My Entries" />
            <div className="entries-list">
                {Array.isArray(entries) && entries.length > 0 ? (

                    entries.map((entry, index) => (<ButtonCard key={index} text={entry.text.length > 50 ? entry.text.substring(0, 50) + "..." : entry.text} size="medium" entriesBtn={true}></ButtonCard>))
                ) : (
                    <p>No entries found.</p>
                )}
            </div>
        </div>
    )
}

export default Entries