# Revature-Project1
# Project Description
The Bank app is a console-based application that simulates banking operations. A customer can apply for an account, view their balance, and make withdrawals and deposits. An employee can aprove or deny accounts and view account balances for their customers.
# Technologies Used
1. Java
2. JDBC
3. PostgresSQL
4. Servlets
5. HTML
6. JavaScript
7. CSS
# Requirements
1. Functionality should reflect the below user stories.
2. Data is stored in a database.
3. A custom stored procedure is called to perform some portion of the functionality.
4. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects.
5. All input is received using the java.util.Scanner class.
6. Log4j is implemented to log events to a file.
7. A minimum of one (1) JUnit test is written to test some functionality.
# User Stories
1. As a user, I can login.
2. As a customer, I can apply for a new bank account with a starting balance.
3. As a customer, I can view the balance of a specific account.
4. As a customer, I can make a withdrawal or deposit to a specific account.
5. As the system, I reject invalid transactions.
    * Ex:
        * A withdrawal that would result in a negative balance.
        * A deposit or withdrawal of negative money.
6. As an employee, I can approve or reject an account.
7. As an employee, I can view a customer's bank accounts.
8. As a user, I can register for a customer account.
9. As a customer, I can post a money transfer to another account.
10. As a customer, I can accept a money transfer from another account.
11. A an employee, I can view a log of all transactions.
# Features
All Requirements and User stories are implemented.
## Future Implementations
Some future implementations include better security and to use the Rest API instead of servlets. I would also like to use Bootstrap next time to make it look more presentable and professional. Due to the time constraint I was unable to have a great UI and therefore it would be my focus if I had more time. 
# Usage
You can use this app to create bank accounts, user accounts and have a verification system where employees will have to approve/reject accounts. As a customer you can do various transacations including withdraw,deposit and transfer money from different bank accounts. 
