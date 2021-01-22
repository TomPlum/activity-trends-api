package com.github.tomplum.activity

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@ExtendWith(MockKExtension::class)
@Retention(AnnotationRetention.RUNTIME)
annotation class EnableMocks()
