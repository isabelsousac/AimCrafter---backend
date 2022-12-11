package com.isabel.aimCrafter.db.model

import java.sql.Timestamp


data class UserDb(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val passwordDigest: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)