package com.isabel.aimCrafter.security

import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

@Component
class PasswordHasher {
    val RANDOM = SecureRandom()

    fun hashPassword(password: String): SaltPass {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        val salt = getNextSalt()
        md.update(salt)
        val digestedPassword = md.digest(password.toByteArray(UTF_8))
        return SaltPass(password = HexFormat.of().formatHex(digestedPassword), salt = HexFormat.of().formatHex(salt))
    }

    fun isValidPassword(loginPassword: String, fetchedPassword: String, salt: String): Boolean {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(HexFormat.of().parseHex(salt))
        val digestedPassword = md.digest(loginPassword.toByteArray(UTF_8))
        return digestedPassword.contentEquals(HexFormat.of().parseHex(fetchedPassword))
    }

    fun getNextSalt(): ByteArray {
        val salt = ByteArray(16)
        RANDOM.nextBytes(salt)
        return salt
    }
}

data class SaltPass(
    val password: String,
    val salt: String
)