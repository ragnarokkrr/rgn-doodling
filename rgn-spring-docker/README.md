# Spring Docker




## Tests

```
$ http localhost:8080
HTTP/1.1 200
Content-Length: 20
Content-Type: text/plain;charset=UTF-8
Date: Wed, 25 Apr 2018 14:13:21 GMT

Hello, Docker World!


$ ./gradlew build docker


$ docker run -p 8080:8080 -t ragna/gs-spring-boot-docker


$ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t ragna/gs-spring-boot-docker

```


## Reference


* [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
