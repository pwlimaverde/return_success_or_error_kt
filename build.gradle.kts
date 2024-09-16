plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    application
    `maven-publish`
}

group = "org.pwlimaverde.return_success_or_error_kt"
version = "1.5.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlin.test)
    implementation(libs.coroutines)
    testImplementation(libs.coroutines.testes)
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
