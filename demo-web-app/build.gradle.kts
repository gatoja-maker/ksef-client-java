plugins {
    java
    idea
    war
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.graalvm.buildtools.native") version "0.10.2"
}

group = "pl.akmf.ksef"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    create("integrationTestImplementation") {
        extendsFrom(configurations["testImplementation"], configurations["annotationProcessor"])
    }
    create("integrationTestRuntimeOnly") {
        extendsFrom(configurations["testRuntimeOnly"], configurations["annotationProcessor"])
    }
}

repositories {
    mavenCentral()
}

val integrationTestImplementation by configurations
val jakartaVersion = "4.0.2"
val jakartaValidationApiVersion = "3.0.2"
val jakartaAnnotationApiVersion = "3.0.0"
val jacksonDatabindNullableVersion = "0.2.6"
val jsr310Version = "2.15.2"
val junitVersion = "4.4"
val junitEngineVersion = "5.8.2"
val jsxbVarsion = "4.0.5"
val wiremockStandaloneVersion = "3.9.1"
val testcontainersVersion = "1.20.0"
val junitJupiterVersion = "5.10.3"


val xjc by configurations.creating
dependencies {
    implementation(project(":ksef-client"))

    implementation("jakarta.xml.bind:jakarta.xml.bind-api:$jakartaVersion")
    // Validation
    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidationApiVersion")
    implementation("jakarta.annotation:jakarta.annotation-api:$jakartaAnnotationApiVersion")
    implementation("org.openapitools:jackson-databind-nullable:$jacksonDatabindNullableVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jsr310Version")

    //
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-collections4:4.5.0-M2")
    implementation("org.apache.httpcomponents:httpclient:4.3.6")
    implementation("org.apache.santuario:xmlsec:3.0.4")
    implementation("org.bouncycastle:bcpkix-jdk18on:1.76")

    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    integrationTestImplementation("org.springframework.boot:spring-boot-testcontainers:") {
        exclude("org.testcontainers:testcontainers-shaded-jackson")
    }
    integrationTestImplementation("org.testcontainers:junit-jupiter:${testcontainersVersion}")
    integrationTestImplementation("org.testcontainers:testcontainers:${testcontainersVersion}")
    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
    integrationTestImplementation("org.wiremock:wiremock-standalone:${wiremockStandaloneVersion}")
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-webflux")}

sourceSets {
    create("integrationTest") {
        java.setSrcDirs(listOf("src/integrationTest/java"))
        resources.setSrcDirs(listOf("src/integrationTest/resources"))
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "Verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    useJUnitPlatform()
}

tasks.named("check") {
    dependsOn(tasks.named("integrationTest"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
