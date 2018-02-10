# MicroProfile - Health Check

Example of how to use the health check spec.

```
@Health
@ApplicationScoped
public class DatabaseHealthChecker implements HealthCheck {
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("my-check")
			.state(Math.random() > 0.3)
			.build();
	}
}
```

See the app's health

```curl http://localhost:8080/health```



### Links

* Spec: https://github.com/eclipse/microprofile-health
* Blog: https://developers.redhat.com/blog/2017/10/17/monitoring-aspects-eclipse-microprofile-1-2
