package br.com.lteixeira

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocOpensearchApplication

fun main(args: Array<String>) {
	runApplication<PocOpensearchApplication>(*args)
}
