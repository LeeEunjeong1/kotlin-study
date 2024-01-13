package programmers.level0

fun solution(array: IntArray, n: Int): Int {
    var answer: Int = 0
    array.forEach{
        if(it == n) answer ++
    }
    return answer
}