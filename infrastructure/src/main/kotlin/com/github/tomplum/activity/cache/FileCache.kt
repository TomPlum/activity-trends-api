package com.github.tomplum.activity.cache

import org.springframework.stereotype.Component

@Component
open class FileCache<T> {
    private var file: T? = null

    fun store(value: T)  {
        file = value
    }

    fun query(): T? = file
}