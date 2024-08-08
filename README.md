
# Library Management System API

## Project Description

This Library Management System API is built using Spring Boot and allows librarians to manage books, patrons, and borrowing records efficiently. The system provides RESTful endpoints for managing the library's resources and supports CRUD operations for books and patrons, as well as borrowing and returning books.

## Requirements

### Entities

The system includes the following entities:

- **Book**: Represents a book in the library with attributes such as ID, title, author, publication year, ISBN, etc.
- **Patron**: Represents a library patron with details like ID, name, contact information, etc.
- **Borrowing Record**: Tracks the borrowing and return of books by patrons, including borrowing and return dates.

### API Endpoints

The API provides the following endpoints to interact with the system:

#### Book Management Endpoints
- **GET /api/books**: Retrieve a list of all books.
- **GET /api/books/{id}**: Retrieve details of a specific book by ID.
- **POST /api/books**: Add a new book to the library.
- **PUT /api/books/{id}**: Update an existing book's information.
- **DELETE /api/books/{id}**: Remove a book from the library.

#### Patron Management Endpoints
- **GET /api/patrons**: Retrieve a list of all patrons.
- **GET /api/patrons/{id}**: Retrieve details of a specific patron by ID.
- **POST /api/patrons**: Add a new patron to the system.
- **PUT /api/patrons/{id}**: Update an existing patron's information.
- **DELETE /api/patrons/{id}**: Remove a patron from the system.

#### Borrowing Endpoints
- **POST /api/borrow/{bookId}/patron/{patronId}**: Allow a patron to borrow a book.
- **PUT /api/return/{bookId}/patron/{patronId}**: Record the return of a borrowed book by a patron.

### Data Storage

The system uses MySQL as the relational database to persist data related to books, patrons, and borrowing records. Proper relationships between entities are set up, such as one-to-many between books and borrowing records.

#### MySQL DDL

Below is the Data Definition Language (DDL) used to create the database schema for the project:

```sql
CREATE TABLE book (
    id INTEGER NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(255),
    author VARCHAR(255),
    publication_year INTEGER NOT NULL,
    title VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE borrowing_record (
    record_id INTEGER NOT NULL AUTO_INCREMENT,
    borrow_date DATE,
    return_date DATE,
    book_id INTEGER,
    patron_id INTEGER,
    PRIMARY KEY (record_id)
) ENGINE=InnoDB;

CREATE TABLE patron (
    id INTEGER NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    name VARCHAR(255),
    phone VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

ALTER TABLE borrowing_record ADD CONSTRAINT FK79d6bb8ptx41act3qbt5pxuwm FOREIGN KEY (book_id) REFERENCES book (id);
ALTER TABLE borrowing_record ADD CONSTRAINT FK6e5fdl33e4cvv2jgacf9bpswj FOREIGN KEY (patron_id) REFERENCES patron (id);
```

### Validation and Error Handling

- **Input Validation**: The system validates input data for required fields and proper data formats.
- **Exception Handling**: Proper error messages and HTTP status codes are returned for exceptions and validation errors.

### Transaction Management

The project implements declarative transaction management using Spring's `@Transactional` annotation to ensure data integrity during critical operations.

### Testing

- **Unit Tests**: Comprehensive unit tests are provided to validate the functionality of API endpoints.
- **Testing Frameworks**: The project uses JUnit and Mockito for testing, ensuring high coverage and reliable tests.

## Running the Application

To run the application:

1. Clone the repository.
2. Set up the MySQL database and run the provided DDL scripts to create the necessary tables.
3. Configure the database connection in `application.properties` or `application.yml`.
4. Build the project using Maven or Gradle.
5. Run the application with your preferred IDE or using the command line with `mvn spring-boot:run` or `./gradlew bootRun`.

## Interacting with the API

Use tools like Postman or curl to interact with the API endpoints. Detailed API documentation and example requests can be found in the [API Documentation](#) section.

## Evaluation Criteria

- **Functionality**: Ensure that all CRUD operations for books, patrons, and borrowing records work as expected.
- **Code Quality**: The code is written following best practices for readability, maintainability, and scalability.
- **Error Handling**: The system gracefully handles edge cases and validation errors.
- **Testing**: The project includes unit tests to verify the functionality and reliability of the API.



## Running the Application

### Requirements

- **Java 17**: Ensure you have Java 17 installed.
- **Spring Boot**: The project is built using Spring Boot.
- **MySQL**: The application uses a MySQL database. Ensure you have MySQL installed and running.

### Steps

1. Clone the repository.
2. Set up the MySQL database and run the provided DDL scripts to create the necessary tables.
3. Configure the database connection in `application.properties` or `application.yml`.
4. Build the project using Maven or Gradle.
5. Run the application with your preferred IDE or using the command line with `mvn spring-boot:run` or `./gradlew bootRun`.

---

### When You Try to Upate Book, Patron and BorrowingRecord You can pass the full object or just the field that you want to update.
### If you don't add 'when use Put to update' a specific return Date for Borrowing it will update it auto to take date for today 'LocalDate.now'

---

### Example API Requests using Postman

Here’s how to interact with each API endpoint using Postman, with real data examples:

#### 1. **Add a New Book**

**Endpoint:** `POST /api/books`

- **Request Body:**
  ```json
  {
    "isbn": "978-3-16-148410-0",
    "author": "John Doe",
    "publication_year": 2023,
    "title": "Learning Spring Boot"
  }
  ```

- **Postman Setup:**
  - Method: `POST`
  - URL: `http://localhost:8080/api/books`
  - Body: Select `raw` and `JSON` format, then paste the above JSON.

#### 2. **Retrieve All Books**

**Endpoint:** `GET /api/books`

- **Postman Setup:**
  - Method: `GET`
  - URL: `http://localhost:8080/api/books`
  - No body needed.

#### 3. **Retrieve a Specific Book by ID**

**Endpoint:** `GET /api/books/{id}`

- **Example URL:** `http://localhost:8080/api/books/1`

- **Postman Setup:**
  - Method: `GET`
  - URL: `http://localhost:8080/api/books/1`
  - No body needed.

#### 4. **Update an Existing Book**

**Endpoint:** `PUT /api/books/{id}`

- **Request Body:**
  ```json
  {
    "isbn": "978-3-16-148410-0",
    "author": "Jane Doe",
    "publication_year": 2024,
    "title": "Advanced Spring Boot"
  }
  ```

- **Postman Setup:**
  - Method: `PUT`
  - URL: `http://localhost:8080/api/books/1`
  - Body: Select `raw` and `JSON` format, then paste the above JSON.

#### 5. **Delete a Book**

**Endpoint:** `DELETE /api/books/{id}`

- **Example URL:** `http://localhost:8080/api/books/1`

- **Postman Setup:**
  - Method: `DELETE`
  - URL: `http://localhost:8080/api/books/1`
  - No body needed.

#### 6. **Add a New Patron**

**Endpoint:** `POST /api/patrons`

- **Request Body:**
  ```json
  {
    "email": "jane.doe@example.com",
    "name": "Jane Doe",
    "phone": "123-456-7890"
  }
  ```

- **Postman Setup:**
  - Method: `POST`
  - URL: `http://localhost:8080/api/patrons`
  - Body: Select `raw` and `JSON` format, then paste the above JSON.

#### 7. **Borrow a Book**

**Endpoint:** `POST /api/borrow/{bookId}/patron/{patronId}`

- **Example URL:** `http://localhost:8080/api/borrow/1/patron/1`

- **Postman Setup:**
  - Method: `POST`
  - URL: `http://localhost:8080/api/borrow/1/patron/1`

#### 8. **Return a Book**

**Endpoint:** `PUT /api/return/{bookId}/patron/{patronId}`

- **Example URL:** `http://localhost:8080/api/return/1/patron/1`

- **Postman Setup:**
  - Method: `PUT`
  - URL: `http://localhost:8080/api/return/1/patron/1`

---

This guide provides clear instructions on setting up your project and using the API endpoints with Postman. You can adapt the URL to match your localhost port if it’s different from `8080`.
