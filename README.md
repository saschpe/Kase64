# Kase64

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.9.10-B125EA?logo=kotlin)](https://kotlinlang.org)
[![Maven Central](https://img.shields.io/maven-central/v/de.peilicke.sascha/kase64.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/de.peilicke.sascha/kase64)
[![Build Status](https://github.com/saschpe/kase64/workflows/Main/badge.svg)](https://github.com/saschpe/kase64/actions)
[![License](http://img.shields.io/:License-Apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![badge-android](http://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android)
![badge-ios](http://img.shields.io/badge/Platform-iOS-orange.svg?logo=apple)
![badge-js](http://img.shields.io/badge/Platform-NodeJS-yellow.svg?logo=javascript)
![badge-jvm](http://img.shields.io/badge/Platform-JVM-red.svg?logo=openjdk)
![badge-linux](http://img.shields.io/badge/Platform-Linux-lightgrey.svg?logo=linux)
![badge-macos](http://img.shields.io/badge/Platform-macOS-orange.svg?logo=apple)
![badge-windows](http://img.shields.io/badge/Platform-Windows-blue.svg?logo=windows)

[//]: # (![badge-tvos]&#40;http://img.shields.io/badge/Platform-tvOS-orange.svg?logo=apple&#41;)

[//]: # (![badge-watchos]&#40;http://img.shields.io/badge/Platform-watchOS-orange.svg?logo=apple&#41;)

Base64 encoder/decoder for Kotlin/Multiplatform. Supports Android, iOS, Linux, JavaScript, Windows, watchOS, tvOS
and plain JVM environments.

**Supported encodings**

- [Standard (Rfc 4648 section 4)](https://www.ietf.org/rfc/rfc4648.html#section-4)
- [URL-safe (Rfc 4648 section 5)](https://www.ietf.org/rfc/rfc4648.html#section-5)

## Download

Artifacts are published to [Maven Central][maven-central]:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("de.peilicke.sascha:kase64:1.2.1")
}
```

## Usage

Standard Base64 decoding and encoding:

```kotlin
import saschpe.kase64.*

val helloWorld = "SGVsbG8sIHdvcmxkIQ==".base64Decoded // "Hello, world!"
println("Hello, world!".base64Encoded) // Prints "SGVsbG8sIHdvcmxkIQ=="
```

URL-safe Base64 decoding and encoding:

```kotlin
import saschpe.kase64.*

val helloWorld = "SGVsbG8sIHdvcmxkIQ".base64UrlDecoded // "Hello, world!"
println("Hello, world!".base64UrlEncoded) // Prints "SGVsbG8sIHdvcmxkIQ"
```

## License

    Copyright 2022 Sascha Peilicke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[maven-central]: https://search.maven.org/artifact/de.peilicke.sascha/kase64
