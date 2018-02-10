package com.wesleyegberto.microprofile.healthcheck;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import com.wesleyegberto.microprofile.repository.IntegrationRepository;

@Health
@ApplicationScoped
public class IntegrationHealthChecker implements HealthCheck {
	// inject the resource we want to monitor
	@Inject
	private IntegrationRepository repository;
	
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("integration")
			.state(repository.canPing())
			.build();
	}

}
