package com.github.wesleyegberto.microprofile.reactive.frontend;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.inject.Produces;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

@Stateless
public class TransactionEventProcessor {
	private Flowable<String> flowable;
	private FlowableEmitter<String> emitter;

	@PostConstruct
	public void init() {
		flowable = Flowable.create(emitter -> storeEmitter(emitter), BackpressureStrategy.LATEST);
	}

	private void storeEmitter(FlowableEmitter<String> emitter) {
		this.emitter = emitter;
	}

	@Produces
	public Publisher<String> getPublisher() {
		return flowable;
	}

	public void processEvent(@ObservesAsync String message) {
		if (this.emitter == null) return;
		this.emitter.onNext(message);
	}
}
