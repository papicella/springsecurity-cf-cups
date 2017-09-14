<h1>Spring Security - User provided Service for PCF </h1>

The following demo shows how to inject the Spring Security username/password credentials from a User Provided service on PCF,
hence using the VCAP_SERVICES env variable to inject the values required to protect the resource while running in PCF. Spring 
Boot automatically converts this data into a flat set of properties so you can easily get to the data as shown below

<h2>Steps</h2>

- Clone project as follows

```
$ git clone https://github.com/papicella/springsecurity-cf-cups.git
```

- Package as follows

```
$ mvn package
```

- Create a User Provided Service (CUPS) as follows

```
$ cf cups my-cfcups-service -p  "{\"username\": \"myadminuser\", \"password\": \"myadminpassword\"}"
```

- Push the application to PCF , notice how the manifest.yml is using the name of the CUPS service to bind
to. The name matcahes the CUPS command above

manifest.yml

```
applications:
- name: cf-security-cups
  memory: 756M
  instances: 1
  random-route: true
  path: ./target/springsecurity-cf-cups-0.0.1-SNAPSHOT.jar
  services:
    - my-cfcups-service
  env:
    JAVA_OPTS: -Djava.security.egd=file:///dev/urando
```

```
$ cf push
```

- View ENV variables to see VCAP_SERVICES variable

```
pasapicella@pas-macbook:~/piv-projects/springsecurity-cf-cups$ cf env cf-security-cups
Getting env variables for app cf-security-cups in org pcfdev-org / space pcfdev-space as admin...
OK

System-Provided:
{
 "VCAP_SERVICES": {
  "user-provided": [
   {
    "credentials": {
     "password": "myadminpassword",
     "username": "myadminuser"
    },
    "label": "user-provided",
    "name": "my-cfcups-service",
    "syslog_drain_url": "",
    "tags": [],
    "volume_mounts": []
   }
  ]
 }
}

```

- The src/main/resources/application.yml references the username/password values as shown below ensuring it uses
the CUPS service name "my-cfcups-service"

```
spring:
  application:
    name: security-cf-cups-demo
security:
  user:
    name: ${vcap.services.my-cfcups-service.credentials.username}
    password: ${vcap.services.my-cfcups-service.credentials.password}
```

- Invoke the application in a browser using the URL and when prompted the username/password will be as follows which
is the values we set in our CUPS command above. Binding that service to the application has injected the values into
our application.yml 

```
username: myadminuser
password: myadminpassword
```

Once successful the application name is returned 

<hr />
Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal Australia 