# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.1.1] - 2024-03-23
- Provide javadoc artifacts for Sonatype Maven Central

## [1.1.0] - 2023-09-14
### Changed
- Apple: Add targets for `tvOS` and `watchOS` and `macOS`
- Dependency update:
  - [Gradle-8.3](https://docs.gradle.org/8.3/release-notes.html)

## [1.0.7] - 2023-09-07
### Changed
- Android: Compile with API level 33 / Android 13
- Dependency update:
  - [Android Gradle Plugin 8.1.0-rc01](https://developer.android.com/studio/releases/gradle-plugin#7-2-0)
  - [Gradle-7.5.1](https://docs.gradle.org/7.5.1/release-notes.html)
  - [Kotlin 1.9.10](https://github.com/JetBrains/kotlin/releases/tag/v1.9.10)

## [1.0.6] - 2022-07-31
- Dependency update:
  - [Android Gradle Plugin 7.2.1](https://developer.android.com/studio/releases/gradle-plugin#7-2-0)
  - [Kotlin 1.7.10](https://github.com/JetBrains/kotlin/releases/tag/v1.7.10)
  - [Gradle-7.5](https://docs.gradle.org/7.5/release-notes.html)
  - [Spotless-6.9.0](https://github.com/diffplug/spotless/blob/main/plugin-gradle/CHANGES.md#690---2022-07-28)
- Add targets: linuxX64, macosArm64, mingwX64
  - Stop building frameworks, they aren't published by default

## [1.0.5] - 2022-05-19
### Added
- Implement Base64 URL-safe encoding according to [RFC 4648 §5](https://datatracker.ietf.org/doc/html/rfc4648#section-5)

## [1.0.4] - 2022-04-26
### Changed
- Dependency update:
  - [Kotlin-1.6.21](https://github.com/JetBrains/kotlin/releases/tag/v1.6.21)
  - [Gradle-7.4.2](https://docs.gradle.org/7.4.2/release-notes.html)

## [1.0.3] - 2022-03-10
### Changed
- Gitlab: Publish Android variants, fix iOS simulator tests

## [1.0.2] - 2022-03-04
### Added
- Kotlin/Multiplatform: Enable JavaScript for NodeJS

## [1.0.1] - 2022-03-04
### Added
- GPG sign releases

## [1.0.0] - 2022-03-04
### Added
- Initial implementation of Base64 standard encoding
