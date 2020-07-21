package flashcards

fun main() {
    println("Input the number of cards:")
    val numCards = readLine()!!.toInt()
    // Create the defined amount of cards
    val terms = Array(numCards) { "" }
    val definitions = Array(numCards) { "" }
    repeat(numCards) { it: Int ->
        val cardNo = it + 1
        println("The card #$cardNo:")
        terms[it] = readLine()!!.trim()
        println("The definition of the card #$cardNo:")
        definitions[it] = readLine()!!.trim()
    }
    // Test the user's knowledge
    repeat(numCards) { it: Int ->
        println("Print the definition of \"${terms[it]}\"")
        val answer = readLine()!!.trim()
        println(when (answer) {
            definitions[it] -> "Correct answer."
            else -> "Wrong answer. The correct one is \"${definitions[it]}\""
        })
    }
}
