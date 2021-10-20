package com.example.androidkotlinproject.data.manager

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

class HashMd5Manager {
    fun useMD5(str: String): String {
        return md5(str).toHex()
    }

    fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
}