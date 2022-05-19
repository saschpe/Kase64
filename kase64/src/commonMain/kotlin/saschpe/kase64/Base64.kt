/*
 * Copyright 2022 Sascha Peilicke <sascha@peilicke.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package saschpe.kase64

/**
 * Encode a [String] to Base64 standard encoded [String].
 *
 * See [RFC 4648 §4](https://datatracker.ietf.org/doc/html/rfc4648#section-4)
 */
val String.base64Encoded: String
    get() = encodeInternal(Encoding.Standard)

/**
 * Encode a [ByteArray] to Base64 standard encoded [String].
 *
 * See [RFC 4648 §4](https://datatracker.ietf.org/doc/html/rfc4648#section-4)
 */
val ByteArray.base64Encoded: String
    get() = asCharArray().concatToString().base64Encoded

/**
 * Decode a Base64 standard encoded [String] to [String].
 *
 * See [RFC 4648 §4](https://datatracker.ietf.org/doc/html/rfc4648#section-4)
 */
val String.base64Decoded: String
    get() = decodeInternal(Encoding.Standard).map { it.toChar() }.joinToString("").dropLast(count { it == '=' })

/**
 * Decode a Base64 standard encoded [String] to [ByteArray].
 *
 * See [RFC 4648 §4](https://datatracker.ietf.org/doc/html/rfc4648#section-4)
 */
val String.base64DecodedBytes: ByteArray
    get() = decodeInternal(Encoding.Standard).map { it.toByte() }.toList().dropLast(count { it == '=' }).toByteArray()

/**
 * Decode a Base64 standard encoded [ByteArray] to [String].
 *
 * See [RFC 4648 §4](https://datatracker.ietf.org/doc/html/rfc4648#section-4)
 */
val ByteArray.base64Decoded: String
    get() = asCharArray().concatToString().base64Decoded
