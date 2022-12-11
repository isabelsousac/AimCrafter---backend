package com.isabel.aimCrafter.db

import com.isabel.aimCrafter.db.model.CraftDb
import com.isabel.aimCrafter.db.model.ShowCraftDb
import com.isabel.aimCrafter.db.model.SimplifiedCraftDb
import com.isabel.aimCrafter.rest.model.NewCraft
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class CraftDao(
    val jdbcTemplate: NamedParameterJdbcTemplate,
    val userDao: UserDao
) {
    fun createCraft(craft: NewCraft, userId: Int): CraftDb {
        return jdbcTemplate.queryForObject(
            """
                INSERT INTO crafts (title, userId, tools, description, howLong, difficultyLevel, image)
                VALUES (:title, :userId, :tools, :description, :howLong, :difficultyLevel, :image)
                RETURNING id, title, userId, tools, description, howLong, difficultyLevel, createdAt, updatedAt, image
            """,
            mapOf(
                "title" to craft.title,
                "userId" to craft.userId,
                "tools" to craft.tools,
                "description" to craft.description,
                "howLong" to craft.howLong,
                "difficultyLevel" to craft.difficultyLevel,
                "image" to craft.image
            )
        ) { rs, _ ->
            CraftDb(
                id = rs.getLong("id"),
                title = rs.getString("title"),
                userId = rs.getLong("userId"),
                tools = (rs.getArray("tools").array as Array<String>).toList(),
                description = rs.getString("description"),
                howLong = rs.getTimestamp("howLong").toInstant(),
                difficultyLevel = rs.getInt("difficultyLevel"),
                image = rs.getString("image"),
                createdAt = rs.getTimestamp("createdAt"),
                updatedAt = rs.getTimestamp("updatedAt")
            )
        }!!
    }

    fun showCraft(id: Long): ShowCraftDb? {
        val craft = jdbcTemplate.query(
            """
                SELECT (title, username, tools, description, howLong, difficultyLevel, createdAt, image, userId)
                FROM crafts
                WHERE id = :id
            """,
            mapOf("id" to id)
        ) { rs, _ ->
            SimplifiedCraftDb(
                title = rs.getString("title"),
                tools = (rs.getArray("tools").array as Array<String>).toList(),
                description = rs.getString("description"),
                howLong = rs.getTimestamp("howLong").toInstant(),
                difficultyLevel = rs.getInt("difficultyLevel"),
                createdAt = rs.getTimestamp("createdAt"),
                image = rs.getString("image"),
                userId = rs.getLong("userId")
            )
        }.singleOrNull() ?: return null

        val username = userDao.getUsername(craft.userId) ?: return null

        return ShowCraftDb(
            title = craft.title,
            username = username.username,
            tools = craft.tools,
            description = craft.description,
            howLong = craft.howLong,
            difficultyLevel = craft.difficultyLevel,
            createdAt = craft.createdAt,
            image = craft.image
        )
    }
}