import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    application
    kotlin("plugin.serialization") version "1.5.0"
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
    implementation ("com.phodal.chapi:chapi-ast-java:2.3.5")
    implementation ("com.phodal.chapi:chapi-domain:2.3.5")
    implementation ("com.phodal.chapi:chapi-ast-kotlin:2.3.5")
    implementation ("com.phodal.chapi:chapi-ast-kotlin:2.3.5")
    implementation ("com.phodal.chapi:chapi-ast-csharp:2.3.5")
    implementation ("com.phodal.chapi:chapi-parser-toml:2.3.5")
    implementation ("com.phodal.chapi:chapi-parser-cmake:2.3.5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}