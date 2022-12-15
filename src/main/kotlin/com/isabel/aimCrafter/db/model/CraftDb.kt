package com.isabel.aimCrafter.db.model

import java.time.Duration
import java.time.Instant

data class CraftDb(
    val id: Long,
    val title: String,
    val userId: Long,
    val tools: List<String>,
    val description: String?,
    val minutesToCreate: Duration?,
    val difficultyLevel: Int?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val image: String
)

data class ShowCraftDb(
    val title: String,
    val tools: List<String>,
    val description: String?,
    val minutesToCreate: Duration?,
    val difficultyLevel: Int?,
    val createdAt: Instant,
    val image: String,
    val username: String
)

data class ShowCraftsDb(
    val title: String,
    val username: String,
    val image: String,
    val id: Long
)