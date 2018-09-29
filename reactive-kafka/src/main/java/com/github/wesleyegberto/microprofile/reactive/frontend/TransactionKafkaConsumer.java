package com.github.wesleyegberto.microprofile.reactive.frontend;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import fish.payara.cloud.connectors.kafka.api.KafkaListener;
import fish.payara.cloud.connectors.kafka.api.OnRecord;

@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(propertyName = "topics", propertyValue = "btc-tx"),
	    @ActivationConfigProperty(propertyName = "bootstrapServersConfig", propertyValue = "localhost:9092"),
	    @ActivationConfigProperty(propertyName = "keyDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
	    @ActivationConfigProperty(propertyName = "valueDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer") 
	}
)
public class TransactionKafkaConsumer implements KafkaListener {
	@Inject
	private Event<String> btcTxEvent;

	@OnRecord
	public void processMessage(ConsumerRecord<String, String> record) {
		btcTxEvent.fireAsync(record.value());
	}
}