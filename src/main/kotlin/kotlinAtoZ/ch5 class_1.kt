package kotlinAtoZ

public class ClassName constructor(val property1: Int) : Any() {
    init {
        println("초기화 실행")
    }

    var property2: String = "초기화 값"

    constructor(parameter1: String, parameter2: Int) : this(parameter2) {
        var property3: String = parameter1
    }

    fun method1(): Unit {
        // 객체의 행위
    }

    class InnerClass {
        // 클래스 로직
    }

    object InnerObject {
        // 객체 로직
    }
}

//주 생성자로 객체 생성
class Person(name: String, age: Int) { // 주 생성자의 매개변수 지정
    val name = name // 속성 정의와 매개 변수로 초기화
    var age = age
}

class People(val name: String, val age: Int) // 본문이 속성을 주 생성자에 표시

fun main() {
    val c = Person("아프리카", 33) // 객체 인스턴스 생성
    val d = c // 객체 인스턴스 연결 : 동일한 객체

    println(d.name) // 아프리카
    println(c.name) // 아프리카

    val p = People("사우디", 33)
    println(p.name + " " + p.age) // 사우디 33

    val i = Init("윤돌", 20)
    println(i.name + " " + i.age) //윤돌 20

    val pno = NoConstructor() // 객체 인스턴스 생성
    println(pno.name + " " + pno.phoneNo)

    val pno1 = NoConstructor() // 객체 인스턴스 생성
    println(pno1.name + " " + pno1.phoneNo)
    // 초기화 처리
    // 후순봇 123
    // 초기화 처리
    // 후순봇 123

    val pno11 = PhoneNote(1234, "이은정", "개발자", "학생할래") // 매개변수가 4개인 보조 생성자 호출
    println(pno11.name + " " + pno11.phoneNo)
    val pno22 = PhoneNote(12345, "이은종", "피씨방사장님", "프로그래머") // 매개변수가 3개인 보조 생성자 호출
    println(pno22.name + " " + pno22.phoneNo)
    //초기화 처리
    //이은정 1234
    //초기화 처리
    //이은종 12345

    val cC = C.create(200)
    println(cC.foo()) // 100

    println(Klass::bar) // 클래스의 unbound reference
    println(Klass()::bar) // 클래스의 bound reference
    println((Klass)::foo) // 동반 객체의 bound reference
    println(Klass.Companion::foo) // 동반 객체의 bound reference

    (Klass::bar)(Klass()) // 객체를 전달해서 바운드 필요
    (Klass()::bar)() // 직접 객체가 메서드 실행

    val sup = Super()
    println(sup)
    println(sup.info())

    val sub = Sub()
    println(sub)
    println(sub.info())
    println(sub.getSuper())

    val pn = Person1("더님") // 슈퍼클래스 1레벨 객체 생성
    println(pn)
    println(pn.sayBye())

    val mn = Man("너님", 33) // 슈퍼클래스 2레벨 객체 생성
    println(mn)
    println(mn.sayBye())

    val st = Student("초등학교", "달님", 11) // 서브클래스 객체 생성
    println(st)
    println(st.sayBye())

//    Person(name=더님)
//    안녕히계세요
//    Man(name=너님, age=33)
//    안녕계세요 + 너님
//    Student(school=초등학교, name=달님, age=11
//    안녕계세요 + 달님

    val pet = Pet("개","푸들",4) // 객체 생성
    println("종 : ${pet.species} 세부종 : ${pet.subSpecies}") // 속성 출력

}

//초기화 블록 실행
class Init(name: String, age: Int) {
    var name: String = ""
    var age: Int = 0

    init { // 초기화 블록 정의
        this.name = name // 쵝화 블록에서는 생성자의 매개변수 사용
        this.age = age // 속성 이름 앞에 this로 현재 객체 표시
        println(" 주 생성자와 같이 실행") // 주 생성자와 같이 실ㄹ행
    }
}

//주 생성자나 보조 생성자가 없는 클래스 정의
class NoConstructor {
    val phoneNo: Int = 123 // 내부 속성
    val name: String = "후순봇"
    var job: String = ""
    var etc: String = ""

    init { // 초기화 블록은 객체 생성할 때마다 처리됨
        println("초기화 처리")
    }
}

//보조 생성자만 있는 클래스로 객체 생성
//
//주 생성자와 보조 생성자 모두 정의
//
//주 생성자에만 속성 정의
//
//보조 생성자 오버로딩
class PhoneNote(val phoneNo: Int, val name: String) {
    var job: String = ""
    var etc: String = ""

    init {
        println("초기화 처리")
    }

    constructor(phoneNo: Int, name: String, job: String) : this(phoneNo, name) {
        this.job = job
    }

    constructor(phoneNo: Int, name: String, job: String, etc: String) : this(phoneNo, name, job) {
        this.etc = etc
    }
}

// 주 생성자 가시성 처리
class C private constructor(val a: Int) { // 비공개 생성자
    companion object {
        private val bar = 100 // 컴패니언 객체 내의 비공개 속성
        fun create(x: Int): C { // 컴패니언 객체 내에서 생성자 메서드로 정의해서 처리
            return C(x)
        }

        fun getBar() = bar // 공개 메서드로 조회
    }

    fun foo() = getBar() // 컴패니언 객체의 비공개 속성의 결과를 조회
}

class Klass { // 클래스 정의
    companion object { // 동반 객체 선언
        fun foo() {} // 동반 객체 내부 메서드
    }

    fun bar() { // 클래스 메서드
        println("bar 실행 ")
    }
}

open class Super {
    override fun toString() = "Super(id=${this.hashCode()})"
    open fun info() = "슈퍼 클래스 정보 확인 "
    fun getSuper() = "슈퍼 클래스의 메소드" // 재정의 불가
}

class Sub : Super() {
    override fun toString() = "Sub(id=${this.hashCode()})"
    override fun info() = "서브 클래스 정보 확인"
}

open class Person1(val name: String) { // 슈퍼클래스
    fun sayHello() = "안녕하세요" // 재정의 불가
    open fun sayBye() = "안녕히계세요" // 하위클래스에서 재정의 가능
    override fun toString() = "Person(name=$name)" // 상위클래스 메서드 재정의
}

open class Man(name: String, val age: Int) : Person1(name) {
    final override fun sayBye() = "안녕계세요 + $name" // 하위 재정의 금지
    override fun toString() = "Man(name=$name, age=$age)" // 상위 클래스 메서드 재정의
}

class Student(val school: String, name: String, age: Int) : Man(name, age) {
    override fun toString() = "Student(school=$school, name=$name, age=$age" // 상위 클래스 메서드 재정의
}

open class Animal(val species: String) // 슈퍼클래스 주 생성자

class Pet(species: String, val subSpecies: String) : Animal(species) {
    constructor(species: String, subSpecies: String, age: Int) : this(species, subSpecies) // 슈퍼클래스 위임호출
}
