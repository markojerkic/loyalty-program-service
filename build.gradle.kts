import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
}

group = "hr.loyalty.program"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.6.3")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("com.auth0:java-jwt:3.18.3")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Temporary inmemory db
    runtimeOnly("com.h2database:h2:1.3.148")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("org.postgresql:postgresql")
    testImplementation("io.mockk:mockk:1.4.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("junit:junit:4.13.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
