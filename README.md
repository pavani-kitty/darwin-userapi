# Darwin Coding Challenge - userapi - RESTful services implementation

## INTRODUCTION
Creation, Retrieval, Updation & Removal of users.

## Scenario

You are tasked to collect the following details from your users:
- Full name
- Email
- Password
- Phone number
- Department
- Job title

Write an API that can save, delete and display these details.

To create a simple RESTful services which will allow management of users in a system.
RESTful services serve to create a new user, to update, retrieve & remove users from system. 

## Project High level details
RESTful services are implemented to create a new user, query the users, update the users and remove the users from the system.
	
(i)    **User Creation**     - A new user will be created by providing the details like user name, email, phone, job, department.<br/>
(ii)   **User Retrieval**    - Users can be retrieved and relevant links are provided so that business can get to know further actions.<br/>
(iii)  **User Update**       - User can be updated for all the details mentioned above.<br/>
(iv)   **User Removal**      - User can be removed from the System.<br/>

## Running the REST api

POST   http://localhost:8080/users   - Create a new user<br/>
GET    http://localhost:8080/users   - Retrieve all the users in the system<br/>
GET    http://localhost:8080/users/1 - Retrieve a specific user<br/>
PUT    http://localhost:8080/users   - Update user information<br/>
DELETE http://localhost:8080/users/4 - Remove user from the system<br/>

Have implemented Unit tests and all the above end points.

## Deployment
The project can be installed using maven (mvn clean install)

## Github Link 
https://github.com/pavani-kitty/darwin-userapi

## Author
Pavani Oruganti
