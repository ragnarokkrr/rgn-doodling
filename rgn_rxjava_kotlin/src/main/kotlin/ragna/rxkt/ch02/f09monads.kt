package ragna.rxkt.ch02

import io.reactivex.Maybe

fun main(args: Array<String>) {
    val maybeValue: Maybe<Int> = Maybe.just(14)
    maybeValue.subscribe(
        /*onSuccess = */{ println("Completed with value $it") },
        /*onError = */{ println("Error $it") },
        /*onComplete = */{ println("Completed empty") }
    )

    val maybeEmpty:Maybe<Int> = Maybe.empty();
    maybeEmpty.subscribe(
        /*onSuccess = */{ println("Completed with value $it") },
        /*onError = */{ println("Error $it") },
        /*onComplete = */{ println("Completed empty") }
    )
}