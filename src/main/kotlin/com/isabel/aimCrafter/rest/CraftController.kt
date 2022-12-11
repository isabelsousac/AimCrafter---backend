package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.CraftDao
import com.isabel.aimCrafter.rest.model.CraftNotFoundException
import com.isabel.aimCrafter.rest.model.CraftResponseNew
import com.isabel.aimCrafter.rest.model.CraftResponseShow
import com.isabel.aimCrafter.rest.model.NewCraft
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CraftController(
    val craftDao: CraftDao
) {
    @PostMapping("/craft/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun newCraft(@RequestBody craft: NewCraft, @PathVariable userId: Long): CraftResponseNew {
        val craftCreated = craftDao.createCraft(craft, userId)

        return CraftResponseNew(
            title = craftCreated.title,
            tools = craftCreated.tools,
            description = craftCreated.description,
            howLong = craftCreated.howLong,
            difficultyLevel = craftCreated.difficultyLevel,
            image = craftCreated.image
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
            howLong = craftCreated.howLong,
            difficultyLevel = craftCreated.difficultyLevel,
            image = craftCreated.image,
            username = craftCreated.username
        )
    }
}