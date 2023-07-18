package com.example.apptekatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ApptekaTestApplication

fun main(args: Array<String>) {
	runApplication<ApptekaTestApplication>(*args)
}
