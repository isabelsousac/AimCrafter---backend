package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.UserDao
import com.isabel.aimCrafter.rest.model.*
import com.isabel.aimCrafter.security.PasswordHasher
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userDao: UserDao,
    val passwordHasher: PasswordHasher
) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: UserSignUp): UserSignInResponse {
        val userWithHashedPassword = user.copy(
            password = passwordHasher.hashPassword(user.password)
        )
        val newUser = userDao.signUp(userWithHashedPassword)

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
        val fetchedUser = userDao.signIn(user) ?: throw UserNotFoundException()
        return UserSignInResponse(
            ResponseUser(
                firstName = fetchedUser.firstName,
                lastName = fetchedUser.lastName,
                username = fetchedUser.username
            ),
            token = "5555"
        )
    }

    @GetMapping("user/{username}")
    @ResponseStatus(HttpStatus.OK)
    fun showUser(@PathVariable username: String): User {
        val fetchedUser = userDao.showUser(username) ?: throw UserNotFoundException()
        return User(
            username = fetchedUser.username,
            crafts = fetchedUser.crafts
        )
    }
}