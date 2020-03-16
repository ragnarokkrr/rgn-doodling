package ragna.debezium.rgncdc.utils

enum class Operation(val code: String) {
    READ("r"),
    CREATE("c"),
    UPDATE("u"),
    DELETE("d"),
    ;

    companion object {
        fun forCode(code: String): Operation {
            return values().filter { operation -> operation.code.equals(code, ignoreCase = true) }
                    .first()
        }
    }
}