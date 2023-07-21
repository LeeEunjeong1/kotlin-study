package kotlinAtoZ

fun main() {
    val h = Hello()
    println(h.hello)

    // 정수와 실수도 객체이다
    num()
    string()

    // 객체는 메서드로 연산을 수행한다

    println("${100+100} / ${100.plus(100)}")
    println("${100-100} / ${100.minus(100)}")
    println("${200/100} / ${200.div(100)}")
    println("${300%7} / ${300.rem(7)}")
    println("${10*20} / ${10.times(20)}")
    println("${300%7} / ${300.rem(7)}")


    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()

    print("a= $a"); print(" b= $b");print("\n")
    println(" sum ${a + b}")

    /*
    stdin: 100
    stdin:200
    a = 100 b= 200
     sum 300
    */
}



class Hello {
    val hello = "Hello"
}

fun num() {
    val intVar = 100
    val longVar = 100L
    val doubleVar = 100.0
    val floatVar = 100.0F

    println("intVar :: ${intVar.javaClass.kotlin}") // intVar :: int
    println("longVar :: ${longVar.javaClass.kotlin}") // longVar :: long
    println("doubleVar :: ${doubleVar.javaClass.kotlin}") // doubleVar :: double
    println("floatVar :: ${floatVar.javaClass.kotlin}") // floatVar :: float
}

fun string(){
    val charVar = 'a'
    val stringVar = "string"
    val boolVar = true

    println(charVar.javaClass) // char
    println(stringVar.javaClass) // class java.lang.String
    println(boolVar.javaClass) // boolean
}
