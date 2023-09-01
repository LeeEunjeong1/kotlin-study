package kotlinAtoZ

import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

fun main() {
    val list1 = listOf(1, 2, 3) // 불변 리스트 객체 생성
    val list2 = mutableListOf(1, 2, 3) // 가변 리스트 객체 만들기

    // 가변리스트는 원소를 추가하거나 삭제 가능
    list2.add(10)  // 원소 추가
    list2.removeAt(1) // 원소 삭제
    list2[0] = 0 // 원소 대체

    val linkedList = LinkedList<String>()
    linkedList.addAll(listOf("지구", "금성", "화성")) // 링크드리스트에 원소 추가

    println(linkedList) // [지구, 금성, 화성]
    println("First planet = ${linkedList.first}") // First planet = 지구

    val joinedPlanets = linkedList.plus(linkedList)
    println(joinedPlanets) // [지구, 금성, 화성, 지구, 금성, 화성]

    val emptyList: List<String> = emptyList<String>() // 아무것도 없는 리스트 생성
    val nonNullsList = listOfNotNull(2, 45, null, 5, null) // null 제거 리스트 생성
    println(emptyList) // []
    println(nonNullsList) // [2, 45, 5]

    val intHSet: HashSet<Int> = hashSetOf(1, 2, 6, 3) // 해시 집합 생성
    intHSet.add(5) // 원소 추가
    intHSet.remove(1)

    val intMSet: MutableSet<Int> = mutableSetOf(3, 5, 6, 2, 0) // 가변집합 생성
    intMSet.add(8) // 원소 추가
    intMSet.remove(3) // 원소 삭제

    val intLSet: LinkedHashSet<Int> = linkedSetOf(5, 2, 7, 2, 5)
    intLSet.add(4) // 원소 추가
    intLSet.remove(2) // 원소 삭제
    intLSet.clear() // 원소 전체 삭제

    val treeSet = TreeSet<String>()
    treeSet.addAll(listOf("화성", "금성"))

    val treeSet2 = TreeSet<String>()
    treeSet2.addAll(listOf("화성", "지구"))

    treeSet.retainAll(treeSet2) // 교집합된 원소만 유지
    println(treeSet) // [화성]

    val tomcat = "Tom" to "cat" // 두 개의 원소를 가진 튜플 만들기
    val m = hashMapOf( // 튜플로 맵 생성
        Pair("Tom", "cat"),
        Pair("Jerry", "Mouse")
    )

    val map1 = linkedMapOf("1" to "one") // 튜플 연산자로 맵 생성

    val callingMap: Map<Int, String> = mapOf(82 to "한국", 1 to "USA")
    for ((key, value) in callingMap) {
        println("$key 코드는 어느나라 $value")
    }

    val currencyMap: MutableMap<String, String> = mutableMapOf("원" to "한국")
    println("국가 = ${currencyMap.values}") // 국가 = [한국]
    currencyMap["엔"] = "일본"
    println(currencyMap.remove("원")) // 한국
    println(currencyMap.values) // [일본]

    val list = listOf("a", "b", "c") //리스트 객체 생성
    val set1 = setOf(1, 2, 3)
    val map = mapOf("a" to 100, "b" to 300)

    println(list.size) // 원소 개수
    println(list.isEmpty()) // 원소가 있는지 확인
    println(list.contains("B")) // 포함 여부
    println(list.containsAll(list)) // 전체가 다 포함되었는지

//    3
//    false
//    false
//    true

    list.forEach { print("$it,") } // 순환 처리
    println()
    list.forEachIndexed { index, value -> print("$index = $value, ") } // 인덱스 값과 같이 순환
    println()
    map.forEach { (key, value) -> print("$key = $value") } // 키와 밸류에 대한 순환처리

    println()
//    a,b,c,
//    0 = a, 1 = b, 2 = c,
//    a = 100b = 300

    println("list any : " + list.any { it.length > 3 }) // list any : false
    println("list all : " + list.all { it > "a" }) // list all : false
    println("list any : " + list.none { it > "a" }) // list any : false
    println("list any : " + map.any() { it.key.length > 3 }) // list any : false

    val mList = mutableListOf("나", "가", "다")
    println("mList : $mList")
    println("정렬해서 새객체 : ${mList.sorted()}")
    println("반대로 정렬 새객체 : ${mList.reversed()}")
    println("mList : $mList")
    val mCopyList = mList.toMutableList() // 객체를 다시 처리하면서 복사
    println("mCopyList : $mCopyList")
    mCopyList.sort()
    println("정렬 후 내부 변경 : $mCopyList")
    mCopyList.reverse()
    println("반대로 정렬 후 내부 변경 : $mCopyList")

    val cities = listOf("Seoul", "Tokyo")
    cities.map { str: String -> str.uppercase() }.forEach { print(it) } // SEOULTOKYO
    println()
    cities.filter { it.contains("S") }.forEach { println(it) } // Seoul
    val amounts = listOf(1, 2, 3, 4, 5)
    println(amounts.reduce { total, x -> total + x }) // 15

    val cities1 = listOf("제주", "서울", "상하이")
    val keySelector = { city: String ->
        if (city.length == 3) "A" else "B"
    }
    cities1.groupBy(keySelector) // 함수 전달해서 그룹화 처리
        .forEach { (key, value) -> // 맵으로 구성된 결과를 출력
            println("$key : $value")
        }

    cities1.groupBy { if (it.length == 3) "A" else "B" } // 람다를 바로 전달해서 처리
        .forEach { (key, value) ->
            println("$key : $value")
        }

    val seq = sequenceOf(1, 2, 3, 4, 5) // 시퀀스 생성
    println("seq 원소 개수 : ${seq.count()}") // 실행 연산을 해야 처리된 결과를 보여준다
    seq.forEach { print("$it , ") } // 실행해서 원소 출력
    println()

    val numList = listOf(1, 2, 3, 4, 5) // 리스트 생성
    val seq2 = numList.asSequence() // 시퀀스로 변환
    println("두 시퀀스 객체 비교 : ${seq == seq2}") // 두 객체 비
    println("seq2 원소개수 : ${seq2.count()}")

    val words = "가나 다라마바 사아자차".split(" ")
    iterableCheck(words)
    val wordsSeq = words.asSequence()
    seqCheck(wordsSeq)

}


fun iterableCheck(words: List<String>) {
    val lengthsList = words
        .filter {
            println("iterable filter : $it")
            it.length > 3
        }
        .map {
            println("iterable 길이 확인 : ${it.length}")
            it.length
        }
    println("----- iterable 처리된 결과 출력 -----")
    println(lengthsList)
}

fun seqCheck(words: Sequence<String>) {
    val lengthsSequence = words
        .filter {
            println("seq filter : $it")
            it.length > 3
        }
        .map {
            println("seq 길이 확인 : ${it.length}")
            it.length
        }
    println("----- seq 처리된 결과 출력 -----")
    println(lengthsSequence.toList())
}