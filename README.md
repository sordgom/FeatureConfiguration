# Feature Configuration

## Choices

- This project is implemented as a classic MVC application in Spring Boot.
- H2, a lightweight and embedded database, is chosen for its simplicity and ease of use during development.

## System design decisions
- Create 2 entities: `Feature` and `FeatureUser(Whitelist)`
- Every feature is created independently of users. 
- `isEnabled` field in `Feature` Entity enables/disables the feature for everyone.

## Tradeoffs

- **Updating Existing Features and Emails**: The `POST /feature` endpoint is designed to update existing features and emails. This approach allows for efficient feature management without duplicating entries.

- **Access Response**: The `GET /feature` endpoint responds with canAccess: true or canAccess: false based on the user's email and feature name.

- **Handling Errors**: In the case of a typical `500 Internal Server` Error, the API returns a `304` error as per the instructions. Alternatively, it may return `canAccess: false` in case of errors.

## Instructions
To run the Spring Boot application, open the main application file and execute it as a **Java Application**.

## Database: H2-Console
An embedded H2 database is used in this project. Access the H2 console [here](http://localhost:8080/h2-console).