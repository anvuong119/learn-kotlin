package com.anvuong.coroutines

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking<Unit>(CoroutineName("My Coroutine")) {
    val time = measureTimeMillis {
        val three = async(Dispatchers.Unconfined) { doSomethingUsefulTwo(2) }
        println("After starting three coroutines using async, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
        val one = async(Dispatchers.IO) { doSomethingUsefulOne(1) }
        val two = async(Dispatchers.Default) { doSomethingUsefulTwo(1) }
        println("After launching 3 coroutines, thread ${Thread.currentThread().name}")
        println("The answer is ${one.await() + two.await() + three.await()}, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
    }
    println("Completed in $time ms, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
    println("-----------------------------------------------------------------------------")
    val time2 = measureTimeMillis {
        launch(Dispatchers.Unconfined) { doSomethingUsefulTwo(3) }
        println("After launch(Dispatchers.Unconfined) { doSomethingUsefulTwo(3) }, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
        launch(Dispatchers.IO) { doSomethingUsefulTwo(4) }
        launch(Dispatchers.Default) { doSomethingUsefulOne(2) }
        println("After launching 3 coroutines, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
    }
    println("Completed in $time2 ms, in coroutine ${coroutineContext[CoroutineName.Key]}, thread ${Thread.currentThread().name}")
}

suspend fun doSomethingUsefulOne(id: Int): Int {
    println("Inside doSomethingUsefulOne($id) - start, thread ${Thread.currentThread().name}")
    delay(1000L)
    println("Inside doSomethingUsefulOne($id) - end, thread ${Thread.currentThread().name}")
    return 1
}

suspend fun doSomethingUsefulTwo(id: Int): Int {
    println("Inside doSomethingUsefulTwo($id) - start, thread ${Thread.currentThread().name}")
    delay(2000L)
    println("Inside doSomethingUsefulTwo($id) - end, thread ${Thread.currentThread().name}")
    return 2
}
