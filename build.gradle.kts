plugins {
    kotlin("jvm") version "2.0.10"
    application
    `maven-publish`
}

group = "org.pwlimaverde.return_success_or_error_kt"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
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
            version = "1.0.0"
            from(components["kotlin"])
        }
    }
}
