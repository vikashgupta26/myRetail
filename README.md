# myRetail
myRetail is a company and this microservice is by its merchants to do crud operation for product(s).

This needs a mysql db and mongodb instance
My SQL details can be updated in the property file as required 
spring.datasource.url=jdbc:mysql://{host}:{port}/{database_name} 
spring.datasource.username={username} 
spring.datasource.password={password} 


#MONGO DB details can be updated as needed 
spring.data.mongodb.uri={mongoDBURI} 
spring.data.mongodb.database={mongo_database} 
spring.data.mongodb.username={mongo_user} 
spring.data.mongodb.password={mongo_password} 

==============================

RUN Below queries from file dump.sql

================================
In Your selected database in Mongo RUN from dump.json

--------------------------------------------------------
 Go the project folder and run
 
 mvn clean install spring-boot:run
 
Three merchants are added from property file
myretail.merchants=merchant1,merchant2,merchant3
myretail.merchantsPass=merchant1abc,merchant2abc,merchant3abc
After successful start of the project user can hit service URL's with credentials as mentioned in property file
user:- merchant1
password:- merchant1abc
OR
user:- merchant2
password:- merchant2abc
OR
user:- merchant3
password:- merchant3abc

---------------------------------
URL's

GET http://localhost:8009/product/{id} to get a product

DELETE http://localhost:8009/product/{id} to delete a product

PUT http://localhost:8009/product/update to update a product
{
    "id": 16483500,
    "price": 301.32,
    "currCode": "USD"	
}

POST http://localhost:8009/product/create to create a product 

{
    "id": 16483500,
    "price": 301.32,
    "currCode": "USD",
    "productDetails":{
		"productName":"This is a sample product 00 ",
		"productDescription":"This is a sample product description 00"
	}
}

-----------------------------------

NOTE:- 
Only admin user has access to actuator
For POC there is a hard delete of product.
But in production useable code it should be soft delete using some status active true/false
Also, In production it be deployed in one container. 
For production it will use oauth2 service to authenticate merchant/mobile app.
For production there will be one API gateway service where this service will be registered and via which this service gets called.
For production this service will be registered in a EUREKA SERVER and this will be a eureka client.

Test cases are missing and will be added.
