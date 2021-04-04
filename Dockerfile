FROM openjdk:11
ADD target/air_companies_management_system.jar air_companies_management_system.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "air_companies_management_system.jar"]