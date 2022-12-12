package com.isabel.aimCrafter.rest.model

data class UserSignUp(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
)

data class ResponseUser(
    val firstName: String,
    val lastName: String,
    val username: String
)

data class UserSignInResponse(
    val user: ResponseUser,
    val token: String
)

data class UserSignIn(
    val email: String,
    var password: String
)

data class User(
    val username: String,
    val crafts: List<SimplifiedCraftResponse>
)