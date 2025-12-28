# News App 
## 📸 Screenshots

| <img src="WhatsApp Image 2025-12-05 at 19.08.12_43b59610.jpg" alt="Headlines Screen" width="200"/> | <img src="WhatsApp Image 2025-12-05 at 19.08.12_ab57f74f.jpg" alt="Favourites Screen" width="200"/> | <img src="WhatsApp Image 2025-12-05 at 19.08.13_5a42982e.jpg" alt="Article Detail Screen" width="200"/> |<img src="WhatsApp Image 2025-12-05 at 19.08.13_f383d80b.jpg" alt="Headlines Screen" width="200"/>

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


    
