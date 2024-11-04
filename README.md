# Web Push Demo with Spring Boot

Based on [web-push-demo](https://github.com/DannyMoerkerke/web-push-demo) which uses a server in JavaScript this uses a Spring Boot application as server.

Spring Boot needs a certificate for SSL. Run:

    keytool -genkeypair -alias my-localhost -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass <password>

Then move keystore.p12 into src/main/resources

Spring Boot should now start on port 8443:

    ./mvn spring-boot:run

