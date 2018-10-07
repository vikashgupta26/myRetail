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
RUN Below queries in you particular Databse in MySQL
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` BIGINT(11) NOT NULL ,
  `price` float(11) NOT NULL,
  `curr_code` varchar(255) NOT NULL DEFAULT 'USD',
  PRIMARY KEY (`id`)
);
LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `product` (`id`, `price`) values (13860428,13.49);
INSERT INTO `product` (`id`, `price`) values (16483589,11.32);
INSERT INTO `product` (`id`, `price`) values (16696652,10.82);
INSERT INTO `product` (`id`, `price`) values (16752456,21.72);
INSERT INTO `product` (`id`, `price`) values (15643793,31.52);
INSERT INTO `product` (`id`, `price`) values (15117729,13.46);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
================================
In Your selected database in Mongo RUN below 
db.getCollection("productdetail").insert({
    _id: "13860428" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a first product",
    "productDescription": "This is a first sample product description"
});
db.getCollection("productdetail").insert({
    _id: "16483589" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a second product",
    "productDescription": "This is a second sample product description"
});
db.getCollection("productdetail").insert({
    _id: "16696652" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a third product",
    "productDescription": "This is a third sample product description"
});
db.getCollection("productdetail").insert({
    _id: "16752456" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a fourth product",
    "productDescription": "This is a fourth sample product description"
});
db.getCollection("productdetail").insert({
    _id: "15643793" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a fifth product",
    "productDescription": "This is a fifth sample product description"
});
db.getCollection("productdetail").insert({
    _id: "15117729" ,
    "_class": "com.vik.demo.myretail.model.ProductDetails",
    "productName": "This is a sixth product",
    "productDescription": "This is a sixth sample product description"
});
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
---
NOTE:- 
Only admin user has access to actuator
For POC there is a hard delete of product.
But in production useable code it should be soft delete using some status active true/false
Also, In production it be deployed in one container. 
For production it will use oauth2 service to authenticate merchant/mobile app.
For production there will be one API gateway service where this service will be registered and via which this service gets called.
For production this service will be registered in a EUREKA SERVER and this will be a eureka client.
