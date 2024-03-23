pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    plugins {
        kotlin("multiplatform") version "1.9.23"
        id("com.android.library") version "8.2.2"
        id("org.jetbrains.dokka") version "1.9.20"
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        google()
    }
}

rootProject.name = "Kase64"

include(":kase64")
