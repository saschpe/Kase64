pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    plugins {
        kotlin("multiplatform") version "1.9.23"
        id("com.android.library") version "8.2.2"
        `maven-publish`
        signing
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
