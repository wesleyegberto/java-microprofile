package com.wesleyegberto.microprofile.healthcheck;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import com.wesleyegberto.microprofile.repository.ProductRepository;

@Health
@ApplicationScoped
public class DatabaseHealthChecker implements HealthCheck {
	// inject the resource we want to monitor
	@Inject
	private ProductRepository productRepository;

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("database")
			.state(productRepository.canPingAndConnect())
			.build();
	}
}
