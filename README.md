# Student Management Web Application using Servlets

This project is a web-based implementation of a Student Management System that allows the user to manage student data, such as adding, updating, and deleting students.

<h1 style='color:red'> MVC Diagram </h1> 
</p><img width="745" alt="StudentManagemetSystemMVC" src="https://user-images.githubusercontent.com/108913933/212731223-8c264a21-9d98-4d20-bedf-f69b7d812bd7.png">

## Technologies Used
- Java Servlets
- JSP
- HTML/CSS
- JDBC
- MySQL
- Tomcat Server 9.0

## Features
- Add, update, delete and view student data
- Supports validation of user inputs
- Provides search functionality to search for students based on various parameters

## Getting Started
1. Clone the repository to your local machine
2. Open the project in your IDE
3. Build the project using Maven
4. Import the database schema from the `database.sql` file
5. Update the `application.properties` file with your database credentials
6. Run the application on a Tomcat server

## Application Architecture
The application follows a Model-View-Controller (MVC) architecture with a layered approach for improved code maintainability and readability.

### Model
- The model package contains the `Student` class which represents the entity for a student.

### View
- The view package contains the JSP files which display the UI for the user.

### Controller
- The controller package contains the servlets which handle the user requests and update the model accordingly.

## Credits
This project was created by [Your Name] as a part of [Course Name] at [Institution Name].
