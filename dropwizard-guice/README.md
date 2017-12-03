# DropWizard / Guice

## build
```
$ gradle fatJar

$ java -jar build/libs/dropwizard-guice.all-1.0.jar  server config.yml
```

## usage
```
$ curl http://localhost:8080/hello

$ curl http://localhost:8080/hello/from-component
```

## Ref

* [dropwizard-and-guice](https://dzone.com/articles/dropwizard-and-guice)