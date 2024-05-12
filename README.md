# FlapKap's Vending Machine Backend

Welcome to the FlapKap's Vending Machine Backend repository! This Spring Boot application provides a RESTful API for managing a vending machine. It allows users with different roles to interact with the vending machine - sellers can manage products, while buyers can deposit coins, buy products, and reset deposits.

## Technologies Used

- Java Spring Boot
- H2 Database
- Maven

## Features

- **User Management**: CRUD operations for managing users. Sellers can add, update, and delete users.
- **Product Management**: CRUD operations for managing products. Sellers can add, update, and delete products.
- **Deposit**: Buyers can deposit coins (5, 10, 20, 50, and 100 cent coins) into their vending machine account.
- **Purchase**: Buyers can buy products with the money they've deposited. The API returns the total spent, purchased products, and any change if applicable.
- **Reset Deposit**: Buyers can reset their deposit.

## Getting Started

To get started with the FlapKap's Vending Machine Backend, follow these steps:

- Clone this repository to your local machine:
   ```bash
      git clone https://github.com/osama-mofreh/flapkap.git
   ```
- Navigate to the project directory:
   ```bash
    cd flapkap
    ```
- Run the Spring Boot application using Maven:
  ```bash
    mvn spring-boot:run
    ```
The application will be accessible at http://localhost:8080.

API Endpoints
- **GET /users:** Retrieve all users.

- **GET /users/{id}:** Retrieve a specific user by ID.

- **POST /users:** Create a new user. 

- **PUT /users/{id}:** Update an existing user. 

- **DELETE /users/{id}:** Delete a user. 

- **GET /products:** Retrieve all products.

- **GET /products/{id}:** Retrieve a specific product by ID.

- **POST /products:** Create a new product.

- **PUT /products/{id}:** Update an existing product. 

- **DELETE /products/{id}:** Delete a product. 

- **POST /deposit:** Deposit coins into the vending machine account. (Requires authentication as buyer)

- **POST /buy:** Buy products with deposited money. (Requires authentication as buyer)

- **POST /reset:** Reset deposit. (Requires authentication as buyer)
