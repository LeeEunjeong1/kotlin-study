package programmers.level0

fun solution(n: Int): Int {
    var answer: Int = 0
    when(n%2){
        0 -> {
            for(i in 1..n){
                if(i%2==0) answer += i*i
            }
        }
        else -> {
            for(i in 1..n){
                if(i%2!=0) answer += i
            }
        }
    }

    return answer
}