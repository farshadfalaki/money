# money
### Introduction
Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.

### Prerequisites
To compile and run the project, the following tools should be installed:

+ JDK 8+
+ Maven

### Installing
After pulling project, build MoneyParent module by maven
```
mvn install

```
In MoneyRunner run executable artifact
```
java -jar MoneyRunner-1.0-executable.jar

```
###Specifications
Database is in memory H2
Spark is used to handling http requests and reponses
There is a data intialization process with creating two accounts.
account number:1010 balance:500,000
account number:2020 balance:500,000
###Sample requests
Transfer money 
```
curl -X POST \
  http://localhost:4567/transfer \
  -H 'content-type: application/json' \
  -d '{"sender":"1010","receiver":"2020","amount":"10000"}'
```
Create account
```
curl -X POST \
  http://localhost:4567/account \
  -H 'content-type: application/json' \
  -d '{"accountNumber":"3030","name":"Mat","balance":"30000"}'
```
### Author
  Farshad Falaki
