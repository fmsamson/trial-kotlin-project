package com.exercise.cleanarch

import com.exercise.cleanarch.infra.aspect.ValidationAspect
import com.exercise.cleanarch.ports.core.UserValidationServiceProvider
import org.aspectj.lang.Aspects
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.exercise.cleanarch")
@EnableAutoConfiguration
open class ConfigurationApp {

    /**
     * Autowiring is not working in Aspect.
     * This is a workaround to inject the service for validation
     */
    @Bean
    open fun validationAspect(userValidationServiceProvider: UserValidationServiceProvider): ValidationAspect {
        val validationAspect = Aspects.aspectOf(ValidationAspect::class.java)
        validationAspect.setUserValidationProvider(userValidationServiceProvider)
        return validationAspect
    }

}