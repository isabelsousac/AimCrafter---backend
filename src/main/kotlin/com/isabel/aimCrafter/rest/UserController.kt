package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.UserDao
import com.isabel.aimCrafter.rest.model.*
import com.isabel.aimCrafter.security.PasswordHasher
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class UserController(
    val userDao: UserDao,
    val passwordHasher: PasswordHasher
) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: UserSignUp): UserSignInResponse {
        user.password = passwordHasher.hashPassword(user.password)
        val newUser = userDao.signUp(user)

        return UserSignInResponse(
            ResponseUser(
                firstName = newUser.firstName,
                lastName = newUser.lastName,
                username = newUser.username
            ),
            token = "5555"
        )
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody user: UserSignIn): UserSignInResponse {
        user.password = passwordHasher.hashPassword(user.password)
        val newUser = userDao.signIn(user)

        return UserSignInResponse(
            ResponseUser(
                firstName = "Isabel",
                lastName = "Sousa",
                username = "isabelsousa"
            ),
            token = "5555"
        )
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun showUser(@PathVariable id: Int): User {
        // create query
        return User(
            username = "isabelsousa",
            crafts = listOf(
                SimplifiedCraftResponse(
                    title = "Christmas card",
                    image = "https://media.istockphoto.com/id/508030340/photo/sunny-cat.jpg?s=612x612&w=0&k=20&c=qkz-Mf32sbJnefRxpB7Fwpcxbp1fozYtJxbQoKvSeGM=",
                    createdAt = LocalDate.now()
                )
            )
        )
    }
}