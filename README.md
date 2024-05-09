# Bank System Application

## Overview
This console application simulates a banking system that manages user accounts and transactions with support for different transaction fees. Users can perform various banking operations such as deposits, withdrawals, and transfers between accounts.

## Features
- Create and manage user accounts.
- Deposit and withdraw money.
- Transfer money between accounts with flat or percentage-based fees.
- View transaction history and account balances.
- List all bank accounts and view total transaction fees.

## Technologies Used
- Java
- Spring Boot (for the RESTful API version)
- PostgreSQL
- Maven (for building the project)

## Prerequisites
- JDK 11 or newer
- Maven 3.6 or newer (for building and running the project)
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

## Setup and Running the Application
### Console Application
1. Clone the repository:
2. Navigate to the project directory:
3. Compile and run the application:

### RESTful API Version (if applicable)
1. To run the Spring Boot version, navigate to the root directory of the project and run:
2. Access the API through `http://localhost:8080/`.

## API Endpoints
- `POST /api/accounts`: Create a new account.
- `GET /api/accounts/{id}`: Get an account by ID.
- `POST /api/accounts/{id}/deposit`: Deposit money to an account.
- `POST /api/accounts/{id}/withdraw`: Withdraw money from an account.
- Other endpoints as implemented.

## Database Configuration
This application uses PostgreSQL as its data storage solution. Below are the configuration details and setup instructions:

### PostgreSQL Setup
Ensure that you have PostgreSQL installed and running on your local machine or server. Create a database named `Accounts` that the application will use.

### Application Configuration
Update the `application.properties` file in the Spring Boot project with the following configurations to connect to your PostgreSQL database:

properties
# PostgreSQL DataSource configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/Accounts
spring.datasource.username=postgres
spring.datasource.password=1011
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server configuration
server.port=8095
server.error.include-message=always



## API Testing

The API has been extensively tested using Postman to ensure functionality and reliability across all endpoints. These tests cover various scenarios including CRUD operations, error handling, and validation checks to guarantee that the API behaves as expected under different conditions.

### How to Test with Postman

To facilitate easy testing, here are steps to follow using Postman:

1. **Install Postman**: Download and install Postman from [Postman's official website](https://www.postman.com/downloads/).

2. **Import the Collection**:
   - Download the Postman collection from [insert link to your Postman collection here if available].
   - Open Postman and import the downloaded collection file.

3. **Run the Server**: Make sure your application is running. Refer to the 'Setup and Running' section for instructions on how to start the application.

4. **Execute Requests**:
   - Select an API request from the imported collection.
   - Hit the 'Send' button to execute the request.
   - Review the response returned by the API to validate its correctness.

### Automated Testing

In addition to manual testing with Postman, the application includes a suite of automated unit and integration tests designed to verify the functionality and integrity of the API. These can be run using the following command:

## Testing
bash
mvn test

