# Savings Tracker App

## Overview

The Savings Tracker App is an Android application designed to help users track their weekly savings. The app allows users to set a starting amount for their savings plan, automatically updating the savings amount each week. The app integrates Koin for dependency injection, Room for local data persistence, and Jetpack Compose for modern UI development.

## Features

- **Weekly Savings Tracking:** Start with a default savings amount (Ksh. 50) and increase the savings amount weekly.
- **Data Persistence:** Uses Room to store and manage savings data.
- **Dependency Injection:** Utilizes Koin for managing dependencies.
- **Modern UI:** Built with Jetpack Compose for a responsive and intuitive user interface.
- **Customizable Starting Amount:** Allows users to set and update the initial savings amount.

## Architecture

- **Room:** Provides local data storage with `SavingsDatabase`, `SavingsDao`, and `SavingsRepository`.
- **Koin:** Manages dependency injection with `viewModelModule` and `dataModule`.
- **Jetpack Compose:** Handles UI components and navigation.

## Setup

### Dependencies

Ensure you have the following dependencies in your `build.gradle`:

```groovy
// Koin for dependency injection
implementation "io.insert-koin:koin-android:3.3.0"
implementation "io.insert-koin:koin-androidx-viewmodel:3.3.0"

// Room for local data storage
implementation "androidx.room:room-runtime:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"

// Jetpack Compose for UI
implementation "androidx.compose.ui:ui:1.5.0"
implementation "androidx.compose.material:material:1.5.0"
implementation "androidx.navigation:navigation-compose:2.7.0"

// Coroutine support
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2"
