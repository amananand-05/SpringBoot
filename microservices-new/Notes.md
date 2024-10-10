# Spring boot

- Spring Cloud
  https://spring.io/projects/spring-cloud

### Services

- Product service
- Order service
- Inventory service
- Notification service

---

- order inventory and notification are going to interact(sync and async wise).
  ![alt text](image.png)

####for each service
---

    HTTP Requests
        |
    1. controller layer
        |
    2. Service layer- - - -> 4. Message Queue
        |
    3. Repository ---------> 5. DB

---

    Creds:

    uname: dummy_username
    pass: dummy_password

    spring.data.mongodb.uri=mongodb+srv://dummy_username:dummy_password@cluster0.tngem.mongodb.net/spring_boot_db?retryWrites=true&w=majority&appName=Cluster0

--- 

### Notes:

you can do `maven package` and get an executable .jar file in the target folder
which can be further run

ex `java -jar product-service-0.0.1-SNAPSHOT.jar`

you can overwrite the environment variable with command line arguments

ex: `java -jar product-service-0.0.1-SNAPSHOT.jar --server.port=8081`
















