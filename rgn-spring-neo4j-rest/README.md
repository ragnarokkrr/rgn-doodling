# Spring Neo4j REST POC

## API Tests
```
$ http localhost:8080
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:25:22 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "people": {
            "href": "http://localhost:8080/people{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}

$ http localhost:8080/profile/people



$ http localhost:8080/people
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:27:24 GMT
Transfer-Encoding: chunked

{
    "_embedded": {
        "people": []
    },
    "_links": {
        "profile": {
            "href": "http://localhost:8080/profile/people"
        },
        "search": {
            "href": "http://localhost:8080/people/search"
        },
        "self": {
            "href": "http://localhost:8080/people{?page,size,sort}",
            "templated": true
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 0,
        "totalPages": 0
    }
}

# creating a people
$ http POST localhost:8080/people Content-Type:application/json firstName=Frodo lastName=Baggins
HTTP/1.1 201
Content-Type: application/json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:44:14 GMT
Location: http://localhost:8080/people/21
Transfer-Encoding: chunked

{
    "_links": {
        "person": {
            "href": "http://localhost:8080/people/21"
        },
        "self": {
            "href": "http://localhost:8080/people/21"
        }
    },
    "firstName": "Frodo",
    "lastName": "Baggins"
}


$ http POST localhost:8080/people Content-Type:application/json firstName=Frodo lastName="Baggins, Jr"
HTTP/1.1 201
Content-Type: application/json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:45:36 GMT
Location: http://localhost:8080/people/40
Transfer-Encoding: chunked

{
    "_links": {
        "person": {
            "href": "http://localhost:8080/people/40"
        },
        "self": {
            "href": "http://localhost:8080/people/40"
        }
    },
    "firstName": "Frodo",
    "lastName": "Baggins, Jr"
}


$ http  localhost:8080/people/40
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:47:33 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "person": {
            "href": "http://localhost:8080/people/40"
        },
        "self": {
            "href": "http://localhost:8080/people/40"
        }
    },
    "firstName": "Frodo",
    "lastName": "Baggins, Jr"
}



$ http localhost:8080/people/search
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:49:55 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "findByLastName": {
            "href": "http://localhost:8080/people/search/findByLastName{?name}",
            "templated": true
        },
        "self": {
            "href": "http://localhost:8080/people/search"
        }
    }
}


$ http localhost:8080/people/search/findByLastName?name=Baggins
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:50:58 GMT
Transfer-Encoding: chunked

{
    "_embedded": {
        "people": [
            {
                "_links": {
                    "person": {
                        "href": "http://localhost:8080/people/21"
                    },
                    "self": {
                        "href": "http://localhost:8080/people/21"
                    }
                },
                "firstName": "Frodo",
                "lastName": "Baggins"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/people/search/findByLastName?name=Baggins"
        }
    }
}

$ http PUT localhost:8080/people/21 Content-Type:application/json firstName=Bilbo lastName=Baggins
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:55:59 GMT
Location: http://localhost:8080/people/21
Transfer-Encoding: chunked

{
    "_links": {
        "person": {
            "href": "http://localhost:8080/people/21"
        },
        "self": {
            "href": "http://localhost:8080/people/21"
        }
    },
    "firstName": "Bilbo",
    "lastName": "Baggins"
}


$ http PATCH localhost:8080/people/21 Content-Type:application/json firstName=BILBO

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Wed, 25 Apr 2018 12:57:22 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "person": {
            "href": "http://localhost:8080/people/21"
        },
        "self": {
            "href": "http://localhost:8080/people/21"
        }
    },
    "firstName": "BILBO",
    "lastName": "Baggins"
}







```

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

Login: `neo4j/secret`





## Reference

* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Neo4j with Docker](https://neo4j.com/developer/docker/)
* [Docker Hub neo4j](https://hub.docker.com/_/neo4j/)
* [HTTPie](https://httpie.org/)
* [Neo4j Movies Example with Spring Data Neo4j](https://github.com/neo4j-examples/movies-java-spring-data-neo4j)
