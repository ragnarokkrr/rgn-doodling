package ragna.debezium.rgncdc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RgnDebeziumCdcApplication

fun main(args: Array<String>) {
	runApplication<RgnDebeziumCdcApplication>(*args)
}
