mvn clean install -Ppayara-micro
$JAVA_HOME/bin/java -jar target/health-check-example.jar
# Execute the UberJar generate by the Payara-Micro-Plugin
# $JAVA_HOME/bin/java -jar target/ROOT-microbundle.jar