# Spring Boot RabbitMQ Producer/Consumer Example
I will setup local environment. I will use technology stack;

* Spring Boot version v3.3.3
* Java 21
* Docker Technology for RabbitMQ Setup
* IntelLij IDEA

### Alternative - 1: Write the CLI command
Start Command: <br/>
```
docker-compose up
```
Stop Command:
```
docker-compose down
```

### Alternative - 2: Write the CLI command for local environment debug

```
docker pull rabbitmq:3-management
```

```
docker run -d --hostname rabbitserver --name rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Local Browser Address: http://localhost:15672 (username:guest & password:guest)