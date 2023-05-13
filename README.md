<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP: USERMICROSERVICE</h3>
  <p align="justify">
    This repository contains the microservice of users who register and enter according to their role (Admin, owner, client, employee). 
      In the database, the aforementioned roles are entered in the role table.
  </p >
   <p align="justify">
      The implementation of this microservice begins with the creation of the user with the owner role.
   </p>

</div>

### Steps to keep in mind
It contains the connection with the relational database in MySql, for which the powerup name database must be created and 


   
### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps:

1. Clone the repository
2. Create a database in MySql with the powerup
3. Update the credentials must be modified in the application-dev.yml file.
   ![img.png](img/img.png)

4. Run the microservice by right clicking on the UserMicroserviceApplication file and then on run
5. The port on which the microservice runs is 8080 which can be modified by entering the application-dev.yml file.
6. The documentation of the code is in the following link
   [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html) in your web browser
![img_1.png](img/img_1.png)

<!-- ROADMAP -->
## Tests

Right-click the test folder and choose Run tests with coverage:
- JUnit5 - Library used for testing
  ![Junit5.jpg](img/Junit5.jpg)
  
- Mockito - Framework used for code testing
  ![Mockito.jpg](img/Mockito.jpg)


- Jacoco - Test coverage tool
  ![jacoco.jpg](img/jacoco.jpg)

### Microservice structure

- Layer adpters
![img.png](img/img3.png)

- Layer configuration
![img_1.png](img/img_4.png)

- Layer domain
![img_2.png](img/img_2.png)



### Author
[@LilianaGallego](https://github.com/LilianaGallego) - Liliana Gallego

