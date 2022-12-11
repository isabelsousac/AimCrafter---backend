package com.isabel.aimCrafter.rest.model

import java.time.LocalDate
import kotlin.time.Duration

data class NewCraft(
    val title: String,
    val userId: Int,
    val tools: List<String>,
    val description: String?,
    val howLong: Duration?,
    val difficultyLevel: Int?,
    val token: String // I think I need this, because a user can only post if they have an account
)

data class CraftResponse(
    val title: String,
    val tools: List<String>,
    val description: String?,
    val howLong: Duration,
    val difficultyLevel: Int?,
    val username: String
)

data class SimplifiedCraftResponse(
    val title: String,
    val image: String?,
    val createdAt: LocalDate
)