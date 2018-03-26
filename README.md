# Sample Convertor REST API

### Convert fahrenheit to celsius
```ssh
curl -X GET http://alex-bezverkhniy.herokuapp.com/api/convert/tocelsius/10
```

### Heroku config for Maven based project
```
web: java $JAVA_OPTS -jar -Dserver.port=$PORT target/converter-api-sample-0.0.1-SNAPSHOT.jar
```

### Heroku config for Groovy script based project
```
web: groovy converter-api.groovy
```
