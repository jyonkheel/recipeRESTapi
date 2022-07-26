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
```json
{
    "name":"Potato stew",
    "serving":1,
    "vegetarian": true,
    "ingredientList":[
        {
            "ingredient":"potato"
        }
    ],
    "instructions":"cook 2 potato in bioling water .5 hours"
}
```
* [put] - [localhost:8080/recipe]() - update endpoint
```json
{
    "id":8,
    "name":"Potato stewers",
    "serving":1,
    "vegetarian": true,
    "ingredientList":[
        {
            "ingredient":"potato"
        }
    ],
    "instructions":"cook 2 potato in bioling water 3 hours"
}
```
* [delete] - [localhost:8080/recipe/{id}]() - delete endpoint
* [get] - [localhost:8080/recipe/get/{id}]() - get endpoint
* [get] - [localhost:8080/recipe/listAll]() - list all endpoint
* [post] - [localhost:8080/recipe/addMany]() - allows adding multiple recipes
```json
[
    {
        "name": "Potato stew",
        "serving": 1,
        "vegetarian": true,
        "ingredientList": [
            {
                "ingredient": "potato"
            }
        ],
        "instructions": "cook 2 potato in bioling water .5 hours"
    },
    {
        "name": "Carrot stew",
        "serving": 1,
        "vegetarian": true,
        "ingredientList": [
            {
                "ingredient": "carrot"
            }
        ],
        "instructions": "cook 3 carrot in bioling water .5 hours"
    },
    {
        "name": "Veggie stew",
        "serving": 2,
        "vegetarian": true,
        "ingredientList": [
            {
                "ingredient": "carrot"
            },
            {
                "ingredient": "potato"
            }
        ],
        "instructions": "cook all in bioling water .5 hours"
    },
    {
        "name": "BBQ stick chicken",
        "serving": 4,
        "vegetarian": false,
        "ingredientList": [
            {
                "ingredient": "carrot"
            },
            {
                "ingredient": "potato"
            },
            {
                "ingredient": "chicken thigh"
            }
        ],
        "instructions": "dice all veggies and chicken, put in stick, grill until golden brown"
    }
]
```
* [post] - [localhost:8080/recipe/search]() - search endpoint
  - vegetarian only - if set to true forces results to be vegetarian only
  - number of servings - can be set to zero or null if filter is to be ignored
  - include/exclude ingredients - allows passing a list of ingredients to included or excluded
  - text search of instructions - allows text search of instructions

```json
{
    "vegetarianOnly": false,
    "serving": 0,
    "instructionSearch": "",
    "includeIngredient": [
        {
            "ingredient": "potato"
        }
    ],
    "excludeIngredient": [
        {
            "ingredient": "chicken thigh"
        }
    ]
}
```
