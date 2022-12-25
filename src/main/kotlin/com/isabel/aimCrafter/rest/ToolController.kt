package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.CraftDao
import com.isabel.aimCrafter.rest.model.CraftsResponseList
import com.isabel.aimCrafter.rest.model.Tools
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ToolController(
    val craftDao: CraftDao,
) {
    @GetMapping("/pickedCrafts")
    @ResponseStatus(HttpStatus.OK)
    fun craftsByTools(@RequestBody tools: Tools): List<CraftsResponseList> {
        val toolsLowercase = tools.tools.map { it.lowercase() }
        val fetchedCrafts = craftDao.searchFilteredCrafts(toolsLowercase)

        return fetchedCrafts.map { craft ->
            CraftsResponseList(
                title = craft.title,
                image = craft.image,
                username = craft.username,
                id = craft.id
            )
        }
    }
}