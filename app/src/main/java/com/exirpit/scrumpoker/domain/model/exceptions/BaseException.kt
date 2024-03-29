package com.exirpit.scrumpoker.domain.model.exceptions

open class BaseException : Exception {
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(cause: Throwable): super(cause)
}