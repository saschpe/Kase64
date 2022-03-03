plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    android {
        compilations.all {
            java.sourceCompatibility = JavaVersion.VERSION_1_8
            java.targetCompatibility = JavaVersion.VERSION_1_8
            kotlinOptions.jvmTarget = "1.8"
        }
//        publishAllLibraryVariants()
    }
    ios()
    iosSimulatorArm64()
//    iosArm64 { binaries.framework("Kase64") }
//    iosX64 { binaries.framework("Kase64") }
    js {
//        browser {
//            testTask {
//                useKarma {
//                    useSafari()
//                    useChrome()
//                }
//            }
//        }
        nodejs()
//        compilations.all {
//            kotlinOptions.sourceMap = true
//            kotlinOptions.moduleKind = "umd"
//        }
    }
    jvm { testRuns["test"].executionTask.configure { useJUnitPlatform() } }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
    sourceSets["iosSimulatorArm64Main"].dependsOn(sourceSets["iosMain"])
    sourceSets["iosSimulatorArm64Test"].dependsOn(sourceSets["iosTest"])

    sourceSets.remove(sourceSets["androidAndroidTestRelease"]) // https://issuetracker.google.com/issues/152187160

//    tasks {
//        register("universalFrameworkDebug", org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask::class) {
//            baseName = "Kase64"
//            from(
//                iosArm64().binaries.getFramework("Kase64", "Debug"),
//                iosX64().binaries.getFramework("Kase64", "Debug")
//            )
//            destinationDir = buildDir.resolve("bin/universal/debug")
//            group = "Universal framework"
//            description = "Builds a universal (fat) debug framework"
//            dependsOn("linkKase64DebugFrameworkIosArm64")
//            dependsOn("linkKase64DebugFrameworkIosX64")
//        }
//
//        register("universalFrameworkRelease", org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask::class) {
//            baseName = "Kase64"
//            from(
//                iosArm64().binaries.getFramework("Kase64", "Release"),
//                iosX64().binaries.getFramework("Kase64", "Release")
//            )
//            destinationDir = buildDir.resolve("bin/universal/release")
//            group = "Universal framework"
//            description = "Builds a universal (fat) release framework"
//            dependsOn("linkKase64ReleaseFrameworkIosArm64")
//            dependsOn("linkKase64ReleaseFrameworkIosX64")
//        }
//
//        register("universalFramework") {
//            dependsOn("universalFrameworkDebug")
//            dependsOn("universalFrameworkRelease")
//        }
//    }
}

android {
    buildToolsVersion = "32.0.0"
    compileSdk = 31

    defaultConfig {
        minSdk = 17
        targetSdk = 31
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

group = "de.peilicke.sascha"
version = "1.0.0"

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar.get())

        pom {
            name.set("Kase64")
            description.set("Base64 encoder/decoder for Kotlin/Multiplatform. Supports Android, iOS, JavaScript and plain JVM environments.")
            url.set("https://github.com/saschpe/kase64")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://opensource.org/licenses/Apache-2.0")
                }
            }
            developers {
                developer {
                    id.set("saschpe")
                    name.set("Sascha Peilicke")
                    email.set("sascha@peilicke.de")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/saschpe/kase64.git")
                developerConnection.set("scm:git:ssh://github.com/saschpe/kase64.git")
                url.set("https://github.com/saschpe/kase64")
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            credentials {
                username = Secrets.Sonatype.user
                password = Secrets.Sonatype.apiKey
            }
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
        }
    }
}

signing {
    val sonatypeGpgKey = System.getenv("SONATYPE_GPG_KEY")
    val sonatypeGpgKeyPassword = System.getenv("SONATYPE_GPG_KEY_PASSWORD")
    when {
        sonatypeGpgKey == null || sonatypeGpgKeyPassword == null -> useGpgCmd()
        else -> useInMemoryPgpKeys(sonatypeGpgKey, sonatypeGpgKeyPassword)
    }
    sign(publishing.publications)
}
