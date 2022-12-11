package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.rest.model.SimplifiedCraftResponse
import com.isabel.aimCrafter.rest.model.Tool
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class ToolController {
    // I don't know if I need a Dao interface for this rest controller
    @GetMapping("/pickedCrafts")
    @ResponseStatus(HttpStatus.OK)
    fun craftsByTools(@RequestBody tools: List<Tool>): List<SimplifiedCraftResponse> {
        // create query
        return listOf(
            SimplifiedCraftResponse(
                title = "abc",
                image = "https://cdn.pixabay.com/photo/2016/11/23/00/37/art-1851483__340.jpg",
                createdAt = LocalDate.now()
            ),
            SimplifiedCraftResponse(
                title = "abc",
                image = "https://cdn.pixabay.com/photo/2016/11/20/08/25/embroidery-1842177__340.jpg",
                createdAt = LocalDate.now()
            )
        )
    }
}