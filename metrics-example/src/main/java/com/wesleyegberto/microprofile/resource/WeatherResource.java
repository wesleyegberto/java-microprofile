package com.wesleyegberto.microprofile.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("weather")
@Produces({ MediaType.APPLICATION_JSON })
public class WeatherResource {

	@Inject
	private TemperatureSensor sensor;

	@GET
	// How many threads in the method
	@Counted(name = "weather_current_calls", absolute = true)
	// How often it is called in one sec
	@Metered(name = "weather_freq_calls", absolute = true, unit = MetricUnits.SECONDS)
	// Track the execution time
	@Timed(name = "weather_ms", absolute = true, unit = MetricUnits.MILLISECONDS)
	public Response getWeather() {
		// sensor simulator
		try {
			Thread.sleep(100);
		} catch (InterruptedException ignored) {
		}
		return Response.ok(sensor.readValue()).build();
	}
}
