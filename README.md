#### Description:

A sample webservice based on Spring Boot, utilizing hibernate ORM libraries to materialize data within a PostgreSQL database.
It also contains the Swagger library to expose in a dynamic fashion the current REST endpoints.

---
#### Testing the web service:

This project comes with a preconfigured (integration) test set, mainly focusing on testing the webapi as a whole (not just the services and or the repositories). 

The test set uses a configuration which can be found @ src/test/resources
and requires NO changes for the tests.

It utilizes the hsqldb in-memory database for the materialization of the data.

It can be tested by just running the tests within the **gr.laskarisn.test.TestAllControllers** class.

---

#### Deployment instructions:

**Prerequisites:** 

+ Please install a PostgreSQL database and run the commands on the **order-capture/database.sql** file which comes with the project. Can also be found here: https://github.com/laskasn/order-capture/blob/master/database.sql 

+ Please edit the **src/main/resources/application.properties** file to reflect your specific database deployment.
Edit **only** *spring.datasource.{url,username,password}*.


Then, there are two possible modes of running the service:

1. Standalone, i.e. within the IDE: 
It can be easilly initialized by just running the **main()** function of the **gr.laskarisn.app.Application** class.

2. Deployed on a servlet container (**recommended**)
Order capture web service can be easily deployed on a servlet container (i.e. Apache Tomcat), by performing a maven build.
Just navigate to the basepath of the project (where the pom.xml resides) and issue the following command:

```
mvn clean install
```
That will execute first the test suite (described on the previous paragraph **_Testing the web service_**) and then will build a deployable war file named "order-capture-ws.war" under the folder "target".

Then, just deploy on the tomcat, as any usual webapp.

---
#### Endpoints:

The endopoints can be easilly viewed from Swagger's UI.
To view them, the endpoint is of the form:

* Case of "standalone" deployment: the endpoint has the form: http://localhost:8080/swagger-ui.html
* Case of container deployment: the endpoint has the form: http://hostname:port/webapp-name/swagger-ui.html
where the hostname and port are the hostname and port of the container within which was deployed, and the webapp-name is the name of the war file deployed (by default it is "swagger-ui.html"). So, if deployed on a tomcat locally, the endpoint most probably should read http://localhost:8080/order-capture-ws/swagger-ui.html 
