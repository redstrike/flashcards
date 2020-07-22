package flashcards

fun main() {
    println("Input the number of cards:")
    val numCards = readLine()!!.toInt()
    // Create the defined amount of cards
    val flashcards = mutableMapOf<String, String>()
    repeat(numCards) { it: Int ->
        val cardNo = it + 1
        println("The card #$cardNo:")
        var term = readLine()!!.trim()
        while (flashcards.containsKey(term)) {
            println("The card \"$term\" already exists. Try again:")
            term = readLine()!!.trim()
        }
        println("The definition of the card #$cardNo:")
        var definition = readLine()!!.trim()
        while (flashcards.containsValue(definition)) {
            println("The definition \"$definition\" already exists. Try again:")
            definition = readLine()!!.trim()
        }
        flashcards[term] = definition
    }
    // Test the user's knowledge
    for ((term, definition) in flashcards) {
        println("Print the definition of \"$term\":")
        val answer = readLine()!!.trim()
        println(when {
            answer == definition -> "Correct answer."
            flashcards.containsValue(answer) -> {
                val anotherTerm = flashcards.filter { it.value == answer }.keys.first()
                getWrongMessage(definition, anotherTerm)
            }
            else -> getWrongMessage(definition)
        })
    }
}

private fun getWrongMessage(definition: String, anotherTerm: String? = null): String {
    val message = "Wrong answer. The correct one is \"$definition\""
    if (anotherTerm.isNullOrEmpty()) {
        return "$message."
    }
    return "$message, you've just written the definition of \"$anotherTerm\"."
}