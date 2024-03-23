plugins {
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.ben-manes.versions") version "0.51.0"
}

spotless {
    freshmark {
        target("**/*.md")
        propertiesFile("gradle.properties")
    }
    kotlin {
        target("**/*.kt")
        ktlint("1.2.1").setEditorConfigPath(".editorconfig")
    }
    kotlinGradle {
        ktlint("1.2.1").setEditorConfigPath(".editorconfig")
    }
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        fun isStable(version: String) = Regex("^[0-9,.v-]+(-r)?$").matches(version)
        !isStable(candidate.version) && isStable(currentVersion)
    }
}
