package kotlinAtoZ

import kotlin.math.pow
import kotlin.math.sqrt

class Outer {
    private val bar: Int = 1 // 외부 클래스의 비공개 속성

    class Nested {
        private val nestVar = 100 // 내포된 클래스
        fun foo() = 999 // 내포된 클래스의 메서드에서 외부 클래스 멤버

        // fun foo() = this@Outer.bar // 외부 클래스 멤버 참조시 예외 발생
    }
}

class Outer2 {
    private val bar: Int = 1 //외부 클래스의 비공개 속성

    inner class Inner {
        private val bar = 100 // 동일한 이름의 속성을 가지고 있음
        fun foo() = this@Outer2.bar // 내포된 클래스의 메서드 외부 비공개 속성 접근
        fun fbar() = bar // 비공개 속성에 접근할 수 있는 메서드 제공
    }

    fun getBar() = println(Inner().fbar())
}

fun localClasses() {
    open class Amphibian { // 함수 내부에 지역 베이스 클래스 정의
        open fun foo() = "foo"
    }

    class Frog : Amphibian() { // 상속받아서 지역 클래스 정의
        override fun foo() = "bar"
    }

    val amphibian: Amphibian = Frog() // 객체 생성
    println(amphibian.foo()) // 메서드 호출
}

var ar: Int = 999 // 전역 할당

class AB {
    fun methodsA(a: Int): Int { // 메서드
        ar += a // 전역 갱신
        return ar // 전역 참조
    }
}

open class Base {
    open fun method() = println("베이스 클래스 f()")
}

class Derived : Base() {
    override fun method() = println("파생 클래스 f()")
    inner class Inner {
        fun method() = println("이너클래스 f()")
        fun test() {
            this.method() // 이너 클래스 메서드 참조
            Derived().method() // 외부 클래스 메서드 참조
            super@Derived.method() // 슈퍼 클래스 메서드 참조
        }
    }
}

/* 지역 변수에 익명 클래스 생성 - 일회성 객체가 필요한 경우 내부에 정의해서 바로 사용 */
fun getLength(): Double {
    val point = object {
        val x: Double = 2.0
        val y: Double = 3.0
        override fun toString() = "Point($x, $y)"
    }
    println(point)
    return sqrt(point.x.pow(2.0) + point.y.pow(2.0))
}

/* 함수의 매개변수에 익명 객체 전달 */
interface Personnel { // 자료형으로 사용할 인터페이스 정의
    val name: String
    val age: Int
}

object Counter {
    private var count: Int = 0 // 비공개 속성 정의
    fun currentCount() = count // 비공개 속성 조회
    fun increment() = ++count // 비공개 속성 갱신
}

fun getObject(p: Personnel): Personnel { // 함수 매개변수와 반환값을 인터페이스로 자료형 지정
    return p
}

val p = getObject(object : Personnel { // 인자로 object 표현식을 생성된 객체 전달
    override val name = "달문" // 인터페이스 내의 추상 속성을 구현
    override val age = 55
})

class ObjectClass {
    object ObjectTest { // 싱글턴 객체 생성
        const val CONST_STRING = "1"
        fun test() {
            println("object 선언 : $CONST_STRING")
        }
    }
}

class CompanionClass {
    companion object { // 동반객체 정의
        const val CONST_TEST = 2
        fun test() {
            println("동반 객체 선언 : $CONST_TEST")
        }
    }
}

val person: Int = 0
    get():Int { // getter 메서드
        return field // 속성의 배킹 필드
    }

var man: Int = 0
    get() = field // getter 메서드
    set(value) { // setter 메서드
        field = value // 속성의 배킹 필드에 갱신
    }


fun Int.swap(other: Int): Pair<Int, Int> {
    var (first, second) = other to this
    return first to second
}

interface Balanceable {
    fun getBal(): Double // 잔액 조회 추상 메서드
    fun credit(amount: Int) // 입금 추상 메서드
}

class Balance(var balance: Double) : Balanceable {
    override fun getBal() = balance
    override fun credit(amount: Int) {
        balance += amount.toDouble()
    }
}

class AgreementManager(val balance: Balanceable) : Balanceable by balance {
}

fun AgreementManager.calBenefit(rate: Double) { // 이자 계산 후 입금 처리 확장함수로 구현
    val benefit = balance.getBal() * rate / 365 // 연 이자로 계산
    balance.credit(benefit.toInt())
}

fun main() {
    val demo = Outer.Nested() // 내포된 객체 생성은 외부클래스로 접근해서 생성
    println(demo.foo()) // 내포된 객체의 메서드 실행
    // Outer.Nested().nestVar // 내포 클래스의 비공개 속성 접근 시 예외 발생

    val demo2 = Outer2().Inner().foo() // 이너 클래스가 멤버 클래스라서 객체로 접근
    println(demo2)
    Outer2().getBar()
    //1
    //100

    localClasses()
    //bar

    println(AB().methodsA(10))// 전역 갱신 결과
    println(ar)

    val c1 = Derived()
    c1.Inner().test()

    println(getLength())
    // Point(2.0, 3.0)
    // 3.605551275463989
    println("객체 반환 이름 = ${p.name} 나이 = ${p.age}")
    // 객체 반환 이름 = 달문 나이 = 55

    Counter.increment()
    println(Counter.currentCount()) // 1

    CompanionClass.test()
    ObjectClass.ObjectTest.test()

    //동반 객체 선언 : 2
    //object 선언 : 1

    println(person) // 0
    man = 100
    println(man) // 100

    println((100).swap(200)) // (200, 100)

    val agreeMG = AgreementManager(Balance(100.00))

    agreeMG.credit(10000)
    println(agreeMG.getBal()) // 10100.0
    agreeMG.calBenefit(0.5)
    println(agreeMG.getBal()) // 10113.0
}