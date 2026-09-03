\# Sunrise Dental Clinic Management System



A Java-based Dental Clinic Management System developed using NetBeans and MySQL.



\## Main Features



\- User Login and Authentication

\- Patient Management

\- Dentist Management

\- Treatment Management

\- Appointment Management

\- Appointment Details

\- Bill Calculation

\- Reports

\- Help Section

\- User Management

\- Logout and Exit



\## Technologies Used



\- Java

\- Java Swing

\- NetBeans IDE

\- MySQL

\- JDBC

\- Git and GitHub



\## Database Setup



1\. Install MySQL and MySQL Workbench.

2\. Create a database named `dental\_clinic`.

3\. Open the `database.sql` file included in this project.

4\. Execute the SQL script in MySQL Workbench.

5\. Set the Windows environment variable:



&#x20;  `DENTAL\_DB\_PASSWORD`



&#x20;  to the MySQL database password.



\## Running the Project



1\. Open the project in NetBeans.

2\. Make sure MySQL Server is running.

3\. Make sure the `DENTAL\_DB\_PASSWORD` environment variable is configured.

4\. Run the project from NetBeans.

5\. Login using a valid user account.



\## Project Structure



\- `model` - Data model classes

\- `dao` - Database access classes

\- `controller` - Application controllers

\- `view` - Swing user interface forms

\- `test` - Automated test classes



\## Version Control



This project is maintained using Git and GitHub.



Repository:



https://github.com/AnjanaDampitiya/DentalClinicSystem



## Author

## Development Workflow

New documentation and features are developed in a separate feature branch before being merged into the main branch.

## Continuous Integration and Build Delivery

GitHub Actions is used to automate the build process of the Dental Clinic Management System.

The CI workflow:
1. Checks out the source code from GitHub.
2. Sets up Java 21.
3. Builds the NetBeans project using Apache Ant.
4. Uploads the generated JAR file as a GitHub Actions build artifact.

The workflow is triggered when changes are pushed to the `main` branch or feature branches, and when pull requests are created for the `main` branch.

The generated build artifact is named `DentalClinicSystem-build`. This provides an automated method of building and delivering a deployable version of the desktop application after source-code changes.

### CI/CD Workflow

GitHub Push → GitHub Actions → Java 21 Setup → Ant Build → JAR Artifact



Anjana Dampitiya

