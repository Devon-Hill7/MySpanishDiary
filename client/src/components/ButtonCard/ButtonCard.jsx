import styles from "./ButtonCard.module.css"

function ButtonCard({text}) {
    return (<button className={styles.buttonCard}>{text}</button>);
}

export default ButtonCard