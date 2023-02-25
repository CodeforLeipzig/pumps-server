import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	//id("com.netflix.dgs.codegen")
}

group = "de.oklab.l"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	maven { url = uri("https://repo.osgeo.org/repository/release/") }
	mavenCentral()
}

dependencies {
	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:_"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:_")
	implementation(Spring.boot.actuator)
	implementation(Spring.boot.data.jpa)
	implementation(Spring.boot.data.rest)
	implementation(Spring.boot.oauth2Client)
	implementation(Spring.boot.security)
	implementation(Spring.boot.validation)
	implementation("com.graphql-java:graphiql-spring-boot-starter:_")
	implementation(Spring.boot.web)
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
	implementation("com.bedatadriven:jackson-datatype-jts:_")
	implementation("org.hibernate:hibernate-spatial:_")
	implementation("org.jetbrains.kotlin:kotlin-reflect:_")
	implementation(Kotlin.stdlib.jdk8)
	implementation("org.springframework.session:spring-session-core:_")
	implementation("org.eclipse.jgit:org.eclipse.jgit:_")
	implementation("org.eclipse.rdf4j:rdf4j-bom:_")
	implementation("org.eclipse.rdf4j:rdf4j-storage:_")
	implementation("org.wikidata.wdtk:wdtk-datamodel:_")
	implementation("org.wikidata.wdtk:wdtk-rdf:_")
	implementation("org.wikidata.wdtk:wdtk-storage:_")
	implementation("org.wikidata.wdtk:wdtk-util:_")
	implementation(Square.okHttp3)
	implementation(Square.okHttp3.urlConnection)
	implementation("org.geotools:gt-referencing:_")

	runtimeOnly("io.micrometer:micrometer-registry-prometheus:_")
	runtimeOnly("org.postgresql:postgresql:_")
	testImplementation(Spring.boot.test)
	testImplementation(Spring.security.spring_security_test)
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
