# SchoolApp — Android Student Portal

An academic **Android student portal prototype** developed in Java. The app provides student-facing screens for schedules, grades, and an assignment-submission interface. It uses Volley to request JSON data and RecyclerView to display academic records.

> **Project status:** Educational prototype. The login screen currently uses a dummy session/token, and assignment submission is simulated rather than uploaded. End-to-end integration with the separate PHP backend has not been verified.

## Tech stack

- **Language:** Java (Java 11 source compatibility)
- **Platform:** Android SDK; Android Studio; Gradle
- **User interface:** AndroidX, RecyclerView, View Binding
- **Networking:** Volley and HTTP/JSON
- **Local state:** SharedPreferences

## Implemented screens and functionality

- Student home screen and navigation
- Schedule display using RecyclerView and a schedule adapter
- Grades/marks display using RecyclerView and a marks adapter
- Volley-based requests for schedule and marks JSON data
- Local session state using `SessionManager` and SharedPreferences
- Assignment-file selection interface (submission action is currently simulated)

## Project structure

```text
app/src/main/java/com/schoolapp/
├── MainActivity.java            # Login UI (currently dummy login)
├── models/                      # ScheduleItem and MarkItem
├── network/                     # ApiService and VolleySingleton
├── ui/student/                  # Student screens and adapters
└── utils/                       # SessionManager
```

## Run locally

1. Install **Android Studio** and an appropriate Android SDK.
2. Clone the repository:

   ```bash
   git clone https://github.com/abedsabbagh/SchoolApp.git
   ```

3. Open the project folder in Android Studio and allow Gradle to sync.
4. Start an Android emulator or connect an Android device.
5. Run the `app` configuration.

### API configuration

`ApiService.java` currently uses:

```java
public static final String BASE = "http://10.0.2.2/api/";
```

`10.0.2.2` points from the **Android emulator** to the development computer. To retrieve schedules and grades, a compatible local API must be running and its routes and JSON response formats must match the Android client. A physical device requires a reachable address instead. Use HTTPS for any non-local deployment.

**The Android client and PHP repository have not been demonstrated working together end to end; their endpoint paths and authentication flow may require changes.**

## Known limitations

- `MainActivity` currently accepts a demo login and saves a `DUMMY_TOKEN`; it does **not** authenticate users against the PHP backend.
- `SubmitAssignmentActivity` lets the user select a file but currently displays a simulated success message instead of uploading it.
- The current Volley requests do not attach the session token as an authorization header.
- Backend deployment, real user authentication, and full integration testing remain future work.

## Related repository

**PHP/MySQL backend:** [Backend-SchoolApp](https://github.com/abedsabbagh/Backend-SchoolApp)

## Purpose

This project demonstrates Android UI development, activity navigation, RecyclerView adapters, JSON API consumption, and local state handling in Java.
