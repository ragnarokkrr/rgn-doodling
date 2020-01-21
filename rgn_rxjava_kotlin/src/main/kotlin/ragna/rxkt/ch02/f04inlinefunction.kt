package ragna.rxkt.ch02

// inline functions are an enhancement feature to improve the performance and memory optimization of a program.
fun doSomeStuff(a: Int = 0) = a + (a * a)

fun main(args: Array<String>) {
    for (i in 1..10) {
        println("$i Output ${doSomeStuff(i)}")
    }
    println("'' Output ${doSomeStuff()}")

}