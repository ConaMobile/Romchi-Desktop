import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.conamobile"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.materialIconsExtended)
                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc06")
                implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc06")
                implementation("cafe.adriel.voyager:voyager-androidx:1.0.0-rc05")
                implementation("cafe.adriel.voyager:voyager-koin:1.0.0-rc05")
                //coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
                //for-network
                implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
                implementation("com.squareup.okhttp3:okhttp:4.9.0")
                implementation("com.google.code.gson:gson:2.8.6")
                //koin
                implementation("io.insert-koin:koin-core:3.4.1")
                implementation("io.insert-koin:koin-compose:1.0.4")
                // Koin for Ktor
                implementation("io.insert-koin:koin-ktor:3.4.1")
                implementation("io.insert-koin:koin-logger-slf4j:3.4.1")
                //ktor-no-way
                implementation("io.ktor:ktor-client-core:2.3.1")
                implementation("io.ktor:ktor-client-android:2.3.1")
                implementation("io.ktor:ktor-client-serialization:2.3.1")
                implementation("io.ktor:ktor-client-logging:2.3.1")
                implementation("io.ktor:ktor-client-okhttp:2.3.1")
//                implementation("ch.qos.logback:logback-classic:2.3.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                //datastore
                implementation("androidx.datastore:datastore-preferences:1.0.0")
                implementation ("androidx.datastore:datastore-preferences-core:1.0.0")
                //
//                implementation("androidx.appcompat:appcompat:1.4.1")
                //moko
                implementation("dev.icerock.moko:mvvm-core:0.16.1")
//                implementation("dev.icerock.moko:mvvm-flow-resources:0.16.1")
                implementation("dev.icerock.moko:mvvm-compose:0.16.1")
                implementation("dev.icerock.moko:mvvm-flow-compose:0.16.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Romchi-Desktop"
            packageVersion = "1.0.0"
        }
    }
}
