package com.isabel.aimCrafter.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.nio.file.Files
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.time.Instant


@Component
class JWTTokens {
    private final val algorithm: Algorithm

    init {
        val publicKeyFile = ResourceUtils.getFile("classpath:public.key")
        val privateKeyFile = ResourceUtils.getFile("classpath:private.key")
        val publicKeyBytes: ByteArray = Files.readAllBytes(publicKeyFile.toPath())
        val privateKeyBytes: ByteArray = Files.readAllBytes(privateKeyFile.toPath())
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        val publicKeySpec: EncodedKeySpec = X509EncodedKeySpec(publicKeyBytes)
        val privateKeySpec: EncodedKeySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val publicKey = keyFactory.generatePublic(publicKeySpec)
        val privateKey = keyFactory.generatePrivate(privateKeySpec)
        algorithm = Algorithm.RSA256(publicKey as RSAPublicKey, privateKey as RSAPrivateKey)
    }

    val verifier = JWT.require(algorithm)
        .withIssuer("aimcrafter")
        .build()

    fun generateToken(userId: Long): String =
        JWT.create()
            .withIssuer("aimcrafter")
            .withClaim("userId", userId.toString())
            .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 10)) // 10 days
            .sign(algorithm)

    fun validateToken(token: String?): Long? {
        token ?: return null
        val decodedToken = try {
            verifier.verify(token)
        } catch (e: Exception) {
            return null
        }

        return decodedToken.claims["userId"]?.asString()?.toLong()
    }

}