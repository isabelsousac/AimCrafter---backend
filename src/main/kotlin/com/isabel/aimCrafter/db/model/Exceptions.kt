package com.isabel.aimCrafter.db.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username is already taken.")
class DuplicatedUsernameException : RuntimeException()