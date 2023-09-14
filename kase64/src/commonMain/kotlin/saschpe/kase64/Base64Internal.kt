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
 * Base64 encoding scheme
 */
internal sealed interface Encoding {
    val alphabet: String
    val requiresPadding: Boolean

    data object Standard : Encoding {
        override val alphabet: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
        override val requiresPadding: Boolean = true
    }

    data object UrlSafe : Encoding {
        override val alphabet: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"
        override val requiresPadding: Boolean = false // Padding is optional
    }
}

internal fun String.encodeInternal(encoding: Encoding): String {
    val padLength = when (length % 3) {
        1 -> 2
        2 -> 1
        else -> 0
    }
    val raw = this + 0.toChar().toString().repeat(maxOf(0, padLength))
    val encoded = raw.chunkedSequence(3) {
        Triple(it[0].code, it[1].code, it[2].code)
    }.map { (first, second, third) ->
        (0xFF.and(first) shl 16) + (0xFF.and(second) shl 8) + 0xFF.and(third)
    }.map { n ->
        sequenceOf((n shr 18) and 0x3F, (n shr 12) and 0x3F, (n shr 6) and 0x3F, n and 0x3F)
    }.flatten()
        .map { encoding.alphabet[it] }
        .joinToString("")
        .dropLast(padLength)
    return when (encoding.requiresPadding) {
        true -> encoded.padEnd(encoded.length + padLength, '=')
        else -> encoded
    }
}

internal fun String.decodeInternal(encoding: Encoding): Sequence<Int> {
    val padLength = when (length % 4) {
        1 -> 3
        2 -> 2
        3 -> 1
        else -> 0
    }
    return padEnd(length + padLength, '=')
        .replace("=", "A")
        .chunkedSequence(4) {
            (encoding.alphabet.indexOf(it[0]) shl 18) + (encoding.alphabet.indexOf(it[1]) shl 12) +
                (encoding.alphabet.indexOf(it[2]) shl 6) + encoding.alphabet.indexOf(it[3])
        }
        .map { sequenceOf(0xFF.and(it shr 16), 0xFF.and(it shr 8), 0xFF.and(it)) }
        .flatten()
}

internal fun ByteArray.asCharArray(): CharArray {
    val chars = CharArray(size)
    for (i in chars.indices) {
        chars[i] = get(i).toInt().toChar()
    }
    return chars
}
