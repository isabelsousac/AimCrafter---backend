package com.isabel.aimCrafter.db

import com.isabel.aimCrafter.db.model.UserDb
import com.isabel.aimCrafter.rest.model.UserSignIn
import com.isabel.aimCrafter.rest.model.UserSignUp
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class UserDao(
    val jdbcTemplate: NamedParameterJdbcTemplate
) {
    fun signUp(user: UserSignUp): UserDb {
        return jdbcTemplate.queryForObject(
            "INSERT INTO users (firstName, lastName, username, email, passwordDigest) VALUES (:firstName, :lastName, :username, :email, :passwordDigest)" +
                    "RETURNING id, username, firstName, lastName, email, passwordDigest, createdAt, updatedAt",
            mapOf(
                "firstName" to user.firstName,
                "lastName" to user.lastName,
                "username" to user.username,
                "email" to user.email,
                "passwordDigest" to user.password
            )
        ) { rs, _ ->
            UserDb(
                id = rs.getLong("id"),
                username = rs.getString("username"),
                firstName = rs.getString("firstName"),
                lastName = rs.getString("lastName"),
                email = rs.getString("email"),
                passwordDigest = rs.getString("passwordDigest"),
                createdAt = rs.getTimestamp("createdAt"),
                updatedAt = rs.getTimestamp("updatedAt")
            )
        }!!
    }

    fun signIn(user: UserSignIn): UserDb {
        return jdbcTemplate.queryForObject(
            "SELECT id, firstName, lastName, userName, email, passwordDigest " +
                    "FROM users WHERE email = :email, passwordDigest = :passwordDigest" +
                    "RETURNING id, username, firstName, lastName, email, passwordDigest, createdAt, updatedAt",
            mapOf(
                "email" to user.email,
                "passwordDigest" to user.password,
            )
        ) { rs, _ ->
            UserDb(
                id = rs.getLong("id"),
                username = rs.getString("username"),
                firstName = rs.getString("firstName"),
                lastName = rs.getString("lastName"),
                email = rs.getString("email"),
                passwordDigest = rs.getString("passwordDigest"),
                createdAt = rs.getTimestamp("createdAt"),
                updatedAt = rs.getTimestamp("updatedAt")
            )
        }!!
    }
}