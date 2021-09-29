# POC Debezium CDC for MongoDB

## Tutorial

```markdown
# Start the topology as defined in https://debezium.io/docs/tutorial/
export DEBEZIUM_VERSION=1.7
docker-compose -f docker-compose-mongodb.yaml up

# Initialize MongoDB replica set and insert some test data
docker-compose -f docker-compose-mongodb.yaml exec mongodb bash -c '/usr/local/bin/init-inventory.sh'

# Start MongoDB connector
curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @register-mongodb.json

# Consume messages from a Debezium topic
docker-compose -f docker-compose-mongodb.yaml exec kafka /kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server kafka:9092 \
    --from-beginning \
    --property print.key=true \
    --topic dbserver1.inventory.customers

# Modify records in the database via MongoDB client
docker-compose -f docker-compose-mongodb.yaml exec mongodb bash -c 'mongo -u $MONGODB_USER -p $MONGODB_PASSWORD --authenticationDatabase admin inventory'

db.customers.insert([
    { _id : NumberLong("1005"), first_name : 'Bob', last_name : 'Hopper', email : 'thebob@example.com', unique_id : UUID() }
]);

db.customers.insert([
    { _id : NumberLong("1006"), first_name : 'Bob 2', last_name : 'Hopper 2', email : 'thebob2@example.com', unique_id : UUID() }
]);

db.customers.insert([
    { _id : NumberLong("1009"), first_name : 'Bob 9', last_name : 'Hopper 9', email : 'thebob9@example.com', unique_id : UUID() }
]);


db.customers.updateOne(
   { _id : NumberLong("1009") },
   {
     $set: { "email": "thebob9.new.email@example.com"}
   }
);


# Shut down the cluster
docker-compose -f docker-compose-mongodb.yaml down
```

## Reference

* [Debezium connector for mongo DB](https://debezium.io/documentation/reference/1.7/connectors/mongodb.html)
* [Debezium mongo image](https://github.com/debezium/docker-images/tree/master/examples/mongodb/1.7)
* [Debezium CDC Mongo DB tutorial](https://github.com/debezium/debezium-examples/tree/master/tutorial#using-mongodb)
* [Debezium Tutorial](https://debezium.io/docs/tutorial/)
* Docker Images:
  * [Kafka](https://github.com/debezium/docker-images/tree/master/kafka/1.7)
  * [Kafka Connect base](https://github.com/debezium/docker-images/tree/master/connect-base/1.7)
  * [Kafka Connect](https://github.com/debezium/docker-images/tree/master/connect/1.7)
  * [Mongo DB](https://github.com/debezium/docker-images/blob/master/examples/mongodb/1.7/Dockerfile)
  * [Zookeeper](https://github.com/debezium/docker-images/tree/master/zookeeper/1.7)
