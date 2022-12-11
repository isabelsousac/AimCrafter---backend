package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.rest.model.CraftInfo
import com.isabel.aimCrafter.rest.model.LikeInfo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class LikeController(
    // create Dao
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postLike(@RequestBody likeInfo: LikeInfo): CraftInfo {
        // create query
        // maybe create a constraint that if craft id belongs to user id, like is not allowed
        return CraftInfo(
            craftId = 2,
            likesCount = 4
        )
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun removeLike(@RequestBody likeInfo: LikeInfo): CraftInfo {
        // create query
        return CraftInfo(
            craftId = 2,
            likesCount = 4
        )
    }
}