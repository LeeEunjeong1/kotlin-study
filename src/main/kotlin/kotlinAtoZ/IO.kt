package kotlinAtoZ

import java.io.*

fun main() {
    var fin = FileInputStream("./data.txt")
    var fout = FileOutputStream("./dataout.txt")

    var data = fin.read()
    println("바이트 하나 읽기 : $data , ${data.toChar()}")
    println("데이터 자료형 확인 : ${data.javaClass.kotlin}")

    while (data != -1) {
        fout.write(data)
        data = fin.read()
    }

    fin.close()
    fout.close()

    val fin1 = FileInputStream("./dataout.txt")
    var data1 = fin1.read()
    while (data1 != -1) {
        print(data1.toChar())
        data1 = fin1.read()
    }

    fin1.close()

    val inputStream: InputStream = File("test.txt").inputStream()
    val inputString = inputStream.bufferedReader().use{it.readLines()}
    println(inputString)

    val content = "코틀린 세상~"
    val fileName = "studyKotlin.txt"
    val fw = FileWriter(fileName)
    val writer = BufferedWriter(fw)
    writer.append(content)
    writer.flush() // 메모리에 있는 것 쓰기
    writer.close()

    val lines = File(fileName).readLines()
    println(lines)

}