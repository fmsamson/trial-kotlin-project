import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
//    kotlin("plugin.lombok") version "1.6.0"
//    id("io.freefair.lombok") version "5.3.0"
//    id("org.jetbrains.kotlin.plugin.spring") version "1.6.0"
    application
}

group = "com.practice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // base
    implementation("org.jetbrains.kotlin:kotlin-reflect:${project.property("kotlin.reflect.version")}")
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${project.property("spring.boot.version")}"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${project.property("spring.cloud.version")}"))
    // main framework
    implementation("org.springframework.boot:spring-boot")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    // controller
    implementation("org.springframework.boot:spring-boot-starter-web")
    // data layer
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    // http client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-httpclient")
    // message broker
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${project.property("mockito.kotlin.version")}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("com.practice.MainApp")
}