package com.isabel.aimCrafter.db

import com.isabel.aimCrafter.db.model.ArraySqlValue
import com.isabel.aimCrafter.db.model.CraftDb
import com.isabel.aimCrafter.db.model.ShowCraftDb
import com.isabel.aimCrafter.rest.model.NewCraft
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class CraftDao(
    val jdbcTemplate: NamedParameterJdbcTemplate,
    val userDao: UserDao
) {
    fun createCraft(craft: NewCraft, userId: Long): CraftDb {
        return jdbcTemplate.queryForObject(
            """
                INSERT INTO crafts (title, userId, tools, description, timeToCreate, difficultyLevel, image)
                VALUES (:title, :userId, :tools, :description, :timeToCreate, :difficultyLevel, :image)
                RETURNING id, title, userId, tools, description, timeToCreate, difficultyLevel, createdAt, updatedAt, image
            """,
            mapOf(
                "title" to craft.title,
                "userId" to userId,
                "tools" to ArraySqlValue(craft.tools.toTypedArray(), "text"),
                "description" to craft.description,
                "timeToCreate" to craft.timeToCreate,
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
                timeToCreate = rs.getTimestamp("timeToCreate")?.toInstant(),
                difficultyLevel = rs.getInt("difficultyLevel"),
                image = rs.getString("image"),
                createdAt = rs.getTimestamp("createdAt").toInstant(),
                updatedAt = rs.getTimestamp("updatedAt").toInstant()
            )
        }!!
    }

    fun showCraft(id: Long): ShowCraftDb? {
        return jdbcTemplate.query(
            """
                SELECT
                    title, tools, description, timeToCreate, difficultyLevel, createdAt, image,
                    (SELECT username FROM users WHERE users.id = crafts.userId) AS username
                FROM crafts
                WHERE id = :id
            """,
            mapOf("id" to id)
        ) { rs, _ ->
            ShowCraftDb(
                title = rs.getString("title"),
                tools = (rs.getArray("tools").array as Array<String>).toList(),
                description = rs.getString("description"),
                timeToCreate = rs.getTimestamp("timeToCreate")?.toInstant(),
                difficultyLevel = rs.getInt("difficultyLevel"),
                createdAt = rs.getTimestamp("createdAt").toInstant(),
                image = rs.getString("image"),
                username = rs.getString("username")
            )
        }.singleOrNull()
    }
}