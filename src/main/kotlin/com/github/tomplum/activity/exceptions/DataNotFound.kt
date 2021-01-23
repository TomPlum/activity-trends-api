package com.github.tomplum.activity.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class DataNotFound(message: String) : RuntimeException(message)