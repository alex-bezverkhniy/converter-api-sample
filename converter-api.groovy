@Grab('com.sparkjava:spark-core:2.7.2')
@Grab('org.slf4j:slf4j-simple:1.7.21')

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import groovy.json.*
import static spark.Spark.*

def toJson = {JsonOutput.toJson(it)} as spark.ResponseTransformer
Logger logger = LoggerFactory.getLogger("Main")
float RATE = 1.8000
int GAP = 32

port(System.getenv("PORT") ? Integer.parseInt(System.getenv("PORT")) : 4567)

get("/health", {req, res ->
    return [status: "UP"]
}, toJson)

path("/api", {
    path("/convert", {
        get("/tocelsius/:temperature", {req, res ->
            float temperature = Float.parseFloat(req.params(":temperature"))
        Map result = ["celsius": ((temperature - GAP) / RATE), "fahrenheit": temperature]
        logger.info("Fahrenheit to Celsius: " + result)
            result
        })

        get("/tofahrenheit/:temperature", {req, res ->
            float temperature = Float.parseFloat(req.params(":temperature"))
            Map result = ["celsius": temperature, "fahrenheit": ((temperature * RATE) + GAP)]
            logger.info("Celsius to Fahrenheit: " + result)
            result
        })
    })
})