# Train Ticket Booking System
![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

# How to use this code?

1. Make sure you have [Java 17 and above](https://www.java.com/download/) and [Maven](https://maven.apache.org) installed

2. Fork this repository and clone it
  
```
$ git clone https://github.com/Aariv/TrainTicketBookingSystem
```

3. Navigate into the folder  

```
$ cd TrainTicketBookingSystem
```

4. Install dependencies

```
$ mvn clean install
```

5. Run the project

```
$ mvn spring-boot:run
```

6. Navigate to `http://localhost:8080/swagger-ui.html` in your browser to check everything is working correctly. You can change the default port in the `application.properties` file

```properties
server:
  port: 8080
```
## Swagger UI
<img width="1547" alt="Screenshot 2024-05-15 at 3 51 23 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/81ce01b1-8996-4d80-bd4c-aff77893194a">

## API where you can submit a purchase for a ticket
<img width="1414" alt="Screenshot 2024-05-15 at 3 53 34 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/f1b8594e-21d5-4503-8611-242e34338747">

## API that shows the details of the receipt for the user
<img width="1282" alt="Screenshot 2024-05-15 at 4 01 30 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/98fa65c2-51b3-4f66-8877-485b95c1f3f5">

## API that lets you view the users and seat they are allocated by the requested section
<img width="1282" alt="Screenshot 2024-05-15 at 4 03 29 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/5c090af5-accc-4169-90e9-9327e583c56b">

## API to remove a user from the train
<img width="1282" alt="Screenshot 2024-05-15 at 4 03 58 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/7f9f8310-170f-4074-bfa1-8f715bf4dd8c">

## API to modify a user's seat
<img width="1282" alt="Screenshot 2024-05-15 at 4 04 29 PM" src="https://github.com/Aariv/TrainTicketBookingSystem/assets/11393142/b9af60e9-72a0-4b80-ad92-2bc5a8602a14">

