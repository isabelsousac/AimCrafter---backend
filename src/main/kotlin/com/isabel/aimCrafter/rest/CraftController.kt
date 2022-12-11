package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.rest.model.CraftResponse
import com.isabel.aimCrafter.rest.model.NewCraft
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RestController
class CraftController(
    // need to call Dao
) {
    @PostMapping("/craft")
    @ResponseStatus(HttpStatus.CREATED)
    fun newCraft(@RequestBody craft: NewCraft): CraftResponse {
        // create query
        return CraftResponse(
            title = "Christmas card",
            tools = listOf(" brown paper card", "scissors", "pencil", "gold pen", "white pen"),
            description = "A simple christmas card that you can present your family members",
            howLong = 20.toDuration(DurationUnit.MINUTES),
            difficultyLevel = 4,
            username = "isabelsousa"
        )
    }

    @GetMapping("/craft/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun showCraft(@PathVariable craftId: Int): CraftResponse {
        // create query
        return CraftResponse(
            title = "Christmas card",
            tools = listOf(" brown paper card", "scissors", "pencil", "gold pen", "white pen"),
            description = "A simple christmas card that you can present your family members",
            howLong = 20.toDuration(DurationUnit.MINUTES),
            difficultyLevel = 4,
            username = "isabelsousa"
        )
    }
}