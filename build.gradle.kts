plugins {
    kotlin("jvm") version "2.0.10"
    application
    `maven-publish`
}

group = "org.pwlimaverde.return_success_or_error_kt"
version = "1.2.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin{
    jvmToolchain(22)
}

publishing{
    publications{
        create<MavenPublication>("Maven"){
            groupId = "com.pwlimaverde"
            artifactId = "return_success_or_error_kt"
            version = version
            from(components["kotlin"])
        }
    }
}
