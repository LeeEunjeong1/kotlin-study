package programmers.level1

fun solution1(num: Int): Int {
    return collatz(num.toLong(),0)
}
fun collatz(num:Long,answer:Int):Int{
    if(answer+1 == 500) return -1
    if(answer == 0 && num == 1L) return 0
    if(num == 1L) return answer
    return if(num%2 == 0L){
        collatz(num/2, answer+1 )
    }else{
        collatz(num*3+1, answer+1 )
    }
}