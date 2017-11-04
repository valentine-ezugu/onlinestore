# onlinestore
Admin and BookStore

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
 

# Maven - Dependency Management, 
how to install
Web Server (one of them) 
 
# Directory 
cd IdeaProject/onlinestore/
mvn clean install

 # Run Each Module
cd IdeaProject/onlinestore/admin
mvn spring-boot:run


admin is running on http://localhost:8081/adminportal

username and password = admin and admin

cd IdeaProject/onlinestore/bookstore
mvn spring-boot:run

boostore is running on http://localhost:8080/

username and password = V and A
 
# Built With
Spring-boot - The web framework used
Spring starter Security  - The web security framework used
Spring LDAP for security test
Maven(3.5) - Dependency Management
Hibernate(5.2) - JPA Provider
MySQL(5.7) - DBMS
H2(1.4) - DBMS for testing
Thymeleaf - Template Engine

# Author
Valentine Ezugu - Valentine-Ezugu

# TODOs
add more tests

