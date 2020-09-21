package com.github.tomplum.activity.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sleep")
class SleepController {

    @GetMapping("/initialise")
    fun initialise() {
        println("HIT INITIALISE")
    }
}