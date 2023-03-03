package com.example.springsecuritykotlin

class new {
}

fun f(n: Int): Int {
    return n+1
}

fun main(s: Array<String>) {
    val n: Int? = null
    val v: Int? = 3

    println(n?.let(::f))
    println(v?.let(::f))
}
