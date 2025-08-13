# URL Shortener & Analytics Platform Backend

This project is a robust, high-performance backend for a URL shortening service, built with the Spring Boot ecosystem. It goes beyond simple redirection by including a powerful analytics engine to track link performance, making it a comprehensive tool for managing and understanding web traffic.

The entire backend is exposed via a clean REST API, designed to be consumed by any front-end client or testing tool like Postman.

***

## ✨ Features

*   **User Authentication**: Secure user registration and login using JWT (JSON Web Tokens).
*   **URL Shortening**: Generate unique, short codes for any long URL.
*   **High-Throughput Redirection**: Near-instantaneous redirection from short URLs to their original destinations.
*   **Asynchronous Click Tracking**: Captures detailed click data (IP address, user agent, referrer) without slowing down the user redirection.
*   **Analytics Dashboard API**: Endpoints to retrieve performance data for each link, including total clicks.
*   **Scheduled Data Aggregation**: A background job to process raw click data into summarized, easy-to-read analytics (placeholder for full implementation).
*   **Role-Based Authorization**: Ensures users can only manage and view analytics for their own links.

***

## 🛠️ Tech Stack

*   **Framework**: Spring Boot
*   **Language**: Java 17
*   **Security**: Spring Security
*   **Data Persistence**: Spring Data JPA / Hibernate
*   **Database**: PostgreSQL
*   **API**: REST APIs with Spring MVC
*   **Authentication**: JSON Web Tokens (JWT)
*   **Build Tool**: Maven

***


***

## 🚀 API Endpoints

The following are the core endpoints exposed by the application.

### Authentication (`/api/auth`)

| Method | Endpoint                | Access  | Description                  |
| :----- | :---------------------- | :------ | :--------------------------- |
| `POST` | `/api/auth/register`    | Public  | Creates a new user account.  |
| `POST` | `/api/auth/login`       | Public  | Authenticates a user and returns a JWT. |

### URL Management (`/api/links`)

| Method | Endpoint                    | Access  | Description                                 |
| :----- | :-------------------------- | :------ | :------------------------------------------ |
| `POST` | `/api/links`                | Private | Creates a new short URL for the logged-in user. |
| `GET`  | `/api/links/{linkId}/analytics` | Private | Retrieves performance analytics for a specific link. |

### Redirection

| Method | Endpoint      | Access  | Description                                       |
| :----- | :------------ | :------ | :------------------------------------------------ |
| `GET`  | `/{shortCode}` | Public  | Redirects to the original long URL and logs the click. |

***

## ⚙️ Setup and Installation

Follow these steps to get the project up and running on your local machine.

### Prerequisites

*   JDK 17 or later
*   Maven 3.2+
*   PostgreSQL
*   An IDE like IntelliJ IDEA

### 1. Database Setup

1.  Open PostgreSQL (e.g., via `psql` or a GUI tool like pgAdmin).
2.  Create a new database for the project:
    ```
    CREATE DATABASE url_shortener_db;
    ```

### 2. Configure Application

1.  Open the `src/main/resources/application.properties` file.
2.  Update the `spring.datasource` properties with your PostgreSQL username and password.
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener_db
    spring.datasource.username=your_postgres_username
    spring.datasource.password=your_postgres_password
    ```
3.  **Important**: Change the JWT secret key to a strong, unique value.
    ```
    # Generate a strong, random, BASE64-encoded secret for production
    app.jwt.secret=YourSuperSecretKeyForThisProjectThatIsVeryLong
    ```

### 3. Build and Run the Application

1.  Open a terminal in the project's root directory.
2.  Build the project using Maven:
    ```
    mvn clean install
    ```
3.  Run the application:
    ```
    mvn spring-boot:run
    ```
    Alternatively, you can run the `UrlShortenerApplication.java` main class from your IDE. The application will start on `http://localhost:8081`.

***

## 🧪 Testing with Postman

Here is a quick guide to testing the API workflow.

1.  **Register a User**
    *   `POST` request to `http://localhost:8081/api/auth/register`.
    *   Set **Authorization** to `No Auth`.
    *   Body (`raw`, `JSON`):
        ```
        {
          "username": "myuser",
          "email": "myuser@example.com",
          "password": "password123"
        }
        ```

2.  **Login and Get JWT**
    *   `POST` request to `http://localhost:8081/api/auth/login`.
    *   Set **Authorization** to `No Auth`.
    *   Body (`raw`, `JSON`):
        ```
        {
          "username": "myuser",
          "password": "password123"
        }
        ```
    *   **Action**: Copy the `token` from the response body.

3.  **Create a Short URL**
    *   `POST` request to `http://localhost:8081/api/links`.
    *   Set **Authorization** to `Bearer Token` and paste the copied token.
    *   Body (`raw`, `JSON`):
        ```
        {
          "longUrl": "https://github.com/spring-projects/spring-boot"
        }
        ```
    *   **Action**: Note the `id` and `shortCode` (e.g., `b`) from the response.

4.  **Test the Redirection**
    *   Open your web browser and go to `http://localhost:8081/b`. You should be redirected to the GitHub page.

5.  **Get Link Analytics**
    *   `GET` request to `http://localhost:8081/api/links/{id}/analytics` (replace `{id}` with the ID from step 3).
    *   Set **Authorization** to `Bearer Token`.
    *   The `totalClicks` count in the response will update after the background aggregation task runs.


The project follows a standard layered architecture to ensure separation of concerns, making the code clean, scalable, and maintainable.

