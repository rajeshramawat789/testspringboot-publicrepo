# Spring Boot Rest API with Cucumber E2E testing

## Description:

This service contains CRUD operation for user accounts with following repository structure:
    
    ├───.github                                                 
    │   └───workflows                                                           # GitHub Workflow
    ├───gradle                                                                  # Gradle Wrapper
    │   └───wrapper
    ├───src
    │   ├───main
    │   │   ├───java
    │   │   │   └───com
    │   │   │       └───test
    │   │   │           ├───advice                                              #Contains Controller Advice to Handle Exception Globally
    │   │   │           ├───constants                                           #Contains Constant Classes
    │   │   │           ├───controller                                          #Controller interface to define API
    │   │   │           │   └───impl                                            #Controller Classes to implement API interface
    │   │   │           ├───dto                                                 #Data transfer Object Classes
    │   │   │           │   ├───request
    │   │   │           │   └───response
    │   │   │           ├───entities                                            #Database Entities
    │   │   │           ├───exception                                           #Exception Helper Classes and Custom Exception
    │   │   │           ├───mapper                                              #Mapper to convert dto to entity and vice versa
    │   │   │           ├───repository                                          #Contains repository interfaces
    │   │   │           └───service                                             #Contains Service definition
    │   │   │               └───impl                                            #Contains Service implementation
    │   │   └───resources                                                       #Contains resources
    │   │       ├───static
    │   │       └───templates
    │   └───test
    │       ├───java
    │       │   └───com
    │       │       └───test
    │       │           └───bdd                                                 #Contains BDD Cucumber Classes
    │       │               └───stepdefs                                        #Contains BDD Cucumber step definations
    │       └───resources
    │           └───features                                                    #Contains BDD Cucumber feature files


## Poject Structure

## Configuration:

## API:
**User Accounts Manage API:**

- Create user account;
- Update user account;
- Delete user account;
- Get user account;
