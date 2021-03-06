# Kase64

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![Maven Central](https://img.shields.io/maven-central/v/de.peilicke.sascha/kase64)
[![Build Status](https://github.com/saschpe/kase64/workflows/Main%20CI/badge.svg)](https://github.com/saschpe/kase64/actions)
![badge-android](http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-brightgreen.svg?style=flat)
![badge-native](http://img.shields.io/badge/platform-native-lightgrey.svg?style=flat)
![badge-js](http://img.shields.io/badge/platform-js-yellow.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/platform-jvm-orange.svg?style=flat)
![Kotlin Version](https://img.shields.io/badge/kotlin-v1.3.60-F88909?style=flat&logo=kotlin)

Base64 encoder/decoder for Kotlin/Multiplatform. Supports Android, iOS, JavaScript and plain JVM environments.

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
    implementation("de.peilicke.sascha:kase64:1.0.5")
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
