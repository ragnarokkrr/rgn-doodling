package ragna.rxkt.ch02

fun main() {

    val fibonacciSeries = sequence {
        var a = 0
        var b = 1
        yield(a)
        yield(b)

        while(true) {
            val c = a + b
            yield(c)
            a = b
            b = c
        }
    }

    println(fibonacciSeries.take(10).toList())

}

private fun oldSchoolFibo() {
    var a = 0
    var b = 1
    print("$a,")
    print("$b,")

    for (i in 2..9) {
        val c = a + b
        print("$c,")
        a = b
        b = c
    }
}