package com.sasfmlzr.mathoptimization.matchParser

import java.util.*
import kotlin.math.*

class MatchParser {
    private val variables: HashMap<String, Double?> = HashMap()
    fun setVariable(variableName: String, variableValue: Double?) {
        variables[variableName] = variableValue
    }

    private fun getVariable(variableName: String): Double? {
        if (!variables.containsKey(variableName)) {
            System.err.println("Error: Try get unexists variable '$variableName'")
            return 0.0
        }
        return variables[variableName]
    }

    fun parse(s: String): Double {
        val result = plusMinus(s)
        if (result.rest.isNotEmpty()) {
            System.err.println("Error: can't full parse")
            System.err.println("rest: " + result.rest)
        }
        return result.acc
    }

    private fun plusMinus(s: String): Result {
        var current = mulDiv(s)
        var acc = current.acc
        while (current.rest.isNotEmpty()) {
            if (!(current.rest[0] == '+' || current.rest[0] == '-')) break
            val sign = current.rest[0]
            val next = current.rest.substring(1)
            current = mulDiv(next)
            if (sign == '+') {
                acc += current.acc
            } else {
                acc -= current.acc
            }
        }
        return Result(acc, current.rest)
    }

    private fun bracket(s: String): Result {
        val zeroChar = s[0]
        if (zeroChar == '(') {
            val r = plusMinus(s.substring(1))
            if (r.rest.isNotEmpty() && r.rest[0] == ')') {
                r.rest = r.rest.substring(1)
            } else {
                System.err.println("Error: not close bracket")
            }
            return r
        }
        return functionVariable(s)
    }

    private fun functionVariable(s: String): Result {
        var f = ""
        var i = 0
        // ищем название функции или переменной
        // имя обязательно должна начинаться с буквы

        var funcStr = s

        var negative = false
        // число также может начинаться с минуса
        if (funcStr[0] == '-') {
            negative = true
            funcStr = funcStr.substring(1)
        }

        while (i < funcStr.length && (Character.isLetter(funcStr[i]) || Character.isDigit(
                funcStr[i]
            ) && i > 0)
        ) {
            f += funcStr[i]
            i++
        }
        return if (f.isNotEmpty()) { // если что-нибудь нашли
            if (funcStr.length > i && funcStr[i] == '(') { // и следующий символ скобка значит - это функция
                val r =
                    bracket(funcStr.substring(f.length))
                processFunction(f, r)
            } else { // иначе - это переменная
                if (negative) {
                    Result(
                        -getVariable(f)!!,
                        funcStr.substring(f.length)
                    )
                } else {
                    Result(
                        getVariable(f)!!,
                        funcStr.substring(f.length)
                    )
                }

            }
        } else num(s)
    }

    private fun mulDiv(s: String): Result {
        var current = bracket(s)
        var acc = current.acc
        while (true) {
            if (current.rest.isEmpty()) {
                return current
            }
            val sign = current.rest[0]
            if (sign != '*' && sign != '/' && sign != '^') return current
            val next = current.rest.substring(1)
            val right = bracket(next)
            when (sign) {
                '^' -> acc = acc.pow(right.acc)
                '*' -> acc *= right.acc
                '/' -> acc /= right.acc
            }
            current = Result(acc, right.rest)
        }
    }

    @Throws(Exception::class)
    private fun num(s: String): Result {
        var numStr = s
        var i = 0
        var dotCnt = 0
        var negative = false
        // число также может начинаться с минуса
        if (numStr[0] == '-') {
            negative = true
            numStr = numStr.substring(1)
        }
        // разрешаем только цифры и точку
        while (i < numStr.length && (Character.isDigit(numStr[i]) || numStr[i] == '.')) { // но также проверям, что в числе может быть только одна точка!
            if (numStr[i] == '.' && ++dotCnt > 1) {
                throw Exception("not valid number '" + s.substring(0, i + 1) + "'")
            }
            i++
        }
        if (i == 0) { // что-либо похожее на число мы не нашли
            throw Exception("can't get valid number in '$s'")
        }
        var dPart = numStr.substring(0, i).toDouble()
        if (negative) dPart = -dPart
        val restPart = numStr.substring(i)
        return Result(dPart, restPart)
    }

    // Тут определяем все нашие функции, которыми мы можем пользоватся в формулах
    private fun processFunction(
        func: String,
        r: Result
    ): Result {
        when (func) {
            "sin" -> {
                return Result(
                    sin(
                        Math.toRadians(
                            r.acc
                        )
                    ), r.rest
                )
            }
            "cos" -> {
                return Result(
                    cos(
                        Math.toRadians(
                            r.acc
                        )
                    ), r.rest
                )
            }
            "tan" -> {
                return Result(
                    tan(
                        Math.toRadians(
                            r.acc
                        )
                    ), r.rest
                )
            }
            "exp" -> {
                return Result(
                    exp(r.acc),
                    r.rest
                )
            }
            "log" -> {
                return Result(
                    log10(r.acc),
                    r.rest
                )
            }
            "ln" -> {
                return Result(
                    ln(r.acc),
                    r.rest
                )
            }
            else -> {
                System.err.println("function '$func' is not defined")
            }
        }
        return r
    }

}