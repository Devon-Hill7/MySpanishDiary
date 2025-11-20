import styles from "./ButtonCard.module.css"
import { Link } from "react-router-dom"

function ButtonCard({linkTo, text, size, clickEvent, state, disabled}) {
    
    if (clickEvent) {
        return (
            <button className={`${styles.buttonCard} ${styles[size]} ${disabled ? styles.disabledBtn : ""}`} disabled={disabled} onClick={clickEvent}>{text}</button>
        );
    }

    if (state) {
        return (
            <Link to={linkTo} state={state}>
                <button className={`${styles.buttonCard} ${styles[size]}`}>{text}</button>
            </Link>
        );
    }
    
    return (
    <Link to={linkTo}>
        <button className={`${styles.buttonCard} ${styles[size]}`}>{text}</button>
    </Link>
);
}

export default ButtonCard