# News App 
## 📸 Screenshots

| <img src="https://storage.googleapis.com/generativeai-downloads/images/user_provided/8908a8a25c1387d85cf55d045d6cd4f9.jpeg" alt="Headlines Screen" width="250"/> | <img src="https://storage.googleapis.com/generativeai-downloads/images/user_provided/46654b41b590e82c5fa221469e7116b4.jpeg" alt="Favourites Screen" width="250"/> | <img src="https://storage.googleapis.com/generativeai-downloads/images/user_provided/fc4e304f56f14068367d307730e7195d.jpeg" alt="Article Detail Screen" width="250"/> |

## ✨ Features

-   **Top Headlines**: Fetches and displays the latest news headlines from the [NewsAPI.org](https://newsapi.org/) service.
-   **Search Functionality**: Allows users to search for news articles on any topic.
-   **Offline Caching**: All fetched articles are saved to a local Room database, providing offline access.
-   **Save for Later**: Users can save their favorite articles, which are stored permanently in the database.
   


## 📚 Core Libraries

-   **Jetpack:**
    -   `Lifecycle`: (ViewModel, LiveData) For building lifecycle-aware components.
    -   `Navigation Component`: For handling all in-app navigation.
    -   `Room`: For robust local data persistence.
    -   `AppCompat` & `Fragment-KTX`: Core UI and compatibility libraries.
-   **Networking:**
    -   `Retrofit`: For REST API communication.
    -   `OkHttp Logging Interceptor`: For debugging network requests.
-   **Coroutines**: For managing asynchronous tasks.
-   **UI Components**:
    -   `Material Components`: For modern UI elements like `BottomNavigationView`.
    -   `RecyclerView`: For displaying lists of data efficiently.
    -   `View Binding`: For safer interaction with views.


    
