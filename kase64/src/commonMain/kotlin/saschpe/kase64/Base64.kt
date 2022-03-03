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

internal const val BASE64_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private val RX_BASE64_CLEANER = "[^=$BASE64_SET${"]".toRegex()}"

/**
 * Base64 encode a string.
 */
val String.base64Encoded: String
    get() {
        val pad = when (length % 3) {
            1 -> "=="
            2 -> "="
            else -> ""
        }
        val raw = this + 0.toChar().toString().repeat(maxOf(0, pad.length))
        return raw.chunkedSequence(3) {
            Triple(it[0].code, it[1].code, it[2].code)
        }.map { (first, second, third) ->
            (0xFF.and(first) shl 16) + (0xFF.and(second) shl 8) + 0xFF.and(third)
        }.map { n ->
            sequenceOf((n shr 18) and 0x3F, (n shr 12) and 0x3F, (n shr 6) and 0x3F, n and 0x3F)
        }.flatten()
            .map { BASE64_SET[it] }
            .joinToString("")
            .dropLast(pad.length) + pad
    }

fun ByteArray.asCharArray(): CharArray {
    val chars = CharArray(size)
    for (i in chars.indices) {
        chars[i] = get(i).toInt().toChar()
    }
    return chars
}

/**
 * Base64 encode a ByteArray to String.
 */
val ByteArray.base64Encoded: String
    get() = asCharArray().concatToString().base64Encoded

private fun String.base64DecodeInternal(): Sequence<Int> {
    require(length % 4 == 0) { "The string \"$this\" does not comply with Base64 length requirement." }
    return replace(RX_BASE64_CLEANER, "")
        .replace("=", "A")
        .chunkedSequence(4) {
            (BASE64_SET.indexOf(it[0]) shl 18) +
                (BASE64_SET.indexOf(it[1]) shl 12) +
                (BASE64_SET.indexOf(it[2]) shl 6) +
                BASE64_SET.indexOf(it[3])
        }
        .map { sequenceOf(0xFF.and(it shr 16), 0xFF.and(it shr 8), 0xFF.and(it)) }
        .flatten()
}

/**
 * Decode a Base64 string to String.
 */
val String.base64Decoded: String
    get() = base64DecodeInternal().map { it.toChar() }.joinToString("").dropLast(count { it == '=' })

/**
 * Decode a Base64 string to ByteArray.
 */
val String.base64DecodedBytes: ByteArray
    get() = base64DecodeInternal().map { it.toByte() }.toList().dropLast(count { it == '=' }).toByteArray()

/**
 * Decode a Base64 ByteArray to String.
 */
val ByteArray.base64Decoded: String
    get() = asCharArray().concatToString().base64Decoded
