import ButtonCard from '../ButtonCard/ButtonCard';
import styles from "./SubmitEntryDialogBubble.module.css"
function SubmitEntryDialogBubble ({responseCode, clickEvent}) {
    if(responseCode) {
        if (responseCode >= 200 && responseCode < 300) {
            return (
                <div className={styles.popupOverlay}>
                    <div className={styles.submitEntryDialogBubbleContainer}>
                        <h2>Your entry was saved successfully!</h2>
                        <div className="button-container">
                            <ButtonCard text="Go to Home" size="medium" linkTo="/" />
                            <ButtonCard text="View Entries" size="medium" linkTo="/entries" />
                        </div>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div className={styles.popupOverlay}>
                    <div className={styles.submitEntryDialogBubbleContainer}>
                        <h2>There was a problem saving your Entry. Try submiting again!</h2>
                        <div className="button-container">
                            <ButtonCard text="Go Back" size="small" clickEvent={clickEvent}/>
                        </div>
                    </div>
                </div>
            )}
    } 
    
    return null;

}
export default SubmitEntryDialogBubble;