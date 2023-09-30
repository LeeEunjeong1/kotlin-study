package kotlinAtoZ

import kotlin.properties.Delegates

interface Base { // 인터페이스 정의
    fun say()
}

class BaseImpl(val x: Int) : Base { // 인터페이스를 구현한 위임 클래스 정의
    override fun say() { // 메서드 정의
        println("베이스 클래스 구현 : $x")
    }
}

class Derived(val b: BaseImpl) : Base { // 인터페이스를 구현하고 위임 처리할 객체를 인자로 받음
    override fun say() { // 위임 처리할 메서드 구현
        b.say() // 실제 처리할 메서드 호출
    }
}

class Derived_() : Base by BaseImpl(10) //  클래스 객체를 생성해서 by로 위임

interface Sayable {
    fun say()
}

class Person_(val x: String) : Sayable {
    override fun say() {
        println("안녕하세요 : $x")
    }
}

class Pet_(val x: String) : Sayable {
    override fun say() {
        println("멍멍멍 : $x")
    }
}

class Saying(val say: Sayable) : Sayable by say // 매개변수로 전달한 객체로 위임 처리

interface Showable { // 인터페이스 정의
    fun show()
}

open class View : Showable { // 상속 가능한 구현 클래스 정의
    override fun show() { // 메서드 구현
        println("View 클래스의 show()")
    }
}

class CustomView : View() { // 클래스를 상속해서 구현 클래스 정의
    override fun show() { // 메서드 재정의
        println("CustomView 클래스의 show()")
    }
}

class Screen(val showable: Showable) : Showable by showable // 인터페이스만 위임 처리 가능

lateinit var str: String // 최상위 속성 지연 초기화는 참조 객체만 가능
//lateinit var int :Int // 최상위 속성 지연 초기화할때는 기본 자료형은 불가

val a: Int by lazy { 0 } // 최상위 속성 val 지연처리

class LazyVar {
    val lazya: String by lazy { "초기값" } // 클래스 내의 속성 val 지연처리
    lateinit var lateb: String // 클래스 내의 속성
}

class Delegate { // 위임 속성 처리
    private var value: String? = null
    fun getValue_(): String {
        return value ?: "초기값"
    }

    fun setValue_(value: String) {
        this.value = value
        println("속성 위임 갱신")
    }
}

class Bar { // 위임 관리 클래스 정의
    val del = Delegate()
    var p: String
        get() = del.getValue_()
        set(value: String) = del.setValue_(value)
}

fun main() {
    val bb = BaseImpl(10)
    Derived(bb).say()

    Derived_().say() // 인터페이스의 메서드 실행

    val ps = Person_("사람")
    val pt = Pet_("개")

    Saying(ps).say()
    Saying(pt).say()


    val view = View() // 베이스 클래스 객체 생성
    val customView = CustomView() // 구현 클래스 객체 생성

    Screen(view).show() // View.show()
    Screen(customView).show() // CustomView.show()

    var str1: String by Delegates.notNull<String>()
    var int1: Int by Delegates.notNull<Int>()
    str = "초기화 처리"
    str1 = "초기화 처리"
    int1 = 100
    println("lateinit 처리 = $str")
    println("notNull  String 처리 = $str1")
    println("notNull Int 처리 = $int1")

    var observed = false
    var max: Int by Delegates.observable(0) { _, oldValue, newValue ->
        println("변경전 : $oldValue 변경후 : $newValue")
        observed = true
    }
    println(max)
    println("관찰상태  = $observed")
    max = 10
    println(max)
    println("관찰상태  = $observed")

    var vetoableField: Int by Delegates.vetoable(0) { _, old, new ->
        println("변경 전 : $old, 변경 후 : $new")
        new % 2 == 0 // 조건이  참인 경우 : 짝수 값일 경우만 갱신
    }

    println(vetoableField)
    vetoableField = 1 // 홀수일 때는 변경되지 않음
    println(vetoableField)
    vetoableField = 2 //짝수일때만 변경됨
    println(vetoableField)

    println(a)
    val lz = LazyVar()

    println(lz.lazya) // val 지연은 처음 조회활 때 초깃값 처리
    lz.lateb = "지연 초기화" // var 지연 처리 초기화
    println(lz.lateb) // 갱신값 확인
    lz.lateb = "값 갱신" // var 값 갱신
    println(lz.lateb) // 갱신값 확인

    val b = Bar() // 객체 생성

    println(b.p) // 속성 조회
    b.p = "변경" // 위임 속성 갱신
    println(b.p) // 속성 조회
}