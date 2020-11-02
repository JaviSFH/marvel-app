<h1 align="center">Marvel technical test</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
Small android app to technical test using the marvel api. This application just is a exercise to apply a model of clean architecture based on my experience and knowledge probably it could have mistakes.</p>

<p align="center">🌟🌟🌟 &nbsp; If you like this repository check the star 🌟🌟🌟</p>

<p align="center"><img src="/images/preview.gif" align="center" width="250px"/><img src="/images/error.gif" align="center" width="250px"/></p>

</br>

## Technology stack
- [Marvel API](https://developer.marvel.com/documentation/getting_started)
- Write in [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Minimum SDK level 23
- [Hilt - Dagger](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Navigation - Navigation between activities / fragments
  - LiveData - Notify between ViewModel to views.
  - Lifecycle - Dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Layers
    - App (with android sdk)
    - Data (with android sdk)
    - Domain (without android sdk)
  - Repository pattern
- [OkHttp3 & Retrofit2](https://github.com/square/retrofit) - To handle network data and build a REST client.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide) - Loading and caching images.
- Based on [Material-Components](https://github.com/material-components/material-components-android)
- Dark Theme
- [TransformationLayout](https://github.com/skydoves/transformationlayout) - Implementing transformation motion animations.
- [Stetho](http://facebook.github.io/stetho/) - A debug bridge for Android applications
- Unit test
- [Mockito](https://site.mockito.org/) - For building mocks framework to unit tests in Java/Kotlin

## Architecture
This app is based on MVVM architecture and a repository pattern with layers

<img alt="Linkedin" src="/images/architecture.svg"/>

# About myself

<p align="left">
  <a href="https://www.linkedin.com/in/raul-rodriguez-concepcion/"><img alt="Linkedin" src="/images/linkedin.svg" width="50px"/></a>
    <a href="https://github.com/rulogarcillan"><img alt="github" src="/images/github.png" width="50px"/></a>
  <a href="https://play.google.com/store/apps/developer?id=Ra%C3%BAl%20R.&hl=es"><img alt="playstore" src="/images/playstore.png" width="50px"/></a>    
</p>

# License
```xml
Designed and developed by 2020 rulogarcillan (Raúl Rodríguez Concepción)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
