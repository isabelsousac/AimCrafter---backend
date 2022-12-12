package com.isabel.aimCrafter.db

import com.isabel.aimCrafter.db.model.LikeDb
import com.isabel.aimCrafter.rest.model.LikeInfo
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class LikeDao(
    val jdbcTemplate: NamedParameterJdbcTemplate
) {

    fun postLike(likeInfo: LikeInfo, userId: Long): LikeDb {
        try {
            jdbcTemplate.update(
                """
                    INSERT INTO likes (craftId, userId) 
                    VALUES (:craftId, :userId)
                """,
                mapOf("craftId" to likeInfo.craftId, "userId" to userId)
            )
        } catch (_: DataIntegrityViolationException) {

        }

        return LikeDb(
            craftId = likeInfo.craftId,
            likesCount = getNumberOfLikes(likeInfo.craftId)
        )
    }

    fun deleteLike(likeInfo: LikeInfo, userId: Long): LikeDb {
        jdbcTemplate.update(
            """
                    DELETE FROM likes 
                    WHERE craftId = :craftId AND userId = :userId
                """,
            mapOf("craftId" to likeInfo.craftId, "userId" to userId)
        )

        return LikeDb(
            craftId = likeInfo.craftId,
            likesCount = getNumberOfLikes(likeInfo.craftId)
        )
    }

    fun getNumberOfLikes(craftId: Long): Long {
        return jdbcTemplate.queryForObject(
            """
               SELECT COUNT(*) 
               FROM likes
               WHERE craftId = :craftId
            """,
            mapOf("craftId" to craftId),
            Long::class.java
        )!!
    }
}