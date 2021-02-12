Azure spring cloud function
===========================


Run the Function locally
------------------------

**Install azure core function** 
[Work with Azure Functions Core Tools # Version 3.x and 2.x](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=windows%2Ccsharp%2Cbash#v2)

**Run Locally**  
```
mvn package
mvn azure-functions:run

#Test
curl http://localhost:7071/api/hello -d "{\"name\":\"Azure\"}"
```

Deploy the Function to Azure Functions
--------------------------------------

**Connect to azure using maven**  
https://github.com/microsoft/azure-maven-plugins/wiki/Authentication

**Deploy**  
```
mvn azure-functions:deploy
```

Todo
----

* Use functional configuration to speed up startup
* access mongodb api from cosmosdb


Reference
---------

* [Example "Hello, world" Spring Boot application that runs on Azure Functions](https://github.com/Azure-Samples/hello-spring-function-azure)
* [Getting started with Spring Cloud Function in Azure](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/getting-started-with-spring-cloud-function-in-azure)
* [Work with Azure Functions Core Tools # Version 3.x and 2.x](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=windows%2Ccsharp%2Cbash#v2)
* [Create project-specific Maven settings](https://stackoverflow.com/questions/43156870/create-project-specific-maven-settings)
* [Quickstart: Create a Java function in Azure from the command line](https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser)
* [Deploy the function project to Azure](https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser)
* [https://github.com/microsoft/azure-maven-plugins/wiki/Authentication](https://github.com/microsoft/azure-maven-plugins/wiki/Authentication#service-principles-in-settingsxml)
* [How to use Spring Data MongoDB API with Azure Cosmos DB](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/configure-spring-data-mongodb-with-cosmos-db)
* [Is it cost-efficient to run Spring Boot on Azure Functions?](https://dev.to/azure/is-it-cost-efficient-to-run-spring-boot-on-azure-functions-1kce)