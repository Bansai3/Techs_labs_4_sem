plugins {
    id("java")
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
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
    implementation("org.projectlombok:lombok:1.18.22")
    implementation(project(":lab-4:dao"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-validator
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("org.springframework:spring-test:6.0.7")
    implementation("org.springframework.boot:spring-boot-test:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-security")


}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}