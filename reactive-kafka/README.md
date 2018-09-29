# Example of Reactive project using Microprofile


### Requirements:
* Env vars:
  * *JAVA8_HOME*: Path do JDK 8 folder;
  * *PAYARA_HOME*: Path do Payara Micro folder and its JCA Connector to Kafka; 


### Run and Test 

* Run Kafka:
Inside Zookeeper dir: `./bin/zkServer.sh start`
Inside Kafka dir: `./bin/kafka-server-start.sh config/server.properties`

* Build:
`mvn clean package`

* Run:
`./run.sh`

* Check the topic:
`./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic btc-tx --from-beginning`

* Streaming the messages:
`http://localhost:8080/data-processor/resources/transactions/stream`


### Links
* [Payara Kafka Connector](https://blog.payara.fish/payara-micro-jca-adapters-apache-kafka)
* [Blog post](https://github.com/pusher/pusher-websocket-java)