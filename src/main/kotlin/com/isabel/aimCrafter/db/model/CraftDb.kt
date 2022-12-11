package com.isabel.aimCrafter.db.model

import java.sql.Timestamp
import java.time.Instant

data class CraftDb(
    val id: Long,
    val title: String,
    val userId: Long,
    val tools: List<String>,
    val description: String?,
    val howLong: Instant,
    val difficultyLevel: Int?,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val image: String
)

data class ShowCraftDb(
    val title: String,
    val tools: List<String>,
    val description: String?,
    val howLong: Instant,
    val difficultyLevel: Int?,
    val createdAt: Timestamp,
    val image: String,
    val username: String
)

data class SimplifiedCraftDb(
    val title: String,
    val tools: List<String>,
    val description: String?,
    val howLong: Instant,
    val difficultyLevel: Int?,
    val createdAt: Timestamp,
    val image: String,
    val userId: Long
)