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
* [Gradle – Create a Jar file with dependencies](https://www.mkyong.com/gradle/gradle-create-a-jar-file-with-dependencies/)



http://www.dropwizard.io/0.7.1/docs/manual/jdbi.html