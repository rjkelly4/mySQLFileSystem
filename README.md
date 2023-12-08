# MySQL File System

## Spring Boot, Maven, and JPA

### Branch Overview

This branch contains the main framework 
for the backend. The main technologies in the backend stack are Spring boot, 
Maven, and JPA - all are needed to fully understand the structure of the 
backend code. This document is designed to clarify some of the questions 
that may arise regarding the purpose of each java file, as well as the 
overall code structure, for those of us who have limited experience with the 
tech stack.

### Project Structure
The source files for the project (found in mysql-fs-backend/src) are broken 
into five main sections: config, controller, model, repository, and service. 

**Service** serves as the entry point for the application. The details of 
its use are controlled more by Spring Boot than the java file itself; see 
the Sprint Boot section for more relevant details.  
**Config** controls the config context of the application - now, 
conveniently, (mostly) in one place!  
**Controller** works as the dispatcher for requests from the frontend. When 
receiving properly-formatted requests, the controller files will return the 
information that is asked for to the frontend.  
**Model** contains the methods to map mySQL entities into Java objects, 
allowing for their creation, modification, and deletion in a Java context.   
**Repository** is the go-between for the Model and the mySQL databases. 
These files contain the relevant mySQL queries to gather, modify, or delete 
rows from the tables contained in those databases.

### Spring Boot
//TODO

### JPA
JPA (Jakarta Persistence API) controls the project's relational database 
mapping - that is, converting mySQL queries into Java objects that can then 
be modified by the backend. 

//TODO

### Maven
//TODO