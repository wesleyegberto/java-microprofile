package com.wesleyegberto.microprofile.resource;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

@ApplicationScoped
public class TemperatureSensor {
	// Track the last temperature read by the sensor
	@Gauge(name = "temperature_senso_val", absolute = true, unit = MetricUnits.SECONDS)
	public double readValue() {
		return 20 + Math.random() * 5;
	}

}
