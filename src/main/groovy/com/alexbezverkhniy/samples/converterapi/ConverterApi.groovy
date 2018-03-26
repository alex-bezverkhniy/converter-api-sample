package com.alexbezverkhniy.samples.converterapi

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Description
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * ℉ = ℃ * 1.8000 + 32
 * ℃ = (℉ - 32)/1.8000
 */
@Controller
@Description("Simple Convert Celsius to Fahrenheit/Fahrenheit to Celsius")
@RequestMapping(value = "/api/convert")
@EnableAutoConfiguration
class ConverterApi {

    private static final Logger logger = LoggerFactory.getLogger(ConverterApi.class)

    private static final float RATE = 1.8000
    private static final int GAP = 32

    @RequestMapping(value = "/tocelsius/{temperature}", method = RequestMethod.GET)
    @ResponseBody
    Map tocelsius(@PathVariable("temperature") Float temperature) {
        if (temperature == null) {
            throw new IllegalArgumentException("temperature sould not be null or empty")
        }
        Map result = ["celsius": ((temperature - GAP) / RATE), "fahrenheit": temperature]
        logger.info("Fahrenheit to Celsius: " + result)
        return result
    }

    @RequestMapping(value = "/tofahrenheit/{temperature}", method = RequestMethod.GET)
    @ResponseBody
    Map tofahrenheit(@PathVariable("temperature") Float temperature) {
        logger.info("Celsius to Fahrenheit")
        if (temperature == null) {
            throw new IllegalArgumentException("temperature sould not be null or empty")
        }
        Map result = ["celsius": temperature, "fahrenheit": ((temperature * RATE) + GAP)]
        logger.info("Celsius to Fahrenheit: " + result)
        return result
    }

    static void main(String[] args) throws Exception {
        SpringApplication.run(ConverterApi.class, args);
    }
}
