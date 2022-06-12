# Spring retry example

This is a sample spring-boot project to demonstrate how to use spring retry.

Blog post: 

## Requirements

* Java 8 or higher
* Apache Maven 3.5.0 or higher

## How to Run

- Clone the project
- Run the project using Intellij Idea (Simply run the main class)

## Test Retries

- To test failing all the retries and invoke the recover method, just send a GET request to
  http://localhost:8080/api/v1/inventory/1
- To test retry success case, invoke: http://localhost:8080/api/v1/inventory/1?query=success