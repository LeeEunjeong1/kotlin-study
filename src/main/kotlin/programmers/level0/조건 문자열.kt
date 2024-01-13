package programmers.level0

fun solution(ineq: String, eq: String, n: Int, m: Int): Int {
    var answer: Int = 0
    when(ineq+eq){
        ">=" -> if(n>=m) answer = 1
        "<=" -> if(n<=m) answer = 1
        ">!" -> if(n>m) answer = 1
        "<!" -> if(n<m) answer = 1
    }
    return answer
}