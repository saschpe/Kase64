plugins {
    id("com.diffplug.spotless") version "6.21.0"
    id("com.github.ben-manes.versions") version "0.48.0"
}

spotless {
    freshmark {
        target("**/*.md")
        propertiesFile("gradle.properties")
    }
    kotlin {
        target("**/*.kt")
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}
