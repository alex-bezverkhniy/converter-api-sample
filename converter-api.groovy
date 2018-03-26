
@Grapes([
@Grab('com.sparkjava:spark-core:2.7.2'),
@Grab('org.slf4j:slf4j-simple:1.7.21')
])

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static spark.Spark.*
import groovy.json.*

// Server Port
port(System.getenv("PORT") ?: 4567)

Logger logger = LoggerFactory.getLogger("Main")
def toJson = { JsonOutput.toJson(it) } as spark.ResponseTransformer
float RATE = 1.8000
int GAP = 32

get("/health", "application/json", {req, res ->
    [status: "UP"]
}, toJson)

path("/api", {
    path("/convert", {
        get("/tocelsius/:temperature", {req, res ->
            float temperature = Float.parseFloat(req.params("temperature"))
            Map result = ["celsius": ((temperature - GAP) / RATE), "fahrenheit": temperature]
            logger.info("Fahrenheit to Celsius: " + result)
            result
        }, toJson)

        get("/tofahrenheit/:temperature", {req, res ->
            float temperature = Float.parseFloat(req.params("temperature"))
            Map result = ["celsius": temperature, "fahrenheit": ((temperature * RATE) + GAP)]
            logger.info("Celsius to Fahrenheit: " + result)
            result
        }, toJson)
    })
})

