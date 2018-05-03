# Spring Webflux Cassandra


## docker compose

```bash
$ docker-compose up -d

//logon and call cqlsh (from host)
$ docker exec -it docker_react_cassandra_1 /bin/bash

//inside container
root@6e1e272c31a0:/# cqlsh
Connected to Test Cluster at localhost:9160.
[cqlsh 4.1.1 | Cassandra 2.0.10 | CQL spec 3.1.1 | Thrift protocol 19.39.0]
Use HELP for help.
cqlsh>

```

## helenos connection
*url* http://localhost:9000/gui/index.html
*Hosts:* react_cassandra:9160
*Cluster Name:* RagnaCluster


## Reference

* [GETTING STARTED - Building a Reactive RESTful Web Service \(t01\)](https://spring.io/guides/gs/reactive-rest-service/)
* [Spotify cassandra docker](https://hub.docker.com/r/spotify/cassandra/)
* [Doing Stuff With Spring WebFlux](https://dzone.com/articles/doing-stuff-with-spring-webflux)
* [Docker Helenos](https://github.com/emdem/docker-helenos)
* [Reactive Streams With Spring Data Cassandra](https://dzone.com/articles/reactive-streams-with-spring-data-cassandra)
* [Getting started with Spring Data Cassandra](https://lankydanblog.com/2017/10/12/getting-started-with-spring-data-cassandra/)
* [Reactive Microservice And Spring5](https://www.slideshare.net/JayLee1/reactive-microservice-and-spring5)
