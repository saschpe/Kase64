plugins {
    kotlin("jvm") version "1.7.10" apply false
    id("com.android.library") version "7.2.2" apply false
    id("com.diffplug.spotless") version "6.9.0"
    id("com.github.ben-manes.versions") version "0.42.0"
}

spotless {
    format("misc") {
        target("**/*.gradle", "*.md", "**/.gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }
    freshmark {
        target("*.md")
        propertiesFile("gradle.properties")
    }
    kotlin {
        target("source/**/src/**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint().editorConfigOverride(
            mapOf("disabled_rules" to "filename,no-wildcard-imports", "insert_final_newline" to false)
        )
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint().editorConfigOverride(mapOf("insert_final_newline" to false))
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