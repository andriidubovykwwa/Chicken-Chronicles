package com.devname.chickenchronicles.data

data class MathExpression(
    val expression: String,
    val answer: Int
) {
    val displayString: String
        get() = "$expression = ?"
}
