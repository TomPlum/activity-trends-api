package com.github.tomplum.activity

import com.github.tomplum.activity.config.MongoConfig
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.lang.annotation.Inherited

@Inherited
@SpringBootTest
@MustBeDocumented
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootConfiguration
@Target(AnnotationTarget.CLASS)
@Import(MongoConfig::class)
annotation class IntegrationTest