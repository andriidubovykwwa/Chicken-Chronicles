package com.emirhanok20.chickenchronicles.utility.ui.math_quiz

import com.emirhanok20.chickenchronicles.data.MathExpression

data class MathQuizState(
    val secondsLeft: Int = DEFAULT_SECONDS,
    val expressionsLeft: Int = DEFAULT_EXPRESSION_AMOUNT,
    val currentExpression: MathExpression? = null,
    val userAnswer: String = "",
    val isQuizStarted: Boolean = false,
    val winStatus: Boolean? = null, // true - win, false - lose
) {
    companion object {
        private const val DEFAULT_SECONDS = 40
        const val DEFAULT_REWARD = 100
        private const val DEFAULT_EXPRESSION_AMOUNT = 5
    }
}
