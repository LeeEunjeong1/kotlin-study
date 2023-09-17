package kotlinAtoZ

fun purefunc(a: Int, b: Int): Int {
    return a + b // 입력되는 인자에 의해 결정
}


fun nonpure1(a: String) {
    println("비순수함수 $a") // 외부에 출력
}

var state = 100
fun nonpure2(x: Int): Int {
    state += x
    return state
}

val func by lazy { { x: Int -> x } } // 속서 위임에 초깃값을 함수로 전달

val seq = generateSequence(0) { it + 100 } // 무한 시퀀스 정의

fun add(a: Int, b: Int) = a + b
fun outer(a: Int): (Int) -> Int { // 함수로 부분 함수 정의
    fun inner(b: Int): Int = a + b // 내부 함수 정의
    return ::inner // 내부 함수 반환
}

class Car2(var ownerName: String) {
    fun changeOwner(newName: String): Car2 {
        this.ownerName = newName
        return this
    }

    fun info(): Unit = println("Car 소유자 : $ownerName")
}

typealias f = (Int, Int) -> Int // 함수 자료형을 타입 별칭으로 지정

fun highfunc(vararg x: Int, op: f): Int { // 함수를 매개변수로 받는 고차함수
    return x.toList().reduce(op)
}

class Student1(val id: Int, var name: String, var age: Int)

fun composeF(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int {
    return { p1: Int -> f(g(p1)) }
}

fun factorial(n: Int): Long {
    if (n == 1) { // 마지막 처리하는 코드
        return n.toLong()
    } else {
        return n * factorial(n - 1) // 재귀함수 작성. 인자는 항상 이전보다 작아야 함
    }
}

// 꼬리 재귀함수
tailrec fun factorial1(n: Int, total: Int = 1): Long {
    if (n == 1) {
        return total.toLong()
    } else {
        return factorial1(n - 1, n * total) // 꼬리 재귀를 위해 함수에 변경 값을 전달
    }
}

class Person3(var name: String, var age: Int)// 클래스를 정의

class Person4 {
    var name = "코틀린"
    private val id = "9999"
    var age = 0
}

fun interface StringSAMable { // sam은 인터페이스 앞에 fun을 붙인다
    fun accept(s: String): Unit // 추상 메서드 한 개만 가짐
}

inline fun compose_(
    a: Int,
    action: (Int) -> Int, // 고차함수를 인라인 함수로 처리
    block: (Int) -> Int // 두 개의 함수를 매개변수
): Int {
    return action(a) + block(a)
}

fun callingHOF() { // 인라인 함수를 내부에서 호출
    println(compose_(10, { x -> x * 10 }, { y -> y + 10 })) // 두 개의 람다표현식 전달
}

inline fun highNoinline(
    block: () -> Unit,
    noinline noinline: () -> Unit,
    block2: () -> Unit
) {
    block() // 인라인 처리
    noinline() // 노인라인 처리
    block2() // 인라인 처리
}

fun callingFunction() {
    highNoinline({ println("람다표현식 1") },
        { println("노인라인 람다표현식 2") },
        { println("람다표현식 3") })
}

inline fun higherOrderFunc(crossinline aLambda: () -> Unit) { // 실제 지역반환 처리를 금지시킨다
    normalFunc { // 다른 함수에서 람다표현식 실행
        aLambda()
    }
}

fun normalFunc(block: () -> Unit) {
    println("정상함수 호출 111")
    block()
}

fun callingFunc() {
    higherOrderFunc { // 고차함수 호출
        println("람다함수 호출 222")
        // return // 비지역 반환 금지
    }
}

fun main() {
    println(purefunc(10, 20)) // 함수를 계속 호출해도 결과 같음
    nonpure1("외부 출력") // 함수를 호출하면 인자와 관계없이 외부와 연계 처리
    println("state : $state nonpure2 : ${nonpure2(108)}")
    println("state : $state nonpure2 : ${nonpure2(108)}")

    println(func(100))
    println(seq.take(5).toList())

    val add2 = outer(1)
    println(add2(2)) // 3

    val c = Car2("이은정")
    c.info()
    c.changeOwner("은정").info() // 메서드 체인 처리

    println(highfunc(1, 2, 3, 4, op = { x: Int, y: Int -> x + y })) // 합산하는 람다표현식 전달

    val s = Student1(1, "ej", 20)
    println(s.name)
    val ss = s.let {
        it.name = "lee" // 내부 갱신
        it// 객체 전달
    }
    println("처리 결과 : ${ss.name} ${ss.age}")

    val s1: Student1? = null // 널러블 처리도 가능
    if (s1 == null) s1
    else s1.let { it.name = "nullname" } // 널이 아니면 실행
    println(s1?.let { it.name = "ej ej" }) // 널이 아니면 함수 실행

    val lr: Int.(Int) -> Int = { x -> this + x }
    println(lr(100, 200)) // 내부적으로 두 개의 인자로 처리
    println((100).lr(200)) // 수신 객체 정의하고 람다표현식을 처리
    println(with(100) { this + 200 }) // 수신 객체를 인자로 전달하고 this로 람다표현식 내부에서 처리
    val f = { x: Int -> x + 2 } // 첫 번째 함수
    val g = { y: Int -> y + 3 } // 두 번째 함수 : 함수 내부에 결합되는 함수
    val composeFunc = composeF(f, g) // 두 개의 함수를 인자로 전달

    println(f(g(3))) // 두 함수를 결합해서 실행
    println(composeFunc(3)) // 합성함수로 반환된 함수 실행

    val number = 4
    var result: Long = 0
    result = factorial(number)
    println("팩토리얼 계산 : $number = $result")

    result = factorial1(number)
    println("꼬리 재귀 팩토리얼 계산 : $number = $result")


    val op: Int.(Int) -> Int = { x -> x + this } // 함수 자료형에 특정 자료형을 수신 객체로 지정
    println((100).op(100)) // 람다표현식에 수신 객체를 붙이면 메서드처럼 객체를 사용할 수 있다.
    // 200

    val person = Person3("Ej", 20) // 객체를 생성
    val ageNextYear = person.run { // 객체로 run 함수 사용. 람다 표현식은 이 객체 내부의 속성 갱신
        ++age
        this
    }
    println("반환은 수신객체로 처리 = ${ageNextYear.age}")

    val person4 = Person4()
    val also1 = person4.also { it -> println("이름은 ${it.name}") }
    println("also1 ${also1::class.simpleName}")

    val apply1 = person4.apply { println("이름은 $name") }
    println("apply1 ${apply1::class.simpleName}")

    val cosume1 = StringSAMable { s -> println(s) } // sam에 재정의 함수를 람다표현식으로 전달
    cosume1.accept("바로 람다표현식을 전달해서 재정의")

    callingHOF() // 120
    callingFunction()
    callingFunc()
}

// let
fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}

// with
//fun <T,R>with(reciever:T,block: (T) -> R):R{
//    return block()
//}

// run
fun <T, R> T.run(block: T.() -> R): R {
    return block()
}

// also
public inline fun <T> T.also(block: T.() -> Unit): T {
    block(this)
    return this
}

// apply
public inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}