plugins {
    kotlin("jvm") version "1.7.10" apply false
    id("com.android.library") version "7.2.1" apply false
    id("com.diffplug.spotless") version "6.7.2"
    id("com.github.ben-manes.versions") version "0.42.0"
}

spotless {
    format("misc") {
        target("*.md", "**/.gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }
    freshmark {
        target("*.md")
    }
    kotlin {
        target("*/src/**/*.kt")
        targetExclude("*/build/**/*.kt")
        ktlint().userData(mapOf("disabled_rules" to "no-wildcard-imports"))
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint()
    }
}

tasks {
    withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        rejectVersionIf {
            fun isStable(version: String) = Regex("^[0-9,.v-]+(-r)?$").matches(version)
            !isStable(candidate.version) && isStable(currentVersion)
        }
    }
}
