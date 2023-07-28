package kotlinAtoZ

fun main(){
    equality()
    conditionalStatements()
    exception()
    range()
    forCycle()
    iterator()
}

fun equality(){
    // 동등성 연산 처리
    val a = 100
    val b = 100
    val c = null
    println(a == b) // true
    println(a.equals(b)) // true

    // 널타입 처리는 널도 체크
    println(a?.equals(b) ?: (b === null)) // true
    // 객체 참조 비교
    println(a === b) // true

    // 널값끼리 비교
    println(c == null) // true
    // 널 객체 참조 비교
    println(c === null) // true
    // 정상적인 값과 널값 비교
    println(a?.equals(c) ?: (c === null)) // false

    /* 실수 처리 */
    val d = 0.0
    val e = -0.0

    println(d == e) // true
    println(d === e) // true
}

fun conditionalStatements(){
    // 단순 조건 처리 : 단일표현식
    val a = if(10>20) " 성공 " else " 실패 "
    println(a) // 실패

    // 단순 조건 처리 : 코드 블록
    val b = if(10>20){
        true
    }else {
        false
    }
    println(" 변수 = $b") // 변수 = false

    // when 조건

    val cores = Runtime.getRuntime().availableProcessors()

    when(cores){
        1-> println(" 1 core")
        in 2..16 -> println(" $cores Cores")
        else -> println("I want your machine")
    }

    println(systemInfoR()) // 8 cores

    val number = 10
    if(number < 0){
        println("음수")
    }else if(number == 0){
        println("영")
    }else if(number % 2 == 0){
        println("짝수")
    }else{
        println("홀수")
    }

    when{
        number < 0 -> println("음수")
        number == 0 -> println("영")
        number % 2 == 0-> println("짝수")
        else -> println("홀수")
    }

    // --> 짝수

}

fun systemInfoR(): String=
    when(val cores = Runtime.getRuntime().availableProcessors()){
        1-> " 1 core"
        in 2..16 -> " $cores Cores"
        else -> "I want your machine"
    }

fun exception(){
    // 예외 처리
    try{
        add()
    }catch(e: Exception){
        println(e)
    }finally{
        println("정상적으로 처리")
    }
    // 결과 --> 정상적으로 처리

    // 예외 던지기
    try{
        throw Exception("예외 발생")
    }catch(e:Exception){
        println(e)
    }finally{
        println("정상적으로 처리")
    }
    // 결과 -->
    // java.lang.Exception: 예외 발생
    // 정상적으로 처리

    // Nothing
    try{
        except()
    }catch (e:Exception){
        println(e)
    }
}

fun add() = 100

fun except() : Nothing{
    return throw Exception(" 예외 발생")
}

fun range(){
    val range1 = 1..10 // 순방향
    val range2 = 1.rangeTo(10) // 순방향
    val range3 = 1.until(10) // 순방향, 마지막 미포함
    val range4 = 10.downTo(1) // 역방향

    println("range1 first = ${range1.first} / last = ${range1.last}") // range1 first = 1 / last = 10
    println("range2 first = ${range2.first} / last = ${range2.last}") // range2 first = 1 / last = 10
    println("range3 first = ${range3.first} / last = ${range3.last}") // range3 first = 1 / last = 9
    println("range4 first = ${range4.first} / last = ${range4.last}") // range4 first = 10 / last = 1

    val range5 = 1.rangeTo(10).step(2) // 범위를 만든 후에 step 메서드로 진행 객체로 변환

    println("range5 first = ${range5.first} / last = ${range5.last} / step = ${range5.step}") //range5 first = 1 / last = 9 / step = 2

}

fun forCycle(){
    for(i in 1..5){
        print("$i, ") // 1, 2, 3, 4, 5
    }
    println()
    for(i in 1.rangeTo(5)){
        print("$i, ") // 1, 2, 3, 4, 5
    }
    println()
    for(i in 1.until(5)){
        print("$i, ") // 1, 2, 3, 4,
    }
    println()

    for(i in 1..10){
        if(i % 2 == 0){
            println("continue $i")
            continue // 가장 가까운 순환으로 이동
        }

        if(i == 7){
            println("break $i ")
            break // 가장 가까운 순환을 종료
        }
    }

    // --> 결과
    //continue 2
    //continue 4
    //continue 6
    //break

    loop@ for(i in 1..3){
        for(j in 1..5){
            if(j==3){
                println(" 내포 순환 ")
                break@loop
            }
            println(" for 순환 $j")
        }
    }

    // --> 결과
    // for 순환 1
    // for 순환 2
    // 내포 순환

    // while
    var n = 0
    while(n < 3){
        println(n)
        n++
    }
    // --> 결과
    //0
    //1
    //2

    // do while
    var m = 0
    do{
        println(m)
        m++
    }while (m<3)

    // --> 결과
    //0
    //1
    //2
}

fun iterator(){
    val i = 1..10
    val c = 'a'..'z'

    val ilter = i.iterator() // 정수 범위를 반복자로 처리
    val clter = c.iterator() // 문자 범위를 반복자로 처리

    ilter.forEach{print("$it,")} // 내부 순환
    println()
    for(i in clter)print("$i, ") // 외부 순환
    println()

    // --> 결과
    //1,2,3,4,5,6,7,8,9,10,
    //a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,

    val r = ('a'..'c').iterator() // 반복자 처리
    while(r.hasNext()){ // 외부 순환
        println(r.next()) // 반복자 내의 원소를 하나씩 조화
    }

    ('a'..'c').forEach(::println) // 내부 순환

    // --> 결과
    //a
    //b
    //c
    //a
    //b
    //c
}