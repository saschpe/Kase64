@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
    }

    plugins {
        kotlin("multiplatform") version "2.4.0"
        id("com.android.kotlin.multiplatform.library") version "9.2.1"
        id("org.jetbrains.dokka") version "2.2.0"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
    }
}

rootProject.name = "Kase64"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0")
}

include(":kase64")
