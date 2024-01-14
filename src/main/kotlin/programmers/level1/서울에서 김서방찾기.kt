package programmers.level1

fun solution(seoul: Array<String>): String {
    var answer = ""
    seoul.forEachIndexed{index,it ->
        if(it=="Kim") answer = "김서방은 ${index}에 있다"
        return@forEachIndexed
    }
    return answer
}