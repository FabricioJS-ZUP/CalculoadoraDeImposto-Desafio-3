# Tax Calculator API

This project provides an API to manage and calculate taxes.

## API Documentation (Swagger UI)

The API documentation is available at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Use this interface to explore and test the API endpoints.

## Prerequisites

* **Java Development Kit (JDK) installed**
* **IntelliJ IDEA Community Edition (or another compatible IDE) installed**
* **A tool for making HTTP requests (Postman, Thunder Client, etc.)**

## Installation and Usage Guide

1.  **Download and Extract the Project:**
    * Download the project repository to your computer.
    * Extract the ZIP file to a folder of your choice.

2.  **Open the Project in IntelliJ IDEA:**
    * Open IntelliJ IDEA Community Edition.
    * Go to "File" -> "Open" and select the folder where you extracted the project.
    * Wait for IntelliJ IDEA to index the project.

3.  **Run the Application:**
    * In IntelliJ IDEA, click the "Run" button (or press Shift + F10).
    * Wait for the application to start.

4.  **HTTP Request Tool:**
    * Use a tool like Postman or Thunder Client (Visual Studio Code extension) to interact with the API.

5.  **User Registration:**
    * Make a POST request to: `http://localhost:8080/user/register`
    * In the request body, use the following JSON:

    ```json
    {
      "username": "your_username",
      "password": "your_password",
      "role": "ADMIN" or "USER"
    }
    ```

6.  **Authentication Token Retrieval:**
    * Make a POST request to: `http://localhost:8080/user/login`
    * In the request body, use the following JSON:

    ```json
    {
      "username": "your_username",
      "password": "your_password"
    }
    ```

    * The response will contain a JWT token in Bearer format.

7.  **Authorization of Requests:**
    * For all subsequent requests that require authentication, include the token in the "Authorization" header in the following format: `Bearer <your_token>`.

    * Example "Authorization" header:

    ```
    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
    ```

8.  **Tax Operations:**

    * **List All Taxes (GET):**
        * `http://localhost:8080/impostos`

    * **View Tax by ID (GET):**
        * `http://localhost:8080/impostos/{id}` (replace `{id}` with the tax ID)

    * **Create a Tax (POST):**
        * `http://localhost:8080/impostos`
        * Request body:

        ```json
        {
          "name": "Tax Name",
          "description": "Tax Description",
          "rate": 0.18
        }
        ```

    * **Calculate Tax (POST):**
        * `http://localhost:8080/impostos/calculo`
        * Request body:

        ```json
        {
          "id": 2,
          "baseValue": 1000.0
        }
        ```

    * **Delete Tax (DELETE):**
        * `http://localhost:8080/impostos/{id}` (replace `{id}` with the tax ID)

9.  **List Users by Name (GET):**
    * `http://localhost:8080/find/{name}` (replace `{name}` with the username)

## UML Diagram

The project's UML diagram is available at:

[https://app.diagrams.net/#HFabricioJS-ZUP%2FCalculoadoraDeImposto%2Fmain%2FDiagrama%20sem%20nome.drawio#%7B%22pageId%22%3A%22q6dhUPsjB3l89_tuy7Ky%22%7D](https://app.diagrams.net/#HFabricioJS-ZUP%2FCalculoadoraDeImposto%2Fmain%2FDiagrama%20sem%20nome.drawio#%7B%22pageId%22%3A%22q6dhUPsjB3l89_tuy7Ky%22%7D)

## Notes

* Ensure the application is running before making requests.
* The `rate` and `baseValue` values must be numeric.
* Tax IDs are automatically generated.
* The authentication token has a limited validity.
