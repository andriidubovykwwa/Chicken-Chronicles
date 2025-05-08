package com.emirhanok20.chickenchronicles.utility

import com.emirhanok20.chickenchronicles.data.MathExpression
import kotlin.random.Random

object MathExpressionGenerator {
    private val operators = listOf("+", "-", "*")
    private const val MIN_ANSWER = 1
    private const val MAX_ANSWER = 99


    fun generateExpression(): MathExpression {
        var num1: Int
        var num2: Int
        var num3: Int
        var op1: String
        var op2: String
        var answer: Int
        do {
            num1 = Random.nextInt(1, 10)
            num2 = Random.nextInt(1, 10)
            num3 = Random.nextInt(1, 10)
            op1 = operators.random()
            op2 = operators.random()
            answer = calculate(num1, op1, num2, op2, num3)
        } while (answer < MIN_ANSWER || answer > MAX_ANSWER)

        val expression = "$num1 $op1 $num2 $op2 $num3"
        return MathExpression(expression, answer)
    }

    private fun calculate(a: Int, op1: String, b: Int, op2: String, c: Int): Int {
        val first: Int
        val secondOp: String
        val secondNum: Int

        return if (op1 == "*") {
            first = a * b
            secondOp = op2
            secondNum = c
            applyOp(first, secondOp, secondNum)
        } else if (op2 == "*") {
            first = b * c
            secondOp = op1
            secondNum = a
            if (secondOp == "+") secondNum + first else secondNum - first
        } else {
            val temp = applyOp(a, op1, b)
            applyOp(temp, op2, c)
        }
    }


    private fun applyOp(x: Int, op: String, y: Int): Int {
        return when (op) {
            "+" -> x + y
            "-" -> x - y
            "*" -> x * y
            else -> x
        }
    }
}