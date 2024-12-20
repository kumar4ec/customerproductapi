# CustomerProductAPI

This is a RESTful API built using Spring Boot 3.3.7 to manage customer and product data. It allows CRUD operations on customers and products, with proper Swagger documentation for easy testing. The API also includes logging using Logback for request and response tracking.

## Features

- **Create, Read, Update, Delete (CRUD)** operations for customers and products.
- **Swagger UI** for API documentation and interactive testing.
- **Logging** of API requests and responses.
- **Error Handling** for failed requests.
- **Unit Testing** for critical functionalities.

## Technologies

- **Spring Boot 3.3.7 for building the API.
- **MS SQL or Oracle** or equivalent SQL database for storing data.
- **Swagger** for API documentation and testing.
- **Logback** for logging API requests and responses.
- **JUnit** for unit testing.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java 17 or later** installed on your system.
- **Maven** for building the project.
- **MySQL** or equivalent SQL database running and accessible.

## Setup Instructions

### Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/kumar4ec/customer-product-api.git
cd customer-product-api
Install Dependencies
Install the required dependencies using Maven:

bash
Copy code
mvn install
Configure Database
Update the database connection settings in the application.properties file under src/main/resources:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/customer_product_db
spring.datasource.username=root
spring.datasource.password=password
Ensure you have a MySQL database created and running.

Run the Application
Start the application using Maven:

bash
Copy code
mvn spring-boot:run
The API will be available at http://localhost:8080.

Swagger Documentation
Access the Swagger UI at:

bash
Copy code
http://localhost:8080/swagger-ui/
It provides a user-friendly interface to interact with the API and test its endpoints.

API Endpoints
Customer Endpoints
1. Create a New Customer
POST /api/customers

Request Body:

json
Copy code
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "officeEmail": "john.doe@company.com",
  "personalEmail": "johndoe@gmail.com",
  "familyMembers": "Jane (spouse), Alex (son)",
  "phoneNumber": "123-456-7890",
  "address": "123 Main St, Anytown, USA"
}
Response:

201 Created: Customer created successfully.
500 Internal Server Error: Failed to create customer.
2. Get All Customers
GET /api/customers

Response:

200 OK: A list of all customers.
404 Not Found: No customers found.
3. Update Customer Information
PUT /api/customers/{id}

Request Body:

json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "officeEmail": "john.doe@company.com",
  "personalEmail": "johndoe@gmail.com",
  "familyMembers": "Jane (spouse), Alex (son)",
  "phoneNumber": "123-456-7890",
  "address": "123 Main St, Anytown, USA"
}
Response:

200 OK: Customer updated successfully.
4. Partial Update for Customer
PATCH /api/customers/{id}

Request Body:

json
Copy code
{
  "firstName": "John",
  "phoneNumber": "123-456-7890"
}
Response:

200 OK: Customer partially updated.
Product Endpoints
1. Create a New Product
POST /api/products

Request Body:

json
Copy code
{
  "id": 1,
  "bookTitle": "Effective Java",
  "bookPrice": 45.99,
  "bookQuantity": 100,
  "category": "Programming",
  "publishDate": "2020-05-10"
}
Response:

201 Created: Product created successfully.
500 Internal Server Error: Failed to create product.
2. Get All Products
GET /api/products

Response:

200 OK: A list of all products.
404 Not Found: No products found.
3. Update Product Information
PUT /api/products/{id}

Request Body:

json
Copy code
{
  "id": 1,
  "bookTitle": "Effective Java",
  "bookPrice": 45.99,
  "bookQuantity": 100,
  "category": "Programming",
  "publishDate": "2020-05-10"
}
Response:

200 OK: Product updated successfully.
4. Partial Update for Product
PATCH /api/products/{id}

Request Body:

json
Copy code
{
  "bookQuantity": 150
}
Response:

200 OK: Product partially updated.
Logging
The application uses Logback for logging API requests and responses.

API Request and Response Logging
Logs are generated for every incoming request and outgoing response. These logs help monitor and debug API activities.

Example of a log entry for an API request:

css
Copy code
INFO  [Request] [POST] /api/customers
Request Body: {"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com"}
Example of a log entry for an API response:

css
Copy code
INFO  [Response] [POST] /api/customers
Response Status: 201 Created
Response Body: {"message": "Customer created successfully"}
Logs are stored in logs/application.log.

Error Handling
The API provides basic error handling for failed requests, such as:

400 Bad Request: Invalid input data.
404 Not Found: Resource not found.
500 Internal Server Error: Unexpected errors during the operation.
These errors are logged using Logback and can be viewed in the application logs.

Testing
Unit tests are written using JUnit for the key functionalities, including CRUD operations and error handling. To run the tests:

bash
Copy code
mvn test
Contributing
We welcome contributions to this project! To contribute:

Fork the repository.
Create a new branch.
Make your changes.
Create a pull request describing your changes.
License
This project is licensed under the MIT License.
