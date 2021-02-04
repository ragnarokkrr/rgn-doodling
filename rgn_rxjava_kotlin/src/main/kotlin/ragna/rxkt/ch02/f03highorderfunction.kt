package ragna.rxkt.ch02

inline fun highOrderFunc(a: Int, validityCheckFunc: (a: Int) -> Boolean) {
    if (validityCheckFunc(a)) {
        println("a $a is Valid")
    } else {
        println("a $a is Invalid")
    }
}

fun main() {
    highOrderFunc(12) { a: Int -> a.isEven() } // prefered
    //highOrderFunc(12, {a: Int -> a.isEven()})
    highOrderFunc(19) { a: Int -> a.isEven() }
}

fun Int.isEven(): Boolean { //extension function
    return this % 2 == 0
}

fun Int.isOdd(): Boolean {// extension function
    //return !isEven()
    return this % 2 == 1
}

