package com.isabel.aimCrafter.rest

import com.isabel.aimCrafter.db.UserDao
import com.isabel.aimCrafter.rest.model.*
import com.isabel.aimCrafter.security.JWTTokens
import com.isabel.aimCrafter.security.PasswordHasher
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class UserController(
    val userDao: UserDao,
    val passwordHasher: PasswordHasher,
    val jwtTokens: JWTTokens
) {


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: UserSignUp): UserSignInResponse {
        val saltPass = passwordHasher.hashPassword(user.password)
        val userWithHashedPassword = user.copy(
            password = saltPass.password
        )
        val newUser = userDao.signUp(userWithHashedPassword, saltPass.salt)

        return UserSignInResponse(
            ResponseUser(
                firstName = newUser.firstName,
                lastName = newUser.lastName,
                username = newUser.username
            ),
            token = jwtTokens.generateToken(newUser.id)
        )
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody user: UserSignIn): UserSignInResponse {
//        user.password = passwordHasher.hashPassword(user.password)
        val fetchedUser = userDao.signIn(user) ?: throw UserNotFoundException()
        if (!passwordHasher.isValidPassword(user.password, fetchedUser.passwordDigest, fetchedUser.salt))
            throw UserNotFoundException()

        return UserSignInResponse(
            ResponseUser(
                firstName = fetchedUser.firstName,
                lastName = fetchedUser.lastName,
                username = fetchedUser.username
            ),
            token = jwtTokens.generateToken(fetchedUser.id)
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