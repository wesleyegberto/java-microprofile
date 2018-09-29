package com.github.wesleyegberto.microprofile.reactive.dataprocessor;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class TransactionHandler {
	@Inject
	Event<String> btcTxEvent;

	@Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
	public void feed() {
		btcTxEvent.fireAsync("{\"owner\":123,\"amount\":100}");
	}
}