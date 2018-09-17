Prerequisites: Java 8 or up is installed.
1. Unpack demo.zip, cd to /demo/;
2. Run either 'mvnw package' or 'mvnw.cmd package', depending on your OS;
3. Find /demo/target/demo-0.0.1-SNAPSHOT.jar and run it from the command line using 'java -jar ./target/demo-0.0.1-SNAPSHOT.jar' ;
4. Spring Boot application will start up, resource will be available under http://localhost:8080/userservice/register ;
5. Sample request using curl:
curl -i -X POST http://localhost:8080/userservice/register -H "Content-Type: application/json" -d '{"firstName": "Some first name", "lastName": "The last name", "userName": "The user name","password": "The password in plain text"}'
