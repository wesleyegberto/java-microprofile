mvn clean package -Pwildfly-swarm
java -jar -Dswarm.project.stage=development target/health-check-example-swarm.jar