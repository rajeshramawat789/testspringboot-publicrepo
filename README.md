# Spring Boot Rest API with Cucumber E2E testing

## Description:

This service contains CRUD operation for user accounts Along with Cucumber BDD E2E testing and unit testing, having following repository structure:

    ├───.github                                                 
    │   └───workflows                                   # GitHub Workflow
    ├───gradle                                          # Gradle Wrapper
    │   └───wrapper
    ├───src
    │   ├───main
    │   │   ├───java
    │   │   │   └───com
    │   │   │       └───test
    │   │   │           ├───advice                      #Contains Controller Advice to Handle Exception Globally
    │   │   │           ├───configuration
    │   │   │           ├───constants                   #Contains Constant Classes
    │   │   │           ├───controller                  #Controller interface to define API
    │   │   │           │   └───impl                    #Controller Classes to implement API interface
    │   │   │           ├───dto                         #Data transfer Object Classes
    │   │   │           │   ├───request
    │   │   │           │   └───response
    │   │   │           ├───entities                    #Database Entities Classes
    │   │   │           ├───exception                   #Exception Helper Classes and Custom Exception
    │   │   │           ├───mapper                      #Mapper to convert dto to entity and vice versa
    │   │   │           ├───repository                  #Contains repository interfaces
    │   │   │           └───service                     #Contains Service definition
    │   │   │               └───impl                    #Contains Service implementation
    │   │   └───resources                               #Contains static resources
    │   │       ├───static
    │   │       └───templates
    │   └───test
    │       ├───java
    │       │   └───com
    │       │       └───test
    │       │           └───bdd                         #Contains BDD Cucumber Classes
    │       │               └───stepdefs                #Contains BDD Cucumber step definations
    │       │           └───service                     #Unit test cases for service      
    │       └───resources
    │           └───features                            #Contains BDD Cucumber feature files
        
## API:
**User Account Managed APIs:**

- Create user account;
- Update user account;
- Delete user account;
- Get user account;

## Tech Stacks :
- Framework : Spring Boot
- Database : H2
- E2E Testing : Cucumber Spring Boot Implementation
- Test : Junit with Mockito
- Build Tool :  Gradle
- CI : Github CI
- Tools : Docker (For creating Images), Github (version control tool) 

## Test Approach
- Used Cucumber for E2E API testing. Using spring boot with cucumber dependencies. 
- Used Junit Mockito for Unit testing of services. 

## Github CI : 
Used Github Actions for Continuous Integration with following steps.
- Create Action on push and pull request on merge request. 
- Create build.
- Run E2E test cases and unit test cases.
- Prepare build.
- Push build to docker hub. 