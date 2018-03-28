# Sample Convertor REST API

## Request examples

### Convert fahrenheit to celsius
```ssh
curl -X GET http://alex-bezverkhniy.herokuapp.com/api/convert/to/celsius/10 | python -m json.tool
```
### Convert fahrenheit to fahrenheit
```ssh
curl -X GET http://alex-bezverkhniy.herokuapp.com/api/convert/to/fahrenheit/10 | python -m json.tool
```

## Heroku Config
### Heroku config for Groovy script based project
To deploy and run your groovy app (or even groovy script) on Heroku you need to install groovy into your container (dyno).
We can do this using special mechanism [Buildpacks](https://devcenter.heroku.com/articles/buildpacks#officially-supported-buildpacks) are responsible for transforming deployed code into a slug, which can then be executed on a dyno. 
Here is [Builfpack](https://github.com/alex-bezverkhniy/heroku-groovy-buildpack) for installing groovy. 
I forked it from [Yourinspiration/heroku-groovy-buildpack](https://github.com/Yourinspiration/heroku-groovy-buildpack) and upgraded Groovy version up to 2.5  

### Run groovy script on Heroku
To run _converter-api_ on Heroku add this line into _Procfile_.
```
web: groovy converter-api.groovy
```
