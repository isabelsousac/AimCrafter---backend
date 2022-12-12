package com.isabel.aimCrafter.rest.model

import java.time.Instant

data class NewCraft(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val timeToCreate: Instant? = null,
    val difficultyLevel: Int? = null,
    val image: String
)

data class CraftResponseNew(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val timeToCreate: Instant? = null,
    val difficultyLevel: Int? = null,
    val image: String,
    val createdAt: Instant
)

data class CraftResponseShow(
    val title: String,
    val tools: List<String>,
    val description: String? = null,
    val timeToCreate: Instant? = null,
    val difficultyLevel: Int? = null,
    val image: String,
    val username: String,
    val createdAt: Instant
)

data class SimplifiedCraftResponse(
    val title: String,
    val image: String,
    val createdAt: Instant
)