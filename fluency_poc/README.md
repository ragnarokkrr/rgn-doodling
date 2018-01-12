# Fluent D Fluency POC


## fluentd docker

### Docker file extension
```

docker build .

# tag
docker build -t ragna/fluentd_logback_test .


# run bash
docker run -it --name fluentd_forwarder_current ragna/fluentd_forwarder /bin/bash

# push local registry
docker push localhost.localdomain:5000/ubuntu


# rum mapping volume and passing file name
docker run --name fluentd -d \
  -p 9880:9880 -v /home/ragnarokkrr/prj/rgn-doodling/fluency_poc/fluentd_conf:/fluentd/etc -e FLUENTD_CONF=fluentd.conf \
  ragna/fluentd_logback_test



docker exec -it "ragna/fluentd_logback_test" /bin/bash
```


### Parameterized usage - fluentd image
```
docker exec -it <id> /bin/bash

docker run --name fluentd -d \
  -p 9880:9880 \
  fluent/fluentd:v0.12-debian

docker run --name fluentd -d \
  -p 9880:9880 \
  -p 24224:24224 \
  -v /home/ragnarokkrr/prj/rgn-doodling/fluency_poc/fluentd_conf:/fluentd/etc \
  -v /tmp/fluentd:/var/log/fluentd/fluency \
  -e FLUENTD_CONF=fluentd.conf \
  fluent/fluentd:v0.12-debian

MY_FD=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' fluentd)
echo $MY_FD

curl -X POST -d 'json={"json":"message"}' http://$MY_FD:9880/sample.test

docker top fluentd

```



## Ref

* [Dockerfile reference](https://docs.docker.com/engine/reference/builder/#usage)
* [Installing Fluentd with Docker](https://docs.fluentd.org/v0.12/articles/install-by-docker)
* [docker pull fluent/fluentd](https://github.com/fluent/fluentd-kubernetes-daemonset)
* [fluent/fluentd (docker image customization)](https://hub.docker.com/r/fluent/fluentd/)
* [fluency](https://github.com/komamitsu/fluency)
* [fluent-logger-java](https://ithub.com/fluent/fluent-logger-java)
* [Shadow Plugin User Guide & Examples](http://imperceptiblethoughts.com/shadow/)