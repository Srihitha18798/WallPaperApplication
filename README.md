# WallPaperApp

## App description

An android wallpaper App which displayes free images of different categories from [PixaBay API](https://pixabay.com/api/docs/) which can be downloaded and use as wallpaper for mobile phones.

## Features

* Display large number of images.
* Different categories of wallpaper images can be shown.
* Download your favorite wallpapers.
* Set your favorite image as wallpaper of your mobile phones

## Getting started
Clone the repository and import the project in Android Studio.
You will also need to provide a key to access the Pixabay API, after getting the key, just put it in the `MainRepository.xml` file in the place of `key`

## Dependencies
The depdendencies for the project are described in the Gradle script `build.gradle` of the app, just sync the project in Android Studio and you are ready to go :)

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Kotlin's way of writing asynchronous, non-blocking code
- [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView) - A flexible view for providing a limited window into a large data set.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying data changes
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
- [Dagger](https://developer.android.com/training/dependency-injection/dagger-android) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Room](https://developer.android.com/topic/libraries/architecture/room) - A database library providing an abstraction layer over SQLite 
- [Glide](https://github.com/bumptech/glide) - Open source media management and image loading framework for Android
- [Picasso](https://github.com/square/picasso)-Picasso allows for hassle-free image loading in the application

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.


![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Screenshots

<p float="middle">
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/1.png' />  
     <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/2.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/3.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/4.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/5.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/6.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/7.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/8.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/9.png' />    
    <img width="250px" src='https://github.com/Srihitha18798/WallPaperApplication/blob/master/app/src/main/assets/10.png' />    

  
</p>



# License

    Copyright 2022 Srihitha Tadiparthi

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


