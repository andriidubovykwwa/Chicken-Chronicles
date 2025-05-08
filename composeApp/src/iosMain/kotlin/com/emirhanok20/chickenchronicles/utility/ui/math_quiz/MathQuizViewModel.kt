package com.emirhanok20.chickenchronicles.utility.ui.math_quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhanok20.chickenchronicles.utility.MathExpressionGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MathQuizViewModel : ViewModel() {
    private val _state = MutableStateFlow(MathQuizState())
    val state = _state.asStateFlow()

    fun onEvent(event: MathQuizEvent) {
        when (event) {
            is MathQuizEvent.StartQuiz -> processStartQuiz()
            is MathQuizEvent.UpdateUserAnswer -> processUpdateUserAnswer(event.answer)
            is MathQuizEvent.SubmitAnswer -> processSubmitAnswer()
            is MathQuizEvent.SecondTick -> processSecondTick()
            is MathQuizEvent.Reset -> processReset()
        }
    }

    private fun processReset() = viewModelScope.launch {
        _state.update { MathQuizState() }
    }

    private fun processSecondTick() = viewModelScope.launch {
        if (state.value.winStatus != null) return@launch
        val newSeconds = (state.value.secondsLeft - 1).coerceAtLeast(0)
        _state.update {
            it.copy(
                secondsLeft = newSeconds,
                winStatus = if (newSeconds == 0) false else null
            )
        }
    }

    private fun processSubmitAnswer() = viewModelScope.launch {
        if (state.value.userAnswer == "") return@launch
        val answer = state.value.userAnswer.toIntOrNull() ?: 0
        val isRightAnswer = (state.value.currentExpression?.answer ?: 0) == answer
        if (isRightAnswer) {
            if (state.value.expressionsLeft <= 0) {
                _state.update {
                    it.copy(winStatus = true)
                }
            } else {
                _state.update {
                    it.copy(
                        currentExpression = MathExpressionGenerator.generateExpression(),
                        expressionsLeft = it.expressionsLeft - 1,
                        userAnswer = "",
                    )
                }
            }
        } else {
            _state.update {
                it.copy(winStatus = false)
            }
        }

    }

    private fun processStartQuiz() = viewModelScope.launch {
        _state.update {
            it.copy(
                isQuizStarted = true,
                currentExpression = MathExpressionGenerator.generateExpression(),
            )
        }
    }

    private fun processUpdateUserAnswer(answer: String) = viewModelScope.launch {
        if (state.value.winStatus != null) return@launch
        _state.update { it.copy(userAnswer = answer) }
    }
}