package kotlinAtoZ

abstract class Person2 {
    var age: Int = 20 // 일반 속성 정의
    abstract val name: String

    open fun displaySSN(ssn: Int) { // 일반 메서드 정의
        println("주민번호 : $ssn")
    }

    abstract fun displayName()
}

class Woman : Person2() {
    override val name: String = "은정"
    override fun displayName() {
        println("이름 : $name")
    }

    override fun displaySSN(ssn: Int) { // 일반 메서드 오버라이딩
        super.displaySSN(ssn) // 추상 클래스의 일반메서드 호출
    }
}

abstract class BiOperator {
    abstract fun cal(x: Int, y: Int): Int
}

class Add : BiOperator() {
    override fun cal(x: Int, y: Int) = x + y
}

class Sub1 : BiOperator() {
    override fun cal(x: Int, y: Int) = x - y
}

fun cal(obj: BiOperator, x: Int, y: Int) = obj.cal(x, y)

abstract class Weapon {
    fun move() {
        println("이동합니다.")
    }

    abstract fun attack()
}

var w2 = object : Weapon() { // 객체 표현식으로 익명 객체
    override fun attack() { // 추상 메서드 구현
        println("공중 공격을 해요")
    }
}

abstract class Base1 {
    abstract fun String.extension(x: String): String
}

class Derived1 : Base1() {
    override fun String.extension(x: String): String {
        return "$this $x !!!"
    }

    fun callExtension(c: String): String {
        return "hello".extension(c)
    }
}

interface Clickable { // 인터페이스 정의
    fun up(): Unit // 추상 메서드
    fun down(): Unit
}

class TvVolumn : Clickable { // 인터페이스 상속
    override fun up() { // 추상메서드 구현
        println("tv 볼륨을 올려요")
    }

    override fun down() {
        println("tv 볼륨을 내려요")
    }
}

interface Aable { // 인터페이스 정의
    fun callMe() { // 일반 메서드
        println("인터페이스 Aable")
    }
}

interface Bable {
    fun callMe() {
        println("인터페이스 Bable")
    }
}

class Child : Aable, Bable { // 인터페이스 상속
    override fun callMe() { // 일반 메서드 재정의
        super<Aable>.callMe() // super를 사용해 Aable 호출
        super<Bable>.callMe() // super를 사용해 Bable 호출
    }
}

interface Aable1 { // 최상위 인터페이스 정의
    fun absMethod(): Unit
}

interface Bable1 {
    fun bMethod(): Unit
}

interface Cable : Aable1, Bable1 { // 인터페이스 상속
    override abstract fun absMethod(): Unit // 상속한 인터페이스 재정의
    fun method(): Unit
}

class ABablity : Cable {
    override fun absMethod() = println("야호 !!!")
    override fun bMethod() = println("낙성대")
    override fun method() = println("관악산")
}

sealed class SealedClass { // 봉인 클래스 정의
    class SubX(val intVal: Int) : SealedClass() // 내부 클래스 정의
    class SubY(val stringVal: String) : SealedClass()
}

class SubZ(val longVal: Long) : SealedClass() // 외부 클래스 정의

fun printlnType(type: SealedClass): String =
    when (type) {
        is SealedClass.SubX -> "매개변수 타입 : integer"
        is SealedClass.SubY -> "매개변수 타입 : string"
        is SubZ -> "매개변수 타입 : long"
    } // else 필요 없음

fun main() {
    val woman = Woman()
    woman.displayName()
    println(woman.age)
    woman.displaySSN(1234)

    val add: BiOperator = Add() // 객체 생성
    val x1 = cal(add, 10, 20)
    println("덧셈 : $x1") // 덧셈 : 30

    val sub: BiOperator = Sub1()
    val x2 = cal(sub, 10, 20)
    println("뺄셈 : $x2") // 뺄셈 : -10

    w2.move() // 이동합니다.
    w2.attack() // 공중 공격을 해요

    val base = Derived1()
    println(base.callExtension("코틀린"))
    fun Base1.selectPrint() = println("추상클래스의 확장함수")
    base.selectPrint()

    // hello 코틀린 !!!
    // 추상클래스의 확장함수

    val vol = TvVolumn() // 객체 생성
    vol.up() // 메서드 실행
    vol.down()

    val obj = Child()
    obj.callMe()

    val a: Aable1 = ABablity()
    // a.method() // 제공하지 않는 메서드 호출시 에러
    a.absMethod()

    val b: Bable1 = ABablity()
    b.bMethod()

    val c: Cable = ABablity()
    c.bMethod()

    println(printlnType(SubZ(100)))
    println(printlnType(SealedClass.SubY("문자열")))
    println(printlnType(SealedClass.SubX(100)))
}
