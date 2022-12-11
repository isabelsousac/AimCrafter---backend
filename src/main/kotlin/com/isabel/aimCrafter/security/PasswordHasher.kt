package com.isabel.aimCrafter.security

import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import java.util.*

@Component
class PasswordHasher {
    val SALT = "secret-salt12345".toByteArray(UTF_8)
    fun hashPassword(password: String): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(SALT)
        val digestedPassword = md.digest(password.toByteArray(UTF_8))
        return HexFormat.of().formatHex(digestedPassword)
    }
}