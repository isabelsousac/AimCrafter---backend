package com.isabel.aimCrafter.db

import com.isabel.aimCrafter.db.model.ArraySqlValue
import com.isabel.aimCrafter.db.model.CraftDb
import com.isabel.aimCrafter.db.model.ShowCraftDb
import com.isabel.aimCrafter.db.model.ShowCraftsDb
import com.isabel.aimCrafter.rest.model.NewCraft
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CraftDao(
    val jdbcTemplate: NamedParameterJdbcTemplate,
    val userDao: UserDao
) {
    fun createCraft(craft: NewCraft, userId: Long): CraftDb {
        return jdbcTemplate.queryForObject(
            """
                INSERT INTO crafts (title, userId, tools, description, minutesToCreate, difficultyLevel, image)
                VALUES (:title, :userId, :tools, :description, :minutesToCreate, :difficultyLevel, :image)
                RETURNING id, title, userId, tools, description, minutesToCreate, difficultyLevel, createdAt, updatedAt, image
            """,
            mapOf(
                "title" to craft.title,
                "userId" to userId,
                "tools" to ArraySqlValue(craft.tools.toTypedArray(), "text"),
                "description" to craft.description,
                "minutesToCreate" to craft.minutesToCreate,
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
                minutesToCreate = Duration.ofMinutes(rs.getInt("minutesToCreate").toLong()),
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
                    title, tools, description, minutesToCreate, difficultyLevel, createdAt, image,
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
                minutesToCreate = Duration.ofMinutes(rs.getInt("minutesToCreate").toLong()),
                difficultyLevel = rs.getInt("difficultyLevel"),
                createdAt = rs.getTimestamp("createdAt").toInstant(),
                image = rs.getString("image"),
                username = rs.getString("username")
            )
        }.singleOrNull()
    }

    fun deleteCraft(id: Long, userId: Int): Boolean? {
        try {
            jdbcTemplate.update(
                """
                    DELETE FROM crafts 
                    WHERE id = :id AND userId = :userId
                """,
                mapOf("id" to id, "userId" to userId)
            )
        } catch (e: Exception) {
            return null
        }
        return true
    }

    fun showCrafts(): List<ShowCraftsDb> {
        return jdbcTemplate.query(
            """
                 SELECT id, title, image, (SELECT username FROM users WHERE users.id = crafts.userId) AS username
                 FROM crafts
            """
        ) { rs, _ ->
            ShowCraftsDb(
                title = rs.getString("title"),
                image = rs.getString("image"),
                username = rs.getString("username"),
                id = rs.getLong("id")
            )
        }
    }

    fun searchFilteredCrafts(filteredTools: List<String>): List<ShowCraftsDb> {
        return jdbcTemplate.query(
            """
                 SELECT id, title, image, (SELECT username FROM users WHERE users.id = crafts.userId) AS username
                 FROM crafts
                 WHERE
                 tools && :filteredTools;
            """,
            mapOf("filteredTools" to ArraySqlValue(filteredTools.toTypedArray(), "text"))
        ) { rs, _ ->
            ShowCraftsDb(
                title = rs.getString("title"),
                image = rs.getString("image"),
                username = rs.getString("username"),
                id = rs.getLong("id")
            )
        }
    }
}