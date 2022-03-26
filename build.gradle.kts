import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	//id("com.netflix.dgs.codegen") version "5.1.16"
}

group = "de.oklab.l"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.9.15"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.bedatadriven:jackson-datatype-jts:2.4")
	implementation("org.hibernate:hibernate-spatial:5.6.5.Final")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.session:spring-session-core")
	implementation("org.eclipse.jgit:org.eclipse.jgit:6.0.0.202111291000-r")
	implementation("org.eclipse.rdf4j:rdf4j-bom:3.7.5")
	implementation("org.eclipse.rdf4j:rdf4j-storage:3.7.5")
	implementation("org.wikidata.wdtk:wdtk-datamodel:0.13.1")
	implementation("org.wikidata.wdtk:wdtk-rdf:0.13.1")
	implementation("org.wikidata.wdtk:wdtk-storage:0.13.1")
	implementation("org.wikidata.wdtk:wdtk-util:0.13.1")
	implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.6")
	implementation("com.squareup.okhttp3:okhttp-urlconnection:5.0.0-alpha.6")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.+")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
