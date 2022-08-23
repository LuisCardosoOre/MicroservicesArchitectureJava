FROM adoptopenjdk/openjdk11:latest
ADD build/libs/ms-core-gestion-clientes*.jar ms-core-gestion-clientes.jar
EXPOSE 8040
ENTRYPOINT ["java", "-jar","-Xms256m","-Xmx2048m","ms-core-gestion-clientes.jar"]