# Daily-Expenses-Sharing-Application

A web application to manage and share daily expenses among users. The application supports different split types such as Equal, Exact, and Percentage.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 11 or higher**: The application is built using Java.
- **Maven**: For managing dependencies and building the project.
- **MySQL**: To store application data.
- **Git**: For version control.
- **Spring Boot**: The application uses Spring Boot framework.

## Installation

Follow these steps to set up the project:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/Daily-Expenses-Sharing-Application.git
   cd Daily-Expenses-Sharing-Application
   
2. Configure the Database
spring.datasource.url=jdbc:mysql://localhost:3306/expenseanalyser
spring.datasource.username=root
spring.datasource.password=root_pass_1234

3. Build the Project
   mvn clean install

4. Run the Application
    mvn spring-boot:run
   
5.API Endpoints

Here are some of the key API endpoints:

POST ('/api/expenses/add')

Request body example (JSON):
{
  "description": "Dinner with friends",
  "amount": 120.00,
  "splitType": "Percentage",
  "usernames": ["AK", "MK", "VK"],
  "shares": {
      "AK": 40.0,
      "MK": 35.0,
      "VK": 25.0
  }
}

=========================================================
Response example (JSON):
Get All Expenses 
GET ('/api/expenses') :

[
  {
    "id": 1,
    "description": "Dinner with friends",
    "amount": 120.00,
    "splitType": "Percentage",
    "users": [
      {
        "id": 2,
        "name": "MK",
        "email": "mk@example.com",
        "mobileNumber": "8877688899"
      },
      {
        "id": 3,
        "name": "AK",
        "email": "ak@example.com",
        "mobileNumber": "8870688899"
      },
      {
        "id": 1,
        "name": "VK",
        "email": "vk@example.com",
        "mobileNumber": "8877668899"
      }
    ],
    "shares": {
      "VK": 0.0,
      "AK": 0.0,
      "MK": 0.0
    }
  }
]





