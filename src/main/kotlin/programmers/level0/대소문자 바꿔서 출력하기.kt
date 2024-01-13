package programmers.level0

fun main(){
    val s1 = readln()
    var result = ""
    s1.forEach{
        when(it.isUpperCase()){
            true -> result+=it.lowercase()
            false -> result+=it.uppercase()
        }
    }
    print(result)
}