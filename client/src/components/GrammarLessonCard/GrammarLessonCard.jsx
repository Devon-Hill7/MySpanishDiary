import styles from "./GrammarLessonCard.module.css"
function GrammarLessonCard({title, incorrectSentence, suggestions, videoTitle}) {
  
  if(videoTitle) {
    return (
      <div className={styles['lesson-card']}>
          <h3 className={styles.grammarCardTitle}>{title}</h3>
          <div className={styles.correctionsContainer}>
              <span>{incorrectSentence}</span>
              <span>{suggestions}</span>
          </div>
          <video className={styles.videoLesson} controls>
            <source src={`../../../public/videos/${videoTitle}`} type="video/mp4"/>
          </video>
      </div>
    )
  }

   return (
      <div className={styles['lesson-card']}>
          <h3 className={styles.grammarCardTitle}>{title}</h3>
          <div className={styles.correctionsContainer}>
              <span>{incorrectSentence}</span>
              <span>{suggestions}</span>
          </div>
      </div>
    )

}

export default GrammarLessonCard