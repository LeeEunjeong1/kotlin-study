fun main() {

    val 결과값 = 함수명("함수", "호출")

    println(결과값)


    val toUpperCase = object : Function1<String, String> {
        override fun invoke(p1: String): String {
            return p1.uppercase()
        }
    }

    println((fun(매개변수1: Int, 매개변수2: Int): Int { // 익명함수 즉시 실행
        return 매개변수1 + 매개변수2
    })(100, 200))

    println({ "아무 인자가 없다." }())  // 인자가 없는 경우
    println({ x: Int -> x * x }(10)) // 인자가 하나 있는 경우
    println({ x: Int, y: Int -> x * y }(10, 20)) // 인자가 두 개 있는 경우
}

fun 함수명(매개변수명1: String, 매개변수명2: String): Pair<String, String> { // 반환타입 : 튜플처리

    val 지역변수1 = 100
    var 지역변수2 = 300

    fun 지역함수명(매개변수명: String): String {
        return "매개변수명"
    }

    class 지역클래스명 {}

    //object 지역 오브젝트 {} // 함수 내부에서 지정 금지

    println(" $지역변수1 $지역변수2")// 지역변수를 사용하지 않으면 컴파일 에러

    return Pair(매개변수명1, 매개변수명2) // 튜플로 반환값 전환

}

fun add(x: Int, y: Int): Int {
    return x + y
}

fun add1(x: Int, y: Int): Int = x + y

fun comSingle(x: Int, y: Int) = if (x > y) x else y // if표현식 사용

val r = comSingle(20, 10)

fun addVar(x: Int, y: Int, z: Int) = x + y + z

fun defaultArg(x: Int = 100, y: Int = 200) = x + y

fun addVarArg(vararg x: Int): Int {
    var result = 0
    for (i in x) {
        result += i
    }
    return result
}

var outVar = 300
val outVarR = 999

fun outerFunc1(x: Int): Int {
    val y = 100
    fun localFunc() = x + y + outVarR
    return localFunc()
}

fun outerFunc2(x: Int): Int {
    val y = 100
    fun localFunc(): Int {
        outVar += x
        return x + y + outVar
    }
    return localFunc()
}

fun outerFunc(x: Int): Int {
    val y = 100
    fun innerFunc(): Int { // 지역함수 정의
        var z = 777
        z += outVar
        fun localFunc() = x + y + z // 지역함수 내의 지역함수 정의
        return localFunc()
    }
    return innerFunc()
}


val 덧셈 = fun(매개변수1: Int, 매개변수2: Int): Int { // 익명함수를 변수에 할당
    return 매개변수1 + 매개변수2
}

val res1 = 덧셈(300, 200) // 익명함수를 실행

val res2 = (fun(매개변수1: Int, 매개변수2: Int): Int { // 즉시 실행
    return 매개변수1 + 매개변수2
})(500, 200)

val res3 = (fun(x: Int): (Int) -> Int { // 외부 익명함수
    val inner = fun(y: Int): Int { // 내부 익명함수
        return x + y
    }
    return inner // 변수로 반환
})(10)(20)

val res4 = (fun(x: Int): (Int) -> Int { // 외부 익명함수
    return fun(y: Int): Int { // 바로 반환 내부 익명함수
        return x + y
    }
})(10)(20)

val res5 = fun(x: Int, y: Int, f: (Int, Int) -> Int): Int { // 함수를 매개변수로 받음
    return f(x, y) // 함수 실행결과를 전달
}
//300

val a = { x: Int, y: Int -> x + y } // 재사용하려면 변수에 할당
fun func(x: Int, y: Int, f: (Int, Int) -> Int): Int { // 함수 매개변수를 가지는 함수
    return f(x, y)
}

// 함수는 정의 후에 호출
fun add2(x: Int, y: Int) = x + y


fun outer(x: Int) { // 외부함수
    fun inner(y: Int) = x + y // 내부함수 -> 외부함수 변수 사용
    println(inner(x)) // 내부함수 실행 -> 외부함수 지역변수 인자 제공
}
// 20

fun outer1(x: Int): Int {
    fun inner(y: Int) = x + y
    return inner(x) // 내부함수 실행 결과
}

fun outer22(x: Int): Int {
    return (fun(y: Int): Int {
        return x + y
    })(10)
}

fun outer33(x: Int): Int {
    return { y: Int -> x + y }(10)
}

fun outer2(x: Int): (Int) -> Int { // 함수를 참조로 변환
    fun inner(y: Int) = x + y
    return ::inner
}

val inner1 = outer2(10)

fun outer3(x: Int): (Int) -> Int {
    return { y: Int -> x + y }
}

val inner2 = outer3(10)

fun outer4(x: Int): (Int) -> Int {
    return fun(y: Int) = x + y
}

val inner3 = outer4(10)

val a1: () -> Unit = { println("함수 ") } // 매개변수 없고 반환값은 Unit으로 처리
val b: (Int) -> Int = { x -> x * 3 } // 하나의 매개변수로 처리하고 반환값은 Int
val c: (Int, Int) -> Int = { x, y -> x + y } // 두 개의 매개변수로 처리하고 반환값은 Int



// 함수
// 30
// 30


// 함수 자료형도 널 자료형이 가능
fun nullFunc(action: (() -> Unit)?): Long { // 함수 자료형 전체를 괄호로 묶고 난 후에 물음표
    val start = System.nanoTime() // 시스템 시간을 조회
    action?.invoke() // 널이 들어올 수 있으니 ? 이후 실행 처리

    return System.nanoTime() - start // 최종 처리 시간 표시
}


fun unitFunc() = println("함수처리") // 함수 참조 전달
// 함수처리
// 70827

val toUpperCase2 = { str: String -> str.uppercase() }

/* 매개변수 개수가 다른 함수 오버로딩 */
fun func(a: String, b: String): String {
    return "func"
}

fun func(a: String): Int{
    return 100
}

/* 매개변수 개수가 동일한 함수 오버로딩 */
fun test1(a: String, b: String? = null) {
    println("test1")
}

fun test1(a: Int, b: String) {
    println("test2")
}