plugins {
    id("java")
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.fedotov.controller"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks {
    bootJar.get().archiveClassifier.set("boot")
    jar.get().enabled = true
    bootJar.get().enabled = false
}

dependencies {
    implementation(project(":lab-4:service"))
    implementation(project(":lab-4:dao"))
    implementation("org.projectlombok:lombok:1.18.22")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
   implementation("org.springframework.boot:spring-boot-starter-security")
//  Temporary explicit version to fix Thymeleaf bug
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.1.RELEASE")
    implementation("org.springframework.security:spring-security-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}