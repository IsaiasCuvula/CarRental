[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Hilt](https://img.shields.io/badge/Hilt-4285F4?logo=android&logoColor=white)](https://developer.android.com/training/dependency-injection/hilt-android)
[![Retrofit](https://img.shields.io/badge/Retrofit-48B983?logo=retrofit&logoColor=white)](https://square.github.io/retrofit/)
[![Quarkus](https://img.shields.io/badge/Quarkus-4695EB?logo=quarkus&logoColor=white)](https://quarkus.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Keycloak](https://img.shields.io/badge/Keycloak-4D4D4D?logo=keycloak&logoColor=white)](https://www.keycloak.org/)

# Car Rental Management System

**Car Rental Management System** is an Android application for managing the day-to-day operations of a car rental business, including vehicles, customers, rentals, returns, inspections, and rental history.

The application is built with Kotlin and Jetpack Compose using MVVM, with a repository layer responsible for communication with a REST API. Hilt is used for dependency injection, Retrofit for HTTP communication, and Keycloak for authentication and authorization.

The project is part of a client-server system, with a separate Java/Quarkus backend and PostgreSQL database. The Android application communicates with the backend through a RESTful API and keeps the UI, application logic, and data access separated.

## Features

* User authentication and role-based access
* Vehicle registration and management
* Customer management
* Vehicle availability and search
* Rental creation and management
* Vehicle rental and return workflows
* Vehicle condition and damage reports
* Rental history
* Customer ratings
* Reporting and filtering
* Notifications for relevant rental events

## Architecture

The Android application follows the **MVVM** architecture with a clear separation between the UI, application logic, and data access layers.

```text
Compose UI -> ViewModel -> Repository  -> Retrofit API -> Quarkus REST API
```

<img width="510" height="351" alt="Image" src="https://github.com/user-attachments/assets/7defcdb2-73f6-4e84-86bb-f8bc08ba7928" />

### Android

* **Kotlin**
* **Jetpack Compose**
* **MVVM**
* **Hilt**
* **Retrofit**
* **StateFlow**
* **JUnit / Mockito**

### Backend

* **Java**
* **Quarkus**
* **REST API**
* **Hibernate ORM / Panache**
* **PostgreSQL**
* **Keycloak**

## Project Structure

The Android client is organized around the following responsibilities:

* **UI** - Compose screens and UI components
* **ViewModel** - UI state and application logic
* **Repository** - data access and API communication
* **Models / DTOs** - representation of application and API data
* **Dependency Injection** - Hilt modules for providing repositories, API clients, and other dependencies

The backend follows a layered architecture with separate repository, service, and resource layers.

## System

The project is structured as a client-server application:

```text
Android Application
│
│ REST / JSON
▼
Quarkus API
│
▼
PostgreSQL
```

Authentication and authorization are handled through Keycloak, with access controlled according to the user's role.

## Customer App
<img width="680" height="384" alt="Image" src="https://github.com/user-attachments/assets/76857f41-4666-444d-b3e1-581aba478333" />
<img width="680" height="384" alt="Image" src="https://github.com/user-attachments/assets/d80895e7-8ecd-4cac-945e-9991244d2f61" />
</br>
</br>

## Admin App
<img width="680" height="384" alt="Image" src="https://github.com/user-attachments/assets/3212dfdd-412d-4537-9c36-20cfaf55bf29" />
<img width="680" height="384" alt="Image" src="https://github.com/user-attachments/assets/c9143ca3-6361-4333-b584-7e2007af68c6" />

