package com.isabel.aimCrafter.security

import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

@Component
class PasswordHasher {
    val RANDOM = SecureRandom()
    fun hashPassword(password: String): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(getNextSalt())
        val digestedPassword = md.digest(password.toByteArray(UTF_8))
        return HexFormat.of().formatHex(digestedPassword)
    }

    fun getNextSalt(): ByteArray {
        val salt = ByteArray(16)
        RANDOM.nextBytes(salt)
        return salt
    }
}