import styles from "./ButtonCard.module.css"
import { Link } from "react-router-dom"

function ButtonCard({linkTo, text, size, clickEvent}) {
    
    if (clickEvent) {
        return (
            <button className={`${styles.buttonCard} ${styles[size]}`} onClick={clickEvent}>{text}</button>
        );
    }
    
    return (
    <Link to={linkTo}>
        <button className={`${styles.buttonCard} ${styles[size]}`}>{text}</button>
    </Link>
);
}

export default ButtonCard