cd web
mvn package 
scp target/server.war helios:~/wildfly-34.0.0.Beta1/standalone/deployments/server.war
