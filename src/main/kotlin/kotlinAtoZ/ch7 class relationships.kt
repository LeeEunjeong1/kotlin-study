package kotlinAtoZ

class Address(
    val streetNum: Int,
    val city: String,
    val state: String,
    val country: String
) {
    fun printAddr() {
        println("주소  = $streetNum $city $state $country")
    }
}

class College(
    val collegeName: String,
    val collegeAddr: Address // 다른 클래스를 속성에 할당
)

class CarEngine { // 조합대상 클래스
    fun startEngine() {
        println("엔진 가동")
    }
}

open class Car( // 베이스 클래스
    var colour: String,
    var maxi_speed: Int
) {
    fun carDetails() {
        println("차 색상 : $colour , 최고 속도 : $maxi_speed")
    }
}

class CarProduct(color: String, max_speed: Int) : Car(color, max_speed) { // 조합을 구성하는 클래스
    lateinit var carEngine: CarEngine
    fun startCarProduct() {
        carEngine = CarEngine() // 다른 클래스로 조합 구성
        carEngine.startEngine()
    }
}

class Account(val accountNo: Int, var balance: Int = 0) { // 의존 처리 클래스
    fun depoosit(amount: Int) {
        println("입금")
        balance += amount
    }
}

class Customer(var balance: Int) {
    fun makeDeposit(acc: Account) { // 메서드의 매개변수로 의존 클래스 전달
        acc.depoosit(balance) // 내부에서 의존 클래스의 메서드 실행
    }
}

class Counterr {
    var value: Int = 0 // 변경 가능한 속성 정의
        get() {
            println("get value $field")
            return field
        }
        private set // 비공개 속성을 사용해서 외부 갱신 금지

    fun inc_() = value++ // 메서드로 내부에서 비공개 속성 갱신
}

class Amount(var total: Int, var balance: Int) { // 클래스 정의
    operator fun plus(other: Amount) = Amount( // 메서드로 연산자 오버로딩
        this.total + other.total,
        this.balance + other.balance
    )

    operator fun plus(scale: Int) = Amount(
        this.total + scale,
        this.balance + scale
    )

    override fun toString() = "Amount($total, $balance)"
}

infix fun Int.add(x: Int): Int {
    return this + x
}

data class User(val name: String, var age: Int)

enum class CardType(val color: String) { // 클래스에 속성 추가
    SILVER("gray"),
    GOLD("yellow"),
    PLATINUM("black")
}

fun changeCardType(cardType:CardType) = when(cardType){
    CardType.SILVER -> CardType.GOLD
    CardType.GOLD -> CardType.PLATINUM
    CardType.PLATINUM -> CardType.SILVER
}

inline class Password(val value: String) // 인라인 클래스 만들기

@JvmInline
value class Member(val value:String) // 어노테이션으로 인라인 클래스 만들기 -> inline 대신 value로 작성
fun main() {
    val ad1 = Address(55, "관악구", "서울시", "대한민국")// 주소 생성
    val obj1 = College("서울대", ad1) // 주소 배정

    println(obj1.collegeName) // 서울대
    obj1.collegeAddr.printAddr() // 주소  = 55 관악구 서울시 대한민국

    val carJazz = CarProduct("Red", 240)
    carJazz.carDetails()
    carJazz.startCarProduct()

    val acc = Account(123)
    val cus = Customer(3000)

    cus.makeDeposit(acc)
    println(acc.accountNo)
    println(acc.balance)

    val counter = Counterr()
    for (i in 1..5) {
        counter.inc_() // 외부에서는 메서드로 속성 갱신
    }
    println(counter.value)
    //get value 0
    //get value 1
    //get value 2
    //get value 3
    //get value 4
    //get value 5
    //5

    val amt = Amount(200, 100)
    val amt2 = Amount(300, 100)
    val amt3 = amt + amt2
    println(amt3) // Amount(500, 200)

    val amt4 = amt2 + 100
    println(amt4) // Amount(400, 200)

    println(3.add(5))
    println(3 add 5)

    val u1 = User("Ej", 20)
    val u2 = User("Ej", 20)
    println(u1 == u2) // true
    println(u1 === u2) // false

    val u3 = User("Eunjeong", 20)
    val u4 = u3.copy(age = 10)
    println(u3 == u4) // false
    println(u3 === u4) // false

    println(CardType.PLATINUM.name) // 객체의 이름
    println(CardType.PLATINUM.ordinal) // 객체의 순서
    println(CardType.SILVER < CardType.PLATINUM) // 객체간 비교

    println(CardType.SILVER.color) // 이넘 객체 내부의 속성 조회
    println(CardType.valueOf("SILVER")) // 클래스 메서드르 이넘값 조회

    // No actual instantiation of class 'Password' happens
// At runtime 'securePassword' contains just 'String'
    val securePassword = Password("Don't try this in production")
    println(securePassword)
}
