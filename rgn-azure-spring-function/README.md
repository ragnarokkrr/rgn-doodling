Azure spring cloud function
===========================

Azure function implemented using Spring Cloud Functions and accessing Cosmos DB with Mongodb API.


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

curl -XPOST http://localhost:7071/api/customerSave -d "{\"firstName\":\"John\", \"lastName\":\"Connor\"}"

curl -XGET http://localhost:7071/api/customerGet?id=602a8734dda70b5859bf2982

```

Deploy the Function to Azure Functions
--------------------------------------

**Connect to azure using maven**  
https://github.com/microsoft/azure-maven-plugins/wiki/Authentication


**Run locally**  
```
mvn clean package
mvn azure-functions:run
```

**Deploy**  
```
mvn azure-functions:deploy
```

Todo
----

- [ ] Use spring functional configuration to speed up startup
- [x] access mongodb api from cosmosdb
- [ ] embed gatling tests
- [ ] local testing with cosmos simulator
- [ ] deploy native graalvm function
- [ ] configure azure functions proxies to create a REST API from individual functions
- [ ] function access to resources (k8s, web services, db's) from other azure virtual networks/regions and vice-versa
- [ ] manage cosmos db creation using terrafom


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
* [Provisioning Azure Cosmos DB resources with Terraform](https://medium.com/swlh/provisioning-azure-cosmos-db-resources-with-terraform-60c25b7bd52b)
* [Deploy Spring Boot on ServerLess Architecure Using Azure Function and Spring Cloud Function](https://github.com/sagarmal624/SpringCloudFunctionWithAzure)
* [Data modeling in Azure Cosmos DB](https://docs.microsoft.com/en-us/azure/cosmos-db/modeling-data)
* [Java in Azure Functions](https://dzone.com/articles/azure-functions-in-java)
* [Azure Functions Java developer guide](https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference-java?tabs=bash%2Cconsumption)
* [Work with Azure Functions Proxies](https://docs.microsoft.com/en-us/azure/azure-functions/functions-proxies)
* [Introduction to Azure Functions Proxies (preview)](https://github.com/mattchenderson/azure-functions-proxies-intro/blob/master/README.md)
* [Azure Function Proxies samples](https://github.com/Azure-Samples/functions-proxies)
* [Azure Function proxy support](https://gist.github.com/sjkp/890d94b958965898e45e69bf199c88d4)
* [Azure Functions Proxies in Action](https://chsakell.com/2019/02/03/azure-functions-proxies-in-action/)
