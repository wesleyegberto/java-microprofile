mvn clean package -Pwildfly-swarm
$JAVA_HOME/bin/java -jar -Dswarm.project.stage=development target/health-check-example-swarm.jar