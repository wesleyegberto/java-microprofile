# MicroProfile - Metrics

Example of how to use the Metrics spec.

```
@Path
public class WeatherResource {
	@GET
	@Counted(name = "weather_calls", absolute = true)
	public Response call() {
		return return Response.ok("cold").build();
	}
}
```


Feed the metric (using cURL or ApacheBench)

```
* curl http://localhost:8080/resources/weather
* ab -n 10000 -c 100 http://localhost:8080/resources/weather
```


See the metrics

```
curl -H "Accept:application/json" http://localhost:8080/metrics
```



### Links
* Spec: https://github.com/eclipse/microprofile-metrics
* Post: https://developers.redhat.com/blog/2017/10/17/monitoring-aspects-eclipse-microprofile-1-2/
