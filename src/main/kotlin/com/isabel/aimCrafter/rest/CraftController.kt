package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.CraftDao
import com.isabel.aimCrafter.rest.model.*
import com.isabel.aimCrafter.security.JWTTokens
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CraftController(
    val craftDao: CraftDao,
    val jwtTokens: JWTTokens
) {
    @PostMapping("/craft")
    @ResponseStatus(HttpStatus.CREATED)
    fun newCraft(
        @RequestBody craft: NewCraft,
        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?
    ): CraftResponseNew {
        authorization ?: throw NotLoggedInException()
        if (!authorization.startsWith("Bearer ")) throw NotLoggedInException()
        val jwtToken = authorization.substringAfter("Bearer ")
        val userId = jwtTokens.validateToken(jwtToken) ?: throw NotLoggedInException()

        val craftCreated = craftDao.createCraft(craft, userId)

        return CraftResponseNew(
            title = craftCreated.title,
            tools = craftCreated.tools,
            description = craftCreated.description,
            timeToCreate = craftCreated.timeToCreate,
            difficultyLevel = craftCreated.difficultyLevel,
            image = craftCreated.image,
            createdAt = craftCreated.createdAt
        )
    }

    @GetMapping("/craft/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun showCraft(@PathVariable id: Long): CraftResponseShow {
        val craftCreated = craftDao.showCraft(id) ?: throw CraftNotFoundException()
        return CraftResponseShow(
            title = craftCreated.title,
            tools = craftCreated.tools,
            description = craftCreated.description,
            timeToCreate = craftCreated.timeToCreate,
            difficultyLevel = craftCreated.difficultyLevel,
            image = craftCreated.image,
            username = craftCreated.username,
            createdAt = craftCreated.createdAt
        )
    }
}