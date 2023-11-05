package kotlinAtoZ

import java.lang.StringBuilder

fun main() {
    println(함수명<String>("이은정", "은정"))
    println(add1<Int>(10, 10) { x, y -> x + y })

    println(sum(100, 200) { x, y -> x + y })

    println(sumA(100, 200) { x, y -> x + y }) // 300
    println(sumA(100.1, 200.1) { x, y -> x + y }) // 300.2
    // println(sumA("봄","여름"){x,y->x+y}) // 자료형 제한으로 오류

    var name = StringBuilder("사랑하자!!") // 갱신 가능한 문자열 빌더 객체 만들기
    suffix(name) // 함수 호출해서 문자열 추가
    println(name) // 사랑하자!!코틀린

    println(11.map { it * it }) // 121

    val com: Company = Company("인공지능") // 초기화 => 인공지능
    val com1 = Company1<Int>(12) // 초기화 => 12

    val aimp = AnimalImpl("코끼리") // 문자열 전달 : 타입 추론으로 타입 인자 처리
    println(aimp.func()) // 코끼리
    val aimpdog = AnimalImpl(Dog())
    println(aimpdog.func().bark()) // 멍멍

    var x: MyClass1<Any> = MyClass1<Int>()
    println(x.hashCode()) // 1324119927

    var a: Container<Dog1> = Container<Animal1>() // 하위 타입에 상위 타입 할당
    println(Dog::class.supertypes) // 하위타입일 경우 슈퍼타입을 확인할 수 있다
    println(a.javaClass.kotlin)

    val addF = ::add2 // 함수 참조와 변수 할당
    println(addF(10, 20))

    val oa1 = A1::a
    val oaCon = A1::CONST
    val oa2 = A1::getFull

    println(oa1.get()) // 30
    println(oaCon.get()) // 100
    println(oa2) // 1000

}

fun <타입> 함수명(매개변수1: 타입, 매개변수2: 타입): String { // 제네릭 함수 정의
    return "매개변수1 = $매개변수1, 매개변수2 = $매개변수2" // 반환값 처리
}

fun <T> add1(x: T, y: T, op: (T, T) -> T): T = op(x, y)

fun <T, R> sum(x: T, y: T, op: (T, T) -> R): R { // 두 개의 타입 매개변수 중 하나는 매개변수
    return op(x, y) // 하나는 반환 값 처리
}

fun <T : Number> sumA(x: T, y: T, action: (T, T) -> T): T { // 숫자 자료형만 처리 가능
    return action(x, y)
}

fun <T> suffix(str: T) where T : CharSequence, T : Appendable { // 문자 시퀀스와 추가가 가능
    str.append("코틀린") // 추가 메서드 처리
}

fun <T> T.map(block: (T) -> T): T {
    return block(this)
}

class Company(text: String) { // 일반 클래스 정의. 주 생성자는 매개변수 처리
    var x = text // 속성 정의

    init {
        println("초기화 => $x")
    }
}

class Company1<T>(text: T) { // 제네릭 클래스 정의와 매개변수 타입 지정
    var x = text

    init {
        println("초기화 => $x")
    }
}

interface Animalable<T> { // 제네릭 인터페이스 정의 : 타입 매개변수
    val obj: T // 추상 속성과 추상 메서드에 타입 매개변수
    fun func(): T
}

class Dog {
    // 일반 클래스 정의
    fun bark() = "멍멍"
}

class AnimalImpl<T>(override val obj: T) : Animalable<T> { // 제네릭 클래스에서 제네릭 인터페이스 상속
    override fun func(): T = obj
}

class MyClass1<out T>

open class Animal1 // 슈퍼클래스 정의
class Dog1 : Animal1() // 서브클래스가 슈퍼클래스 상속

class Container<in T> // 반공변성을 가지는 클래스 정의

fun acceptList(list: ArrayList<Any>) { // 가변인 리스트에 타입 매개변수 Any 지정
    list.add("문자열")
    list.add(1)
    println(list)
}

@Deprecated("USe removeAt(index) insted")// 경고 처리
class ABC {
    var field1 = ""
    var field2 = 0
    fun function1() {}
    fun function2() {}
}

fun add2(x: Int, y: Int): Int = x + y

object A1 {
    const val CONST = 1000
    val a = 100
    fun getFull(): Int = a
}