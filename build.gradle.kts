plugins {
    kotlin("jvm") version "1.9.10"
    id("com.android.library") version "8.1.0-rc01" apply false
    id("com.diffplug.spotless") version "6.21.0"
    id("com.github.ben-manes.versions") version "0.47.0"
}

spotless {
    freshmark {
        target("**/*.md")
        propertiesFile("gradle.properties")
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}
