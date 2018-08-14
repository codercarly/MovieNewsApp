# Movie News App

This Movie News App is more appropriately called the Hollywood News App. This program was a 2-part project as part of the Udacity Android Basics Nanodegree. The program pulls data from the Guardian API and sorts it according to the settings selected by the user.

![image](https://github.com/codercarly/MovieNewsApp/blob/master/ezgif.com-video-to-gif.gif | width = 100px )

# Project Overview (Stage 1):
- This project goal was to design an app that connects to the internet to provide news articles on any topic. 
- The app will give the user regularly-updated news stories by connecting with the Guardian API.

# Code Includes (Stage 1):
- A main screen which displays multiple news stories
- Each list item displays relevant text and info about the story (Title, author, date), images are not required.
- Stories update properly whenever new news data is fetched from the API
- Clicking on a story uses an intent to open the story in the user’s browser.
- The JSON response is parsed correctly and relevant information is stored in the app.
- When there is no data to display, the app shows a default message.
- The app checks whether the devce is connected to the internet and responds appropriately.
- Networking operations are done using a Loader rather than an AsyncTask.
- All variables, methods, and resource ID’s are descriptively named.
- Correct naming conventions
- Adheres to best practices
- No unused variables or methods and no commented out code.
- No unnecessary blank lines

# Project Overview (Stage Two):
- The goal is to add a Settings Screen to the app created in Part 1 which will allow users to narrow down the stories displayed from the feed. 

# Code Includes (Stage Two):
- All requirements from Stage One.
- Settings Activity which allows users to see the value of all the preferences right below the preference name, and when the value is changed, the summary updates immediately.
- Settings Activity is accessed from the Main Activity via a Navigation Drawer or from the toolbar menu.
- App query is narrowed down by the preferences selected by the user in the Settings.
- All Strings are stored in the strings.xml resource file.
