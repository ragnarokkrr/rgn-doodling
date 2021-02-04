package ragna.rxkt.ch01

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable


fun main(args: Array<String>) {

    var list: List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f)
    var observable: Observable<Any> = list.toObservable()

    observable.subscribeBy(
        onNext = { println(it) },
        onError = { it.printStackTrace() },
        onComplete = { println("Done") }
    )
    // pull()
}

private fun pull() {
    var list: List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f)
    var iterator = list.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}