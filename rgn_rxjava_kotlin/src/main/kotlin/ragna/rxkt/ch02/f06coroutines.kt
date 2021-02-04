package ragna.rxkt.ch02

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Duration
import kotlin.system.measureTimeMillis


suspend fun longRunningTask(): Long {
    val time = measureTimeMillis {
        println("Please wait")

        delay(Duration.ofSeconds(2).toMillis())
        println("Delay over")
    }
    return time
}

fun main(args: Array<String>) {
    runBlocking {
        val exeTime = longRunningTask()
        println("Exceution Time is $exeTime")
    }
}