package com.sasfmlzr.mathoptimization.architecture

interface Logger {
    fun d(tag: String, message: String)
    fun d(message: String)
    fun e(tag: String, message: String)
    fun e(message: String)
}
