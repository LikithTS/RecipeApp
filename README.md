# Numan

This repository contains the Numan take-home challenge. The app calls a Recipes API and displays a
list of recipes along with their detail view. It also includes filters and a search feature on the
recipes list view.

# Tech Stack

1. Language/UI: Kotlin, Jetpack Compose, Material 3
2. Architecture: Clean Architecture, MVVM (unidirectional data flow), Repository pattern
3. Async: Kotlin Coroutines + Flows
4. Networking: Retrofit + OkHttp (logging/interceptors)
5. DI: Koin
6. Paging: Paging 3
7. Image Loading: Coil
8. Testing: JUnit4, Compose UI Test
9. Lint/Quality: ktlint/detekt (can be a future enhancement)
10. Min/Target SDK: 24 / 36
11. IDE: Android Studio Narwhal | 2025.1.1

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
2. We can start Multi-module architecture only when project demands it or if we have plans to re-use
   the modules in other project.
3. Since list view can we common in most of app. I have created a multi-module. So we can re-use it
   in other project.
4. App is having 2 screen.
5. Screen one contains list of recipes and we're using pagination here.
6. Screen two contains details of any one recipe.
7. Note: Task says filter by difficulty and rating. As per review of json we have difficulty and
   rating in all the item. So I feel sorting is more important here rather then filter.
8. Features :
   -> Screen one we can sort recipes based on difficulty and rating.
   -> In screen one added a new functionality to search recipes.

# Known Limitations

1. Since the UI was self-designed, it currently follows a basic theme.
2. Design and theming should be more carefully thought out and ideally backed by proper market
   research.
3. I believe design decisions are best made collaboratively — at least 2–3 people should sit
   together and discuss in order to arrive at the best possible outcome.
4. I don't have the sorting icon. So I am sticking to menu item with FAB.

# Future Enhancements

1. Offline cache with Room.
2. Analytics hooks for complete app.
3. CI pipeline (GitHub Actions) for build + tests + lint 
4. After doing some work on api and UI. I see lot of feature or functionality we can add. I will name a few in below points.
5. We can update our card view to show other parameters returned from api.
6. We can have a read more option and animate card expand and collapse.
7. We can search dish by name, cusine, serving, rating and difficulty.
8. We can make cardview even more attractive.
9. We can have sorting functionality for attributes other then one which is defined in app currently

# Changelog (What I’ve implemented beyond the brief)

1. Added search on the list screen
2. Modularized codebase for scalability and faster builds
3. Ensured accessibility with content descriptions & semantics

# Taken more than 1 day

1. Since I am building a full fledged app. It needs time for architecting, custom class implementation. 
2. Most of the class written is re-usable in any project.
