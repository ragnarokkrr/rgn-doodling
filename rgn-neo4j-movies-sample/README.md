# Spring Data Movie Sample





## Docker Neo4j
### Running

```bash
$ docker-compose up -d
$ docker compose build
$ docker-compose down --volumes

```

Url: http://localhost:7474/

###  Password setup
```
$ curl -v -u neo4j:neo4j -X POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"
```

Login: `neo4j/secret`



## Reference


* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Neo4j with Docker](https://neo4j.com/developer/docker/)
* [Docker Hub neo4j](https://hub.docker.com/_/neo4j/)
* [HTTPie](https://httpie.org/)
* [Neo4j Movies Example with Spring Data Neo4j](https://github.com/neo4j-examples/movies-java-spring-data-neo4j)
