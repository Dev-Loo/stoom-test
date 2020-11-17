# stoom-test
## Stoom Test for Backend Candidates

###### Basic REST API for adresses management using Spring-Boot, Maven and H2 Database

To run project, go to demo folder and execute: mvn spring-boot:run

A simple Dockerfile exists in stoom-test/demo that assumes a .jar is created in demo/target folder

To create .jar go to stoom-test/demo and execute: mvnw install

To build image execute: docker build -t myapp .

And run it with: docker run -p 8080:8080 myapp

Depending on parameters provided in Create and Update operations, the API makes a request for geocoding information provided by google and persists latitude and longitude.
The documentation can be found on: https://developers.google.com/maps/documentation/geocoding/overview#geocoding-requests
In case the response throws an exception it is handled in the class **ClientResponseHandler**

Validation and other custom exceptions is handled by **AdressServiceAdvice** thus returning a status and custom message for the client.
In case of validation exceptions, a map with field names and default message is returned together with the status

Unit testing was planned with MockMvc, Mockito and JUnit 5 but unfortunetly not followed through because of time to build configuration for advice exception handling in mvc requests (had unexpected commitments)

