# Spring boot Java RX


## requests

```bash

$ http localhost:8080/invoices
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Wed, 02 May 2018 13:31:38 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked

[
    {
        "date": 1525267898425,
        "name": "Acme"
    },
    {
        "date": 1525267898425,
        "name": "Oceanic"
    }
]



$ http localhost:8080/messages
HTTP/1.1 200 OK
Content-Type: text/event-stream;charset=UTF-8
Date: Wed, 02 May 2018 13:35:47 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked

```


## Refs

* [RxJava Spring MVC integration - Deprecated](https://github.com/jmnarloch/rxjava-spring-boot-starter)
