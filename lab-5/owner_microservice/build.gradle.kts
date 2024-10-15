plugins {
    id("java")
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.fedotov.main"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("org.springframework.security:spring-security-core:6.0.2")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.0.6")
    implementation("org.springframework.data:spring-data-commons:3.1.0")
    implementation("org.springframework.data:spring-data-jpa:3.1.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.springframework.security:spring-security-config:6.0.2")
    implementation("org.apache.kafka:kafka-clients:3.4.0")
    implementation("org.springframework.kafka:spring-kafka:3.0.7")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.security:spring-security-web:6.1.0")
    implementation("org.postgresql:postgresql:42.6.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    implementation(project(":lab-5:jpa"))
    implementation(project(":lab-5:dto"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}