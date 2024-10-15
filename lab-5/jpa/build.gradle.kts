plugins {
    id("java")
}

group = "ru.fedotov.main"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    implementation("org.projectlombok:lombok:1.18.26")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("org.springframework.security:spring-security-core:6.0.2")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.0.6")
    implementation("org.springframework.data:spring-data-commons:3.1.0")
    implementation("org.springframework.data:spring-data-jpa:3.0.4")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.springframework.security:spring-security-config:6.0.2")
    implementation("org.springframework.security:spring-security-web:6.1.0")
    implementation("org.hibernate:hibernate-core:6.2.3.Final")
    implementation("org.postgresql:postgresql:42.6.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}