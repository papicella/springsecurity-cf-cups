<h1>Spring Security - User provided Service for PCF </h1>

The following demo shows how to inject the Spring Security username/password credentials from a User Provided service on PCF,
hence using the VCAP_SERVICES env variable to inject the values required to protect the resource while running in PCF. Spring 
Boot automatically converts this data into a flat set of properties so you can easily get to the data as shown below

<h2>Steps</h2>

- Clone project as follows

```
$ git clone 
```

- Package as follows

```
$ mvn package
```
<hr />
Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal Australia 