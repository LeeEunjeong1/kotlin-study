package kotlinAtoZ

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun main() {

    // NaN과의 연산은 항상 NaN
    println(Double.NaN + 100.0) // NaN
    // NaN은 비교할 수 없다
    println(Double.NaN == Double.NaN) // false

    val myString = "문자열 인덱스 조회"
    val item = myString[2]

    println(myString.first()) // 문
    println(myString.last()) // 회
    println(item) // 열
    println(myString.getOrNull(myString.length)) // null

    // 배열
    val arInt = arrayOf(1, 2, 3, 4)

    println("배열의 크기 = ${arInt.size}") // 배열의 크기 = 4
    println("배열의 원소의 합  = ${arInt.sum()}") // 배열의 원소의 합  = 10
    println("배열의 최대값 = ${arInt.maxOrNull()}") // 배열의 최대값 = 4
    println("배열의 최소값 = ${arInt.minOrNull()}") // 배열의 최소값 = 1
    println("배열의 평균 = ${arInt.average()}") // 배열의 평균 = 2.5
    println("배열의 개수 = ${arInt.count()}") // 배열의 개수 = 4

    for (i in arInt) {
        print("$i, ")
    }
    println()

    val arStr = arrayOf("코틀린", "자바")
    val x = arStr.indices.iterator() // 반복자로 변환
    while (x.hasNext()) { // 원소가 있는지 확인
        println(arStr.get(x.next()))
    }
    arStr.forEach { println(it) } // 내부 박복자로 처리

    val arr4 = arrayOf(3, 4, 2, 7, 8, 1) // 배열 객체 생성

    arr4.sort() // 내부 변경 정렬
    println(arr4.contentToString())
    arr4.reverse() // 내부 변경 역정렬
    println(arr4.contentToString())

    // [1, 2, 3, 4, 7, 8]
    // [8, 7, 4, 3, 2, 1]

    // 새로운 배열 생성 정렬
    val arr5 = arr4.copyOf() // 복사하기

    val arr6 = arr5.sorted()
    println(arr6)
    val arr7 = arr5.sortedDescending()
    println(arr7)
    val arr8 = arr5.reversed()
    println(arr8)

    checkDataType(100)
    checkDataType("문자")

    //100 은/는 문자열 아님
    //100 은/는 정수
    //문자열 은/는 문자열
    //문자열 은/는 정수 아님

    var x1: String = "100"
    var y1: String? = "3000"
    x1 = y1 as String
    println(x1) // 3000

    val a = 100
    val b = 200
    val c = 300

    val st = Triple(a, b, c) // 원소가 3개인 튜플로 구조화
    val (a_, b_, c_) = st // 튜플 원소를 변수에 할당 : 구조분해
    println(" $a_, $b_, $c_ ")
    println(" ${st.first},${st.second},${st.third}")
    println(" ${st.component1()},${st.component2()},${st.component3()}")

    val today = Calendar.getInstance()
    println("Year : ${today.get(Calendar.YEAR)}")

    val current = Date()
    var formatted = SimpleDateFormat("yyyy-MM-dd")
    println("Current : ${formatted.format(current)}")

    val currentDateTime = LocalDateTime.now()
    println(currentDateTime)
    //2023-08-20T17:35:56.091098

    val s = "TODAY IS A sunny day"
    val res = s.filter { e -> e.isLowerCase() }
    println("result : $res")
    //result : sunnyday

    val s1 = "Today is a sunny day, happy"
    println("replace : ${s1.replace("sunny", "rainy")}")
    println("contains : ${s1.contains("Today")}")

    val s2 = "독수리, 매, 올빼미, 까치"
    val birds = s2.split(",")
    println("split : $birds")
    println("joinToString : ${birds.joinToString(",")}")
}

fun checkDataType(value: Any) {
    if (value is String) {
        println("$value 은/는 문자열 ")
    }
    if (value !is String) {
        println("$value 은/는 문자열 아님")
    }
    if (value is Int) {
        println("$value 은/는 정수")
    }
    if (value !is Int) {
        println("$value 은/는 정수 아님")
    }
}


// 스마트캐스팅이 안될 경우
class Main {
    //    private val c: A = B()
//    private var c:A = B() // 어디서도 바뀔 수 있기 때문에 보장이 안된다. 스마트캐스팅 안됨
    private val c: A by lazy { B() } // lazy는 외부에서 위임을 시켜주는 것 (접근할때마다 get으로 가져온다)
    fun main() {
        val b: A = getB()
//        b.b()
        if (b is B) {
            (b as B).b() // 이렇게 안해도 된다.
            b.b() // 스마트 캐스팅이 된다.
        }

        if (c is B) {
//            c.b() // c가 B인걸 보장할 수 없다.
        }

        val a = getB() // 이 경우에만 캐스팅 된다.
        if (a is B) {
            a.b()
        }
    }

    fun getB(): A {
        return B()
    }
}

open class A {
    fun a() {

    }
}

class B : A() {
    fun b() {

    }
}