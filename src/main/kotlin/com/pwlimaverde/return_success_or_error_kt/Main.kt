package com.pwlimaverde.return_success_or_error_kt

import com.pwlimaverde.return_success_or_error_kt.core.SuccessReturn

fun main() {
    testeCalc()
}

fun testeCalc(): Int {

    var t = 4
    var t2 = 4
    println("teste ${t+t2}")
    return t+t2
}