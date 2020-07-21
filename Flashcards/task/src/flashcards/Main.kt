package flashcards

fun main() {
    val term = readLine()!!.trim()
    val definition = readLine()!!.trim()
    val answer = readLine()!!.trim()
    val result = if (answer == definition) "right!" else "wrong..."
    println("Your answer is $result")
}
