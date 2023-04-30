package com.isabel.aimCrafter.security

import java.io.FileOutputStream
import java.security.KeyPairGenerator


/** Run this to generate a key and write it to the console **/
fun main() {
    generateKeys()
}
fun generateKeys() {
    val generator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(2048)
    val pair = generator.generateKeyPair()
    val privateKey = pair.private
    val publicKey = pair.public
    println("privatekey format: ${privateKey.format}")
    println("public format: ${privateKey.format}")
    FileOutputStream("src/main/resources/public.key").use { fos -> fos.write(publicKey.encoded) }
    FileOutputStream("src/main/resources/private.key").use { fos -> fos.write(privateKey.encoded) }
}