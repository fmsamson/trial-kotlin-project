package com.exercise.cleanarch.acceptance

import com.exercise.cleanarch.MainApp
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [MainApp::class]
)
class SpringBootAcceptanceTest