import styles from "./GrammarLessonCard.module.css"

function GrammarLessonCard({title, incorrectSentence, suggestions, videoTitle, errorStart, errorEnd, text, removeCard}) {
  
  const errorSentenceStartIndex = text.indexOf(incorrectSentence);
  const relativeErrorStart = errorStart - errorSentenceStartIndex;
  const relativeErrorEnd = errorEnd - errorSentenceStartIndex;
  const beforeError = incorrectSentence.slice(0, relativeErrorStart);
  const errorText = incorrectSentence.slice(relativeErrorStart, relativeErrorEnd);
  const afterError = incorrectSentence.slice(relativeErrorEnd);
  const enhancedIncorrectSentence = (
    <span>
      {beforeError}
      <span className={styles.errorHighlight}>{errorText}</span>
      {afterError}
    </span>
  );



  if(videoTitle) {
    return (
      <div className={styles['lesson-card']}>
        <button className={styles.grammarLessonCloseBtn} onClick={removeCard}><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path fill="none" stroke="#ffffff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 6L6 18M6 6l12 12"/></svg></button>
          <h3 className={styles.grammarCardTitle}>{title}</h3>
          <div className={styles.correctionsContainer}>
            <div>
              <span className={styles.grammarCardSubtitle}>Incorrect Sentence: </span>
                <span>{enhancedIncorrectSentence}</span>
            </div>
            <div>
              <span className={styles.grammarCardSubtitle}>Suggested Corrections: </span>
                <span>{suggestions}</span>
            </div>
          </div>
          <video className={styles.videoLesson} controls>
            <source src={`../../../public/videos/${videoTitle}`} type="video/mp4"/>
          </video>
      </div>
    )
  }

   return (
      <div className={styles['lesson-card']}>
        <button className={styles.grammarLessonCloseBtn} onClick={removeCard}><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path fill="none" stroke="#ffffff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 6L6 18M6 6l12 12"/></svg></button>
        <h3 className={styles.grammarCardTitle}>{title}</h3>
        <div className={styles.correctionsContainer}>
          <div>
            <span className={styles.grammarCardSubtitle}>Incorrect Sentence: </span>
              <span>{enhancedIncorrectSentence}</span>
          </div>
          <div>
            <span className={styles.grammarCardSubtitle}>Suggested Corrections: </span>
            <span>{suggestions}</span>
          </div>
        </div>
      </div>
    )

}

export default GrammarLessonCard