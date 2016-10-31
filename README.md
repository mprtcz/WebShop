# WebShop

## Web shop project

A java web application project based on [Spring](https://spring.io/) framework.

Application which allows selling and purchasing items available in the shop. The main page displays several randomly chosen items from database with pictures and descriptions:


![mainpageitems](https://sc-cdn.scaleengine.net/i/2ff88e8153296b5efeedace6ad012b5a3.png)



The application allows users to register and log in to their account using spring Security


 ![login](http://i.giphy.com/l2JhKPP5953KsBAhW.gif)
 
 
 
  * New items can be added only by users with `admin` role. 

  * Items can be purchased by regular users after registration, but can be viewed by anyone using the application with default `Guest` account.

  * Every user has a custom credit balance om their account which can be changed only by a user with `admin` role or by transaction within the application. Every user has a cart in which the items to buy are stored and when the user logs out or the session expires and items are not purchased, they are added again to items pool.

Views are provided by the backend via [JSP pages](https://en.wikipedia.org/wiki/JavaServer_Pages).

The project's persistence is provided by [Hibernate framework](http://hibernate.org/) operating on MySQL database, however there are several maven profiles configuring databases for hsqdb, prostgresql and mysql db providers.

Tests written using [junit](http://junit.org/junit4/) and [mockito](http://site.mockito.org/) APIs. 

The application run on [Apache Tomcat](http://tomcat.apache.org/) servlet container in version 8.0.30