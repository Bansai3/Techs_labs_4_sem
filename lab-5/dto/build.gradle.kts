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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation(project(":lab-5:jpa"))
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.springframework.security:spring-security-core:6.0.2")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}