plugins {
    kotlin("jvm") version "2.0.10"
    application
    `maven-publish`
}

group = "org.pwlimaverde"
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
            from(components["kotlin"])
        }
    }
}
