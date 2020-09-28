package com.github.tomplum.activity

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@Inherited
@SpringBootTest
@MustBeDocumented
@ActiveProfiles("dev")
@Target(AnnotationTarget.CLASS)
annotation class IntegrationTest