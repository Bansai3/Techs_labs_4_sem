plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("name.remal.gradle-plugins.lombok:lombok:2.0.2")
    implementation("name.remal.lombok:name.remal.lombok.gradle.plugin:2.0.2")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.javatuples:javatuples:1.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    //implementation(kotlin("script-runtime"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}