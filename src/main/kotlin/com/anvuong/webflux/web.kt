package com.anvuong.webflux

import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.awaitFirstOrNull

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        mySuspendingFunc()
    }
    println("Done")
}

suspend fun mySuspendingFunc(): Unit {
    val text = MyWebClient().getWebContent("https://www.gooddata.com").awaitFirstOrNull()
    println(text)
}
