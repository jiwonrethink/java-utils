plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.companywe.xergy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.apache.commons:commons-collections4:4.4")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

springBoot {
	mainClass.set("jiwon.java.utils.common.JavaUtilsApplication")
}

val querydslDir = "$buildDir/generated/sources/annotationProcessor"

sourceSets {
	main {
		java.srcDirs("src/main/java", querydslDir)
	}
}

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory.file(querydslDir)
	// 다른 자바 버전에서도 사용할 수 있도록 java.annotation.Generated 로 import 하도록 설정하는 코드
	options.compilerArgs.add("-Aquerydsl.generatedAnnotationClass=javax.annotation.Generated")
}

tasks {
	getByName<Delete>("clean") {
		delete.add(querydslDir) // add accepts argument with Any type
	}
}
