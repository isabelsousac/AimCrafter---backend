package com.isabel.aimCrafter.db.model

import com.isabel.aimCrafter.rest.model.SimplifiedCraftResponse
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

data class UserAndCraftsDb(
    val username: String,
    val crafts: List<SimplifiedCraftResponse>
)

data class UserInfoDb(
    val id: Long,
    val username: String
)

data class UsernameDb(
    val username: String
)