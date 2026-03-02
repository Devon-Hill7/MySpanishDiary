# My Spanish Diary

A full-stack web app that lets users practice Spanish writing and receive real-time grammar feedback with linked learning resources.

## Demo
Will add screenshots/GIFs soon

## Why I Built This
I wanted a consistent way to practice writing in Spanish, but most language-learning tools focus on reading or multiple-choice exercises rather than free-form writing. Corrections often lacked clear explanations.

This app lets me practice Spanish daily while connecting grammar mistakes to targeted lessons, helping reinforce *why* each correction is needed.

## Features
- User registration and secure sign-in with private, user-specific entries
- Choose a writing prompt and create Spanish diary entries for open-ended practice
- Entries are automatically graded with grammar feedback before submission
- Grammar mistakes are highlighted directly in the sentence, with errors displayed in red
- Each mistake includes up to three suggested corrections
- Grammar errors are linked to embedded YouTube lesson videos based on the specific type of mistake
- Edit and update existing diary entries at any time
- View all past entries on a dedicated My Entries page
- All entries are private and visible only to the submitting user

## Tech Stack
**Frontend:** React, JavaScript, CSS  
**Backend:** Java, Spring Boot, Spring Security  
**Database:** PostgreSQL  
**Auth:** JWT  
**Other:** REST APIs, Docker  
**Grammar Analysis:** LanguageTool

## How It Works

1. **Authentication**
   - When a user logs in, the backend generates a JWT and sends it to the frontend.
   - The frontend stores the token and includes it in the headers of all protected requests.
   - This allows the backend to identify the user and ensure they can only access their own entries.

2. **Creating and Checking an Entry**
   - Users choose a prompt and begin writing a diary entry.
   - Grammar checking occurs when the user clicks the **Check** button.
   - The entry must have no unresolved errors before it can be submitted.

3. **Grammar Analysis**
   - The backend sends the entry text to a third-party grammar analysis tool.
   - Each detected mistake includes:
     - an error code
     - the sentence containing the error
     - the position of the error within that sentence
     - up to three suggested corrections

4. **Mapping Errors to Learning Content**
   - Error codes returned by the grammar tool are mapped to grammar categories stored in the database.
   - Each grammar category is mapped to a specific lesson video.
   - This mapping ensures each mistake is linked to a relevant embedded lesson video.

5. **Highlighting Errors**
   - The grammar tool provides the location of the sentence within the full text and the location of the error within that sentence.
   - Using this information, the app identifies the exact portion of text where the mistake occurred and visually highlights it in red.
   - This provides immediate and intuitive feedback.

6. **Submitting and Saving Entries**
   - Once an entry passes grammar checking, the user can submit it.
   - The backend saves the entry prompt, entry text, and associated user as a single Entry record in the database.
   - Entries are saved only on submission, keeping drafts separate from finalized entries.

7. **Viewing Past Entries**
   - When a user navigates to **My Entries**, the backend retrieves all entries associated with that user’s ID.
   - Only the authenticated user’s entries are returned, ensuring privacy and data isolation.

## Challenges & What I Learned

- **Integrating a grammar analysis dependency into the application flow**  
  While the grammar detection itself was handled by a third-party tool, transforming its raw output into meaningful, user-facing feedback required significant custom logic. I filtered through many possible error codes, selected the ones relevant to my use case, and mapped them to grammar categories and lesson content stored in my database.

- **Designing clean backend responsibilities**  
  Early on, I placed too much logic inside controllers. Refactoring the application to move business logic into service layers helped me better understand separation of concerns and resulted in cleaner, more maintainable code.

- **Learning and implementing JWT authentication**  
  Authentication was not part of my original plan, so adding JWT later required understanding token generation, storage on the client, and validation on protected backend routes. This was my first experience implementing authentication end-to-end in a full-stack application.

- **Adapting the project as the design evolved**  
  Several views and workflows changed as my understanding of the project grew. Learning to adjust the architecture and UI without breaking existing functionality taught me how to iterate on a product while it is already in development.

- **Planning a full-stack application from scratch**  
  This was the first project where I was responsible for the entire lifecycle — from initial idea to database design, API structure, authentication, and UI. I learned the importance of upfront planning, especially for cross-cutting concerns like authentication and data ownership.

- **What I would do differently next time**  
  If I rebuilt this project today, I would spend more time designing the system before writing code, particularly around authentication and data flow, to reduce refactoring later in development.

## Future Improvements

- **Enhance grammar checking** – Add more advanced Spanish grammar rules and custom checks to provide deeper feedback for complex mistakes.  
- **Lesson library access** – Allow users to browse and watch all lesson videos independently, not just when a mistake occurs.  
- **Mistake pattern tracking** – Show users their most common grammar mistakes over time to help identify areas for improvement.  
- **Autosave entries** – Save drafts automatically so users don’t lose progress if the app is closed accidentally.  
- **Testing & edge case handling** – Add unit and integration tests to catch potential errors and improve reliability.
