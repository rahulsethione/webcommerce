# Webcommerce

### Technology Stack
- Java 11 EE
- MongoDB
- Elasticsearch
- JavaScript/React.js/HTML

### Purpose
The purpose of creating this project is to gain practical experience of developing a web application that uses Java, Spring Boot, MongoDB, Elasticsearch on the server side and a React.js SPA on client side and manage the project as it scales as full-stack developer.

### Architecture Details
- For starters, the project follows a monolithic application design but later it will be decomposed into a microservices for scalability *
- It consists of a web application with fewer REST APIs at present that allows creation of data which is made to persist on MongoDB
- There is a scheduled job that loads the newly created data and publishes it to an Elasticsearch server after flattening
- Elasticsearch is used to support efficient and speedy searching of products by the customers like any other e-commerce web application
- A new feature of MongoDB explored here is NOSQL Transactions Support otherwise RDBMS would have been a choice, but then data would have to be modelled accordingly
- For security access control has been configured using JWT authorization but session management and role management has to be improved

### Plans for the future
- Add more features to the application related to ecommerce
- Plug in a message broker/ message queue framework like Kafka which will make the publishing data to Elasticsearch by scheduled jobs more fault-tolerant and allow data based on events to be submitted for analytics *
- Add analytics capability to the application with help of suitable technology *
- AWS Cloud Integration *
- Improve security of the application by following practices like setting Response Headers to prevent XSS etc.

## Note
The primary purpose of working on this project is to learn to implemenent different areas of web development as a Full-Stack Developer while focussing on e-commerce domain. The secondary purpose is to develop an e-commerce application for selling liquor online. Who knows it could be authorized by the government some day!

* Areas marked with (*) are the subject that are not yet explored
