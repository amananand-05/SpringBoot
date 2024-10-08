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

    uname: aman
    pass: eA8LpYdwkMnrGbd

    spring.data.mongodb.uri=mongodb+srv://aman:eA8LpYdwkMnrGbd@cluster0.tngem.mongodb.net/spring_boot_db?retryWrites=true&w=majority&appName=Cluster0















