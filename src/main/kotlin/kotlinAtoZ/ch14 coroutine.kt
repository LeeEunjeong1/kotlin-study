package kotlinAtoZ

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main1() {
    runBlocking { // 현재 사용하는 스레드 블로킹 처리
        println(
            "World! " + Thread.currentThread().name
        )
        println("Hello, " + Thread.currentThread().name) // 코루틴 내부에서 출력
    }

    Thread.sleep(1000) // 함수 일시 중단
    println("Main proccess") // 코루틴 처리 후 실행
}


fun main2() = runBlocking {
    var ix = 0
    val job = launch {
        repeat(1000) { _ ->
            println("job을 일시 중단 처리 : ${ix++} ...")
            delay(500L)
        }
    }
    delay(1300L)
    println("main  다른 코루틴 처리 ")
    job.cancel()
    job.join()
    println("main 함수 종료.")

}

fun CoroutineScope.log(msg: String) {
    val name = coroutineContext[CoroutineName]?.name
    println("[$name] $msg") // 코루틴컨텍스트 이름 출력
}

@OptIn(DelicateCoroutinesApi::class)
fun main3() {
    GlobalScope.launch(CoroutineName("전역스코프")) {
        log("launch 코루틴 빌더 시작")
        val job = launch {
            delay(500) // 0.5초 대기
            log("코루틴 처리 1")
        }
        job.join() // 전역 코루틴의 자식 코루틴 종료까지 대기
        log("launch 코루틴 빌더 정지") // 최종 종료
    }
    Thread.sleep(1000) // 메인스레드 잠시 대기
    println("메인 스레드 처리 2") // 메인 출력
}

//val syn = GlobalScope.async(CoroutineName("전역스코프")) {
//    log("현재 코루틴 1 : " + Thread.currentThread().name)
//    async(Dispatchers.Default) { // 비동기 코루틴 빌더
//        delay(100) // 일시정지
//        log("현재 코루틴 2 : " + Thread.currentThread().name)
//    }
//    delay(100) // 일시정지
//    log("코루틴 종료 " + Thread.currentThread().name) // 부모를 종료처리
//}

fun main4() = runBlocking { // 현재 스레드 코루틴 처리
    withContext(Dispatchers.Default) { // 컨텍스트 변경
        println("위드컨텍스트 처리 : " + Thread.currentThread().name)
        delay(100)
    }
    launch { // 기존 컨텍스트 사용
        println("런치 처리 : " + Thread.currentThread().name)
        delay(100)
    }
    delay(2000)
}

fun main5() {
    runBlocking {
        launch {
            println("launch : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("launch(Dispatchers.Unconfined) : ${Thread.currentThread().name}")
        }
//        launch(Dispatchers.Main){ // 안드로이드용
//            println("launch(Dispatchers.Main) :${Thread.currentThread().name}")
//        }
        launch(Dispatchers.IO) {
            println("launch(Dispatchers.IO) :${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("launch(Dispatchers.Default) :${Thread.currentThread().name}")
        }
    }
}

suspend fun doSomething(): Int { // 일시중단 함수
    delay(100L) // 일시 정지
    println("일시중단 함수 실행 1")
    return 13
}

fun main6() {
    GlobalScope.launch(Dispatchers.Default) { // 전역 스코프에 코루틴 빌더
        val time = measureTimeMillis { // 처리 시간 계산
            val something = doSomething() // 함수 호출
            println("실행결과 : $something")
        }
        delay(100) // 임시 지연
        println("총 실행시간 : $time ms.") // 함수 실행 결과 출력
    }
    println("~~ 작업 종료 ~~")
}

fun main7() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("코루틴 job 실행 : $i ...")
                delay(500L)
            }
        } catch (e: Exception) {
            println("중단에 따른 예외 : " + e.message)
        }
    }
    delay(1300L)
    println("메인 처리후 자식 종료")
    job.cancel()
    job.join()
    println("메인 종료 .")
}

fun main8() {
    val handler = CoroutineExceptionHandler { // 예외처리 핸들러 작성
            _, exception ->
        println("예외처리 : $exception")
    }
    GlobalScope.launch(handler) {
        launch {
            println("코루틴 실행 1 ")
            delay(500L)
            throw Exception("첫번째 코루틴 내에서 예외 발생")
        }
        launch {
            println("코루틴 실행 2 ")
            delay(500L)
        }
    }
    Thread.sleep(1300L)
    println("메인 처리 후 자식 종료")
    println("메인 종료 .")
}

fun main9() = runBlocking {
    try {
        supervisorScope { // 슈퍼바이저 스코프로 에외처리
            val job = async { // 어싱크에서 예외가 발생하면 부모에게 전달
                println("코루틴 실행")
                delay(500L)
                throw Exception("첫번째 코루틴 내에서 예외 발생")
            }
            println("메인 처리 후 자식 종료")
            try {
                job.await()
            } catch (e: Exception) {
                println("예외를 다시 전달")
                println(e.message)
            }
            println("메인 종료 .")
        }
    } catch (e: Exception) {
        println("부모영역까지 예외전달 ")
        println(e.message)
    }
}

fun foo(): Flow<Int> = flow {
    println("플로우 시작")
    for (i in 1..3) {
        delay(100)
        emit(i) // 데이터 송신
    }
}

fun main10() = runBlocking<Unit> {
    println("플로우 스코프 만듬")
    val flow = foo() // 플로우 함수 실행
    println(flow.javaClass) // 플로우 객체로 반환

    flow.collect() { value -> println(value) } // 플로우 처리
    println("집합 반환 : " + flow.toSet()) // 집합으로 변환

    launch {
        println("코루틴 내부에서 호출")
        flow.collect { value -> println(value) } // 플로우 중단함수
    }
}

fun foo2(): Flow<Int> = flow { // 플로우 빌더 처리
    for (i in 1..3) {
        delay(100) // 일시 중단
        println("Emitting $i")
        emit(i) // 플로우 값 처리
    }
}

fun main11() = runBlocking<Unit> {
    withTimeoutOrNull(250) {// 250ms 후에 시간 초과
        foo2().collect() { value -> println(value) } // 플로우 중단함수
    }
    println("Done")
}

fun main12() {
    val mapF = mutableMapOf<Int, String>()
    runBlocking<Unit> {
        val nums = (1..3).asFlow()
        val l = listOf("one", "two", "three")
        val strs = flowOf(*l.toTypedArray())
        println("리스트로 변환 : " + strs.toList())

        nums.zip(strs) { a, b -> a to b } // 집을 통해 튜플로 변환
            .collect { mapF.put(it.first, it.second) }
        println("맵으로 변환 : $mapF")
    }
}

@OptIn(ObsoleteCoroutinesApi::class)
fun main13() = runBlocking { // 런블로킹 스코프 생성
    val actor1 = actor<String>(capacity = 10) { // 액터 빌더
        for (data in channel) { // 액터 내부의 수신된 데이터 출력
            println(data + "Thread : " + Thread.currentThread().name)
        }
    }
    (1..5).forEach {
        actor1.send(it.toString()) // 액터에 데이터 전송
    }
    actor1.close() // 액터 종료
    delay(500L) // 전체 지연
    println(" closed")
}

fun main() {
//    main1()
//    main2()
//    main3()
//    syn
//    Thread.sleep(2000)
//    main4()
//    main5()
//    main6()
//    Thread.sleep(2000)
//    main7()
//    main8()
//    main9()
//    main10()
//    main11()
//    main12()
    main13()
}