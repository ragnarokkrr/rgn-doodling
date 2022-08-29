O'Reilly Learning - Event-Driven with Spring Cloud Function and Spring Cloud Stream
===================================================================================


Gists
-----

### Spring Cloud Function web

```bash
curl -H "Content-Type:text/plain" localhost:8080 -d 'OMG! OMG! OMG!'

curl -H "Content-Type:text/plain" localhost:8080/uppercase -d 'OMG! OMG! OMG!'

$ curl -H "Content-Type:text/plain" localhost:8080/uppercase,reverseReactive -d 'OMG! OMG! OMG!'

```
### AOT build image
```bash
$ ./gradlew bootBuildImage

$ docker images
rgn-spring-functions-cloudstream                         0.0.1-SNAPSHOT 


$ docker run -rm -p 8080:8080  rgn-spring-functions-cloudstream:0.0.1-SNAPSHOT
```

### Docker compose
```bash
docker-compose up

```



### Rabbit MQ

#### credentials
Once you see this, open your browser and head over to http://localhost:15672. You should see the RabbitMQ UI. Use guest as username and password.



Reference
---------

* https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.7.3&packaging=jar&jvmVersion=18&groupId=rgn.spring&artifactId=rgn-spring-functions-cloudstream&name=rgn-spring-functions-cloudstream&description=Spring%20Functions%20Cloud%20%20Stream%20Sample&packageName=rgn.spring.functionscloudstream&dependencies=cloud-function,lombok,webflux,cloud-stream,amqp,native
* https://docs.spring.io/spring-cloud-function/docs/current/reference/html/
* O'Reilly Learning - Event-Driven with Spring Cloud Function and Spring Cloud Stream
* https://x-team.com/blog/set-up-rabbitmq-with-docker-compose/
* https://github.com/ThomasVitale/spring-cloud-function-stream-webinar-apr-2021
* https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#aot
* https://www.kennybastani.com/2017/07/microservices-to-service-blocks-spring-cloud-function-aws-lambda.html
* https://faun.pub/spring-cloud-function-deploy-first-serverless-function-using-spring-1bbdc0a4620d
* https://rieckpil.de/java-aws-lambda-with-spring-cloud-function/
* https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/getting-started-with-spring-cloud-function-in-azure
* https://github.com/Azure-Samples/hello-spring-function-azure


