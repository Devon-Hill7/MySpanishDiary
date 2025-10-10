import styles from "./TitleLabel.module.css"
function TitleLabel ({title}) {
    return (
        <div className="title-label">
            <h1>{title}</h1>
        </div>
    )
}

export default TitleLabel