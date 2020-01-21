package ragna.rxkt.ch02

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main(args: Array<String>) {
    println("Initial Output with a = 15, b = 10")
    val calculator = ReactiveCalculator(15, 10)
    println("Enter a = <number> or b = <number> in separate lines\nexit to exit")

    var line: String?
    do {
        line = readLine()
        calculator.handleInput(line)
    } while (line != null && !line.toLowerCase().contains("exit"))
}

class ReactiveCalculator(a: Int, b: Int) {
    internal val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()
    internal var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectCalc.subscribe {
            with(it) {
                calculateAddition()
                calculateSubtraction()
                calculateMultiplication()
                calculateDivision()
            }
        }
        subjectCalc.onNext(this)
    }

    private inline fun calculateAddition(): Int {
        val result = nums.first + nums.second
        println("Add = $result")
        return result
    }

    private inline fun calculateSubtraction(): Int {
        val result = nums.first - nums.second
        println("Subtract = $result")
        return result
    }

    private inline fun calculateMultiplication(): Int {
        val result = nums.first * nums.second
        println("Multiply = $result")
        return result
    }

    private inline fun calculateDivision(): Double {
        val result = (nums.first * 1.0) / (nums.second * 1.0)
        println("Division = $result")
        return result
    }

    fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine: String?) {
        if (!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b]) (?:\\s)?=(?:\\s)(\\d*)")
            val matcher: Matcher = pattern.matcher(inputLine)

            var a: Int? = null
            var b: Int? = null

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(1).toLowerCase().equals("a")) {
                    a = matcher.group(2).toInt()
                } else if (matcher.group(1).toLowerCase().equals("b")) {
                    b = matcher.group(2).toInt()
                }
            }
            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid input")
            }
        }

    }

}
