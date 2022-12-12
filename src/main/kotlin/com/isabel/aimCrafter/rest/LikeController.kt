package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.LikeDao
import com.isabel.aimCrafter.rest.model.CraftInfo
import com.isabel.aimCrafter.rest.model.LikeInfo
import com.isabel.aimCrafter.rest.model.NotLoggedInException
import com.isabel.aimCrafter.security.JWTTokens
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class LikeController(
    val likeDao: LikeDao,
    val jwtTokens: JWTTokens
) {
    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun postLike(
        @RequestBody likeInfo: LikeInfo,
        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?
    ): CraftInfo {
        // maybe create a constraint that if craft id belongs to user id, like is not allowed
        authorization ?: throw NotLoggedInException()
        val jwtToken = authorization.substringAfter("Bearer ")
        val userId = jwtTokens.validateToken(jwtToken) ?: throw NotLoggedInException()
        val craftLiked = likeDao.postLike(likeInfo, userId)

        return CraftInfo(
            craftId = craftLiked.craftId,
            likesCount = craftLiked.likesCount
        )
    }

    @DeleteMapping("/dislike")
    @ResponseStatus(HttpStatus.OK)
    fun removeLike(
        @RequestBody likeInfo: LikeInfo,
        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?
    ): CraftInfo {
        authorization ?: throw NotLoggedInException()
        val jwtToken = authorization.substringAfter("Bearer ")
        val userId = jwtTokens.validateToken(jwtToken) ?: throw NotLoggedInException()
        val craftDisliked = likeDao.deleteLike(likeInfo, userId)

        return CraftInfo(
            craftId = craftDisliked.craftId,
            likesCount = craftDisliked.likesCount
        )
    }
}