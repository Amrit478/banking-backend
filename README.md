# **Banking Backend**

## **Overview**

The Banking Backend is a robust RESTful API designed to facilitate various banking operations. This project serves as the backend component of a banking application, providing essential functionalities such as user management, account handling, and transaction processing.

## **Table of Contents**

* Features  
* Technologies Used   
* Getting Started  
  * Prerequisites  
  * Installation  
  * API Endpoints  
* Usage  
* Contributing  
* License  
* Contact

## **Features**

* **User Authentication**: Secure user registration and login.  
* **User**: Create, update, and delete user accounts.  
* **Balance**: Handle deposits, withdrawals, and transfers.  
* **Credit card**: User able to apply credit card, check how much they got approved for and how much increase that.  
* **Loan**: User able to apply loan check how much they got approved for, and they can pay back their loan.

## **Technologies Used**

* **Programming Language**: Java   
* **Framework**: Spring Boot  
* **Database**: MySQL  
* **API Documentation**: Axios(Specifically)

## **Getting Started**

### **Prerequisites**

Before you begin, ensure you have the following installed:

* Java 21.0.2 or higher  
* Maven 3.3 or higher  
* MySQL 8.0.36

### **Installation**

Follow these steps to set up the project locally:

1. **Clone the Repository**:  
2. git clone (https://github.com/Amrit478/banking-backend.git)  
3. **Navigate to the Project Directory**:  
4. 1cd banking-backend  
5. **Build the Project**:  
6. mvn clean package  
7. **Start the Application**:  
8. java \-jar target/banking-backend.jar

					**CONNECT TO DATABASE**  
In order to connect to database you have to go to application properties and than ***add your own username and password for mysql Local***  and than the mysql will be working and I will add screenshot and code there \-   
![][image1]  
And the code goes here   
spring.application.name\=banking-backend

\# Database connection properties  
spring.datasource.url\=jdbc:mysql://localhost:3306/wood  
spring.datasource.username\=root  
spring.datasource.password\=Sukhjinder18@  
spring.datasource.driver-class-name\=com.mysql.cj.jdbc.Driver  
spring.datasource.jpa.hibernate.ddl-auto\=update

### **API Endpoints**

The following are some key API endpoints available in the application:

* **Create User**: POST /user \- Register a new user.  
* @PostMapping("user/create")  
* **Update User**: PUT /user \- Update a new user.  
* @PutMapping("user/update")  
* **Create User**: POST /user \- Delete an existing user.  
* @DeleteMapping("user/delete")  
* **Add Balance**: Post /Balance \- Retrieve a list of balance of accounts.  
* @PostMapping("user/add")  
* **Get Balance**: GET /Balance \- get balance list of balance of accounts.  
* @GetMapping("user/check")  
* **Delete Balance**: deduct /Balance \- Deduct money balance of accounts.  
* @PostMapping("user/add")  
* **The same applies to the loan and other APIs, and I will add screenshots add here as well.**  
* **![][image2]![][image3]**

For a complete list of endpoints and their descriptions, refer to the API documentation written on every method.

## **Usage**

Once the application is running, you can interact with the API using tools like Postman or frontend which I will give up there. Make sure to authenticate users before accessing protected routes.

## **Contributing**

We welcome contributions to enhance the functionality and performance of the Banking Backend. If you would like to contribute, please follow these steps:

1. Fork the repository.  
2. Create a new branch for your feature or bug fix.  
3. Commit your changes and push to your fork.  
4. Submit a pull request detailing your changes.

   ## **License**

This project is licensed under the MIT License. See the LICENSE file for more details.

## **Contact**

For questions, suggestions, or issues, please reach out to Amrit478. Your feedback is valuable and appreciated\!  
