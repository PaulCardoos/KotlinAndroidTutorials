import java.lang.Exception

fun main(args: Array<String>) {
    try {
        print("Enter a number : ")
        val num: Int = readLine()!!.toInt()
        val divide = 100 / num
        println("Value : $divide")

        println("App is done")

    } catch(ex: Exception){
        println(ex.message)
    }
}