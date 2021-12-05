package com.exercise.cleanarch

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.exercise.cleanarch")
@EnableAutoConfiguration
open class ConfigurationApp