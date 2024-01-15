package programmers.level1

class `수박수박수박수박수박수?` {
    fun solution(n: Int): String {
        var answer = ""
        for(i in 1..n){
            if(i%2==0) answer+="박"
            else answer += "수"
        }
        return answer
    }
}