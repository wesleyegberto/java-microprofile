package com.github.wesleyegberto.microprofile.reactive.frontend;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;

@Path("/transactions/stream")
public class TransactionStreamerController {
	private SseBroadcaster broadcaster;
	
	@Inject
	private Publisher<String> transactions;

	@Context
	void setSse(Sse sse) {
		this.broadcaster = sse.newBroadcaster();
		// convert JSON strings into SSE events
		// and subscribe an SSE broadcaster to receive them
		Flowable.fromPublisher(transactions)
			.map(data -> createResponse(sse, data))
			.subscribe(broadcaster::broadcast);
	}

	private OutboundSseEvent createResponse(Sse sse, String data) {
		System.out.println("=== payload " + data);
		return sse.newEventBuilder()
			.mediaType(MediaType.APPLICATION_JSON_TYPE)
			.data(data)
			.build();
	}
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getBtcTransactions(@Context SseEventSink eventSink) {
		broadcaster.register(eventSink);
	}
}
