# Recipe BE Assesment Documentation
Recipe BE assesment project, if you have questions please email Jonathan Israel E. Serrano (jyonkheel@yahoo.com)

### Setting up
This springboot maven project requires Postgres to be installed locally, you may database settings in application.properties file
* Postman Collection included in repository - please run /product/addMany endpoint to have initial database content. Sample payload already there

### Architectural choices
* Postgres as Database 
* Javax validation and Spring JPA for automatic input validation and creation of database schema
* Standard Junit for Unit and Integration test
* Git actions for simulated CI (will run Unit test on git push to remote)
* Lombok - automatic generator of getters and setters, and object builder

### REST API documentation
There are 7 endpoints available to this project
* [post] - [localhost:8080/recipe]() - add endpoint
* [put] - [localhost:8080/recipe]() - update endpoint
* [delete] - [localhost:8080/recipe/{id}]() - delete endpoint
* [get] - [localhost:8080/recipe/get/{id}]() - get endpoint
* [get] - [localhost:8080/recipe/listAll]() - list all endpoint
* [post] - [localhost:8080/recipe/addMany]() - allows adding multiple recipes
* [post] - [localhost:8080/recipe/search]() - search endpoint