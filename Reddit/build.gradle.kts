import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.14"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.spring") version "1.9.0"
	kotlin("plugin.jpa") version "1.9.0"
}

group = "com.sbk"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

/**
 * implementation: dependencies required (and available) by/for the main source-code for both development/compiler and runtime. These dependencies are included in the classpath of the complied code available during both compile time and runtime
 *
 * testImplementation: dependencies required only during testing. These dependencies are included in the classpath when running tests. These dependencies are testing frameworks, assertion libraries and other tools required for testing the code
 *
 * developmentOnly: dependencies required only during development and not the runtime. Dependencies in this configuration are not included in the distribution of the application code artifacts. Dependencies in this configuration serve purposes such as code analysis, debugging or generating documentation
 *
 * runtimeOnly: dependencies required at runtime but not necessarily during compile time. Dependencies in this configuration provides functionality for only when application is running such as plugins, drivers or dynamically loaded modules
 */
dependencies {
	// spring dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// kotlin test
	implementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.0")
	implementation("org.jetbrains.kotlin:kotlin-test-common:1.9.0")
	implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:1.9.0")

	// database
	runtimeOnly("org.postgresql:postgresql")

	// JWT authentication
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// show time span
	implementation("com.github.marlonlom:timeago:4.0.0")

	// test dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
