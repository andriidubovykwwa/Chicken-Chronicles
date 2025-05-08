package com.emirhanok20.chickenchronicles.data

data class MathExpression(
    val expression: String,
    val answer: Int
) {
    val displayString: String
        get() = "$expression = ?"
}
