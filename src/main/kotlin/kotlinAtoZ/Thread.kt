package kotlinAtoZ

import java.util.concurrent.Executors

fun exec(tr: Thread) { // 스레드 내부에서 실행할 함수 정의
    println("$tr : 보조 스레드 작동중 ")
}

class MyThread : Thread() { // 스레드 클래스를 상속
    override fun run() {
        val tr = Thread.currentThread()  // 함수 실행 스레드 확인
        exec(tr)
        println("$tr : 보조 스레드 종료 ")
    }
}

fun main() {
    val mtr = Thread.currentThread() // 현재 스레드 확인
    println("$mtr : 메인 스레드 작동중")

    val myThread = MyThread() // 스레드 객체 생성
    myThread.start() // 스레드 실행

    exec(myThread)

    println("$mtr : 대기중") // 스레드가 다 처리하면 출력
    myThread.join() // 보조 스레드 종료 대기


    /* 스레드 풀 */
    val executor = Executors.newFixedThreadPool(2) // 특정 스레드 개수만큼만 사용한느 풀을 만듬
    var count = 0
    repeat(3) {
        executor.execute {
            Thread.sleep(10) // 스레드 임시 지연처리
            println(Thread.currentThread().name)
            count += 1
            println(count)
        }
    }
    println(executor.isTerminated) // 스레드 풀 미종료
    executor.shutdown() // 스레드 풀 종료
    println(executor.isShutdown) // 스레드 풀 종료 확인
}