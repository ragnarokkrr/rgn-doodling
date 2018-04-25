# Spring Docker




## Tests

```
$ http localhost:8080
HTTP/1.1 200
Content-Length: 20
Content-Type: text/plain;charset=UTF-8
Date: Wed, 25 Apr 2018 14:13:21 GMT

Hello, Docker World!




```

*build and run *
```
$ ./gradlew build docker


$ docker run -p 8080:8080 -t ragna/gs-spring-boot-docker


$ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t ragna/gs-spring-boot-docker


```


*debug*

```

$ docker inspect -f "{{.Path}} {{.Args}} ({{.Id}})"

$ docker run -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n" \
  -p 8080:8080 \
  -p 8000:8000 \
  -t ragna/gs-spring-boot-docker

```

## Reference


* [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
* [What's so special about /dev/./urandom?](http://www.thezonemanager.com/2015/07/whats-so-special-about-devurandom.html)
* [How do I use Docker environment variable in ENTRYPOINT array?](https://stackoverflow.com/questions/37904682/how-do-i-use-docker-environment-variable-in-entrypoint-array)
