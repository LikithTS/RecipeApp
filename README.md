# Numan

This repository contains the Numan take-home challenge. The app calls a Recipes API and displays a
list of recipes along with their detail view. It also includes filters and a search feature on the
recipes list view.

# Tech Stack

Language/UI: Kotlin, Jetpack Compose, Material 3
Architecture: Clean Architecture, MVVM (unidirectional data flow), Repository pattern
Async: Kotlin Coroutines + Flows
Networking: Retrofit + OkHttp (logging/interceptors)
DI: Koin
Paging: Paging 3
Image Loading: Coil
Testing: JUnit4, Compose UI Test
Lint/Quality: ktlint/detekt (can be a future enhancement)  
Min/Target SDK: 24 / 36
IDE: Android Studio Narwhal | 2025.1.1

# API

Base: https://dummyjson.com/recipes

Used endpoints:
GET /recipes (paging support)
GET /recipes/search?q=<term>(Optional) ?limit=&skip= for pagination

Mapping:
difficulty: normalized to enum (EASY, MEDIUM, HARD)
rating: rounded to one decimal for display

# App Details

1. App will follow modular and clean architecture. Please note we can have a single module
   architecture here, but for future scalability purpose let's stick to modular approach.
2. We can start Multi-module architecture only when project demands it or if we have plans to re-use the modules in other project. 
3. Since list view can we common in most of app. I have created a multi-module. So we can re-use it in other project. 
4. App is having 2 screen.
5. Screen one contains list of recipes and we're using pagination here.
6. Screen two contains details of any one recipe.
7. Features :
   -> Screen one we can filter recipes based on difficulty and rating.
   -> In screen one added a new functionality to search recipes.

# Known Limitations

1. Since the UI was self-designed, it currently follows a basic theme.
2. Design and theming should be more carefully thought out and ideally backed by proper market
   research.
3. I believe design decisions are best made collaboratively — at least 2–3 people should sit
   together and discuss in order to arrive at the best possible outcome.

# Future Enhancements

1. Offline cache with Room.
2. Analytics hooks for complete app.
3. CI pipeline (GitHub Actions) for build + tests + lint

# Changelog (What I’ve implemented beyond the brief)

1. Added search on the list screen
2. Modularized codebase for scalability and faster builds
3. Ensured accessibility with content descriptions & semantics