plugins {
    kotlin("multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("com.vanniktech.maven.publish") version "0.37.0"
    id("org.jetbrains.dokka")
}

kotlin {
    jvmToolchain(25)

    android {
        namespace = "saschpe.kase64"
        compileSdk = 36
        minSdk = 17
        withHostTest { isIncludeAndroidResources = true }
        testCoverage.jacocoVersion = "0.8.13"
    }
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    js { nodejs() }
    jvm()
    linuxX64()
    macosArm64()
    mingwX64() // Win-what ?!?
    tvosArm64()
    tvosSimulatorArm64()
    watchosArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates("de.peilicke.sascha", name, version.toString())

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
