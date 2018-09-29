package com.github.wesleyegberto.microprofile.reactive.dataprocessor;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.ObservesAsync;
import javax.resource.ConnectionFactoryDefinition;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import fish.payara.cloud.connectors.kafka.api.KafkaConnection;
import fish.payara.cloud.connectors.kafka.api.KafkaConnectionFactory;

@Stateless
@ConnectionFactoryDefinition(
	name = "java:global/env/KafkaCF",
	interfaceName = "fish.payara.cloud.connectors.kafka.KafkaConnectionFactory",
	resourceAdapter = "kafka-rar-0.3.0", // the same from .rar
	properties = {
        "bootstrapServersConfig=localhost:9092"
    }
)
public class KafkaPublisher {
	private static final String KAFKA_BTC_TOPIC = "btc-tx";
	
	// lookup is the same string as the name of the definition above
	@Resource(lookup = "java:global/env/KafkaCF")
	KafkaConnectionFactory factory;
	
	public void init() {
		System.out.println("=== Publisher initialized");
	}

	public void sendMessage(@ObservesAsync String value) throws Exception {
		System.out.println("=== sending " + value);
		try (KafkaConnection conn = factory.createConnection()) {
			conn.send(new ProducerRecord<>(KAFKA_BTC_TOPIC, value), this::sendCallback);
		}
	}
	
	private void sendCallback(RecordMetadata metadata, Exception exception) {
		if (exception == null) {
			System.out.println("Message sent to " + metadata);
		} else {
			System.out.println("Error to send to " + metadata + ", ex: " + exception.getMessage());
		}
	}
}