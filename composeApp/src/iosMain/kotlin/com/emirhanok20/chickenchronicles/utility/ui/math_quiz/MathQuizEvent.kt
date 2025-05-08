package com.emirhanok20.chickenchronicles.utility.ui.math_quiz

sealed interface MathQuizEvent {
    data class UpdateUserAnswer(val answer: String) : MathQuizEvent
    data object SubmitAnswer : MathQuizEvent
    data object StartQuiz : MathQuizEvent
    data object SecondTick : MathQuizEvent
    data object Reset : MathQuizEvent
}