package com.anvuong.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        mySuspendingFunc()
    }
    println("Hello, thread ${java.lang.Thread.currentThread().name}") // main coroutine continues while a previous one is delayed
}

suspend fun mySuspendingFunc(): Unit {
    println("Before, thread ${java.lang.Thread.currentThread().name}")
    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    println("World!, thread ${java.lang.Thread.currentThread().name}") // print after delay
}
