package cz.sedy.router

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CountryRouterApplication

fun main(args: Array<String>) {
	runApplication<CountryRouterApplication>(*args)
}
