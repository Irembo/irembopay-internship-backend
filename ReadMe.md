# Irembo Customer Portal API

Welcome to the Irembo Customer Portal API! This project serves as the backend for the Irembo Customer Portal, providing RESTful API endpoints for managing invoices and payouts. It is built with Spring Boot.

## Project Structure

The project follows a package-by-feature structure to organize the codebase in a modular and maintainable way. Here's an overview of the main folders:

- `src/main/java/com/irembo/portal/`
  - `config/`: Contains configuration classes for the application, such as database configuration and security configuration.
  - `controller/`: Holds REST controller classes responsible for handling HTTP requests and returning responses.
  - `dto/`: Contains Data Transfer Objects (DTOs) used for transferring data between layers or to external systems.
  - `model/`: Contains entity and database model classes representing the application's data structure.
  - `repository/`: Holds database repository classes responsible for data access and persistence.
  - `service/`: Holds service classes implementing the business logic and interacting with repositories and other services.
  - `util/`: Contains utility classes and helper methods.
- `src/test/java/com/yourcompany/yourapplication/`: Contains test classes for the application.

## Prerequisites

Before running the project, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 20 or higher
- Maven (for dependency management)

## Running the Project

Follow these steps to run the Irembo Customer Portal API:

1. Clone the repository to your local machine.
2. Navigate to the project's root directory.
3. Open a terminal or command prompt at the root directory.
4. Build the project using Maven:
   ```shell
   mvn clean install
   ```
5. Once the build is successful, run the project using the Spring Boot Maven plugin:
   ```shell
   mvn spring-boot:run
   ```
6. The API will be accessible at `http://localhost:8080`.

## Configuration Settings

The project requires configuration based on your specific environment or preferences. Here are some configuration options:

- Database Configuration: Configure the database connection properties in the `application.properties` file located in `src/main/resources/`

## Playing with the API
[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/25245671-56096998-e1b3-4a66-a66c-9093588c3781?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D25245671-56096998-e1b3-4a66-a66c-9093588c3781%26entityType%3Dcollection%26workspaceId%3Dfa07919f-0d8c-484a-9a59-9605f3c033c0)

Happy coding! 🚀
