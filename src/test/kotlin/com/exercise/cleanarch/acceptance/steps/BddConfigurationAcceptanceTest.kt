package com.exercise.cleanarch.acceptance.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class BddConfigurationAcceptanceTest {
    private val logger = logger(this)

    @When("acceptance test are executed")
    fun func_when(){}

    @Then("print out BDD enabled")
    fun func_then() {
        logger.info("BDD enabled")
    }

    private inline fun <reified T> logger(from: T): Logger {
        return LoggerFactory.getLogger(T::class.java)
    }
}