package com.isabel.aimCrafter.rest.model

import java.time.Duration
import java.time.Instant

data class NewCraft(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val minutesToCreate: Int? = null,
    val difficultyLevel: Int? = null,
    val image: String
)

data class CraftResponseNew(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val minutesToCreate: Duration? = null,
    val difficultyLevel: Int? = null,
    val image: String,
    val createdAt: Instant,
    val id: Long
)

data class CraftResponseShow(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val minutesToCreate: Duration? = null,
    val difficultyLevel: Int? = null,
    val image: String,
    val username: String,
    val createdAt: Instant
)

data class CraftsResponseList(
    val title: String,
    val image: String,
    val username: String,
    val id: Long
)

data class SimplifiedCraftResponse(
    val title: String,
    val image: String,
    val createdAt: Instant
)