package ragna.rxkt.ch02

import kotlin.random.Random

fun main(args: Array<String>) {
    val sum = { x: Int, y: Int -> x + y }
    println("Sum ${sum(12, 14)}")

    val anonymousMult = { x: Int -> (Random.nextInt(15) + 1) * x }
    println("random output ${anonymousMult(2)}")
}