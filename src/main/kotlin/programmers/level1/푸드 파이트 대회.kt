package programmers.level1

class `푸드 파이트 대회` {
    fun solution(food: IntArray): String {
        var answer: String = ""
        food.forEachIndexed{index, item ->
            if(index>0){
                repeat(item/2){
                    answer += "$index"
                }
            }
        }
        answer += "0"
        answer+= answer.split("0")[0].reversed()
        return answer
    }
}