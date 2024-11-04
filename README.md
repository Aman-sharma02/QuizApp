# Quiz Application

A comprehensive Quiz Application built with Java 21 and Spring Boot. This application enables users to manage and take quizzes efficiently, offering features such as creating questions, retrieving questions by category, creating quizzes, submitting quizzes, and fetching quiz questions.

## Features

1. **Question Management**
   - Create new questions.
   - Retrieve all questions.
   - Retrieve questions by category.

2. **Quiz Management**
   - Create a quiz using selected questions.
   - Submit a quiz with answers.
   - Retrieve questions for a specific quiz.

## Requirements

- **Java**: Version 21
- **Spring Boot**: Spring Boot Framework for backend development
- **Database**: PostgreSQL for data storage

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Aman-sharma02/QuizApp.git
   cd QuizApp
   ```

2. **Build the Project**
   Make sure you have Java 21 installed on your machine. Run the following command to build the project:
   ```bash
   ./mvnw clean install
   ```

3. **Configure the Database**
   - Make sure PostgreSQL is installed and running on your machine.
   - Create a PostgreSQL database for the application.

4. **Update `application.properties`**
   Go to `src/main/resources/application.properties` and update the following with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

   > **Warning**: Ensure that you update the database URL, username, and password before running the application.

5. **Run the Application**
   Use the following command to start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

6. **Access the Application**
   The application will be running at `http://localhost:8080`.

## Endpoints

### Question Endpoints
- **Create Question**: `POST /question/add`
- **Get All Questions**: `GET /question/allQuestions`
- **Get Questions by Category**: `GET /question/category/{categoryName}`

### Quiz Endpoints
- **Create Quiz**: `POST /quiz/create`
- **Submit Quiz**: `POST /quiz/submit/{id}`
- **Get Quiz Questions**: `GET /get/{id}`

## Technologies Used

- **Java 21**: The latest version of Java for a high-performance backend.
- **Spring Boot**: Framework to simplify the development of RESTful APIs.
- **PostgreSQL**: Database to store questions and quiz-related data.

## Contributing

1. Fork the project
2. Create your feature branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -m 'Add YourFeature'`
4. Push to the branch: `git push origin feature/YourFeature`
5. Open a pull request

## Contact

For any queries or issues, please reach out to [amansharma11701@gmail.com](mailto:amansharma11701@gmail.com)
