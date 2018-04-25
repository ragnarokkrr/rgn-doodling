# Spring Neo4j POC



## Docker Neo4j
## Running

```bash
$ docker-compose up -d
$ docker compose build
$ docker-compose down --volumes

```

Url: http://localhost:7474/

Password setup:
```
$ curl -v -u neo4j:neo4j -X POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"
```

Login: `neo4j/neo`

## Reference

* [Accessing Data with Neo4j](https://spring.io/guides/gs/accessing-data-neo4j/)
* [Neo4j with Docker](https://neo4j.com/developer/docker/)
