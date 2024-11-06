package tech.clovy.clovyapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClovyApplication

fun main(args: Array<String>) {
    runApplication<ClovyApplication>(*args)
}
