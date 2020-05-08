# Test application
[![Test app](https://img.shields.io/badge/Test-app.svg?style=for-the-badge&logo=android)](https://github.com/evstropovv/Test-app/blob/master/apk/app-debug.apk)

## About
It simply shows list of posts from https://jsonplaceholder.typicode.com/ on first screen. On second screen it shows user detail information and his posts.


<img src="https://github.com/evstropovv/Test-app/blob/master/screenshots/1.jpeg" width="192">  <img src="https://github.com/evstropovv/Test-app/blob/master/screenshots/2.jpeg" width="192">

## Tech stack
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Navigation](https://developer.android.com/guide/navigation) - The library which helps implement navigation in the app
- [Dagger 2](https://dagger.dev/) - Dependency Injection Framework
- [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) - library for building complex screens in a RecyclerView
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Gson](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Architecture
 - Clean Architecture
 - MVVM + MVI (presentation layer)
