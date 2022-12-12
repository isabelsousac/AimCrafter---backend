package com.isabel.aimCrafter.rest.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User does not exist")
class UserNotFoundException : RuntimeException()

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Craft does not exist")
class CraftNotFoundException : RuntimeException()

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User is not logged in")
class NotLoggedInException : RuntimeException()