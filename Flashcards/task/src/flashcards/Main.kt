package flashcards

import java.io.File
import kotlin.random.Random

fun main() {
    val flashcards = mutableMapOf<String, String>()
    loop@ while (true) {
        println("\nInput the action (add, remove, import, export, ask, exit):")
        when (readInput()) {
            "add" -> {
                println("The card:")
                val term = readInput()
                if (flashcards.containsKey(term)) {
                    println("The card \"$term\" already exists.")
                    continue@loop
                }
                println("The definition of the card:")
                val definition = readInput()
                if (flashcards.containsValue(definition)) {
                    println("The definition \"$definition\" already exists.")
                    continue@loop
                }
                flashcards[term] = definition
                println("The pair (\"$term\":\"$definition\") has been added.")
            }
            "remove" -> {
                println("The card:")
                val term = readInput()
                if (!flashcards.containsKey(term)) {
                    println("Can't remove \"$term\": there is no such card.")
                    continue@loop
                }
                flashcards.remove(term)
                println("The card has been removed.")
            }
            "export" -> {
                println("File name:")
                val file = File(readInput())
                var content = ""
                flashcards.forEach {
                    val (term, definition) = it
                    content += "$term=$definition\n"
                }
                file.writeText(content)
                println("${flashcards.size} cards have been saved.")
            }
            "import" -> {
                println("File name:")
                val file = File(readInput())
                if (!file.exists()) {
                    println("File not found.")
                    continue@loop
                }
                val cardList = file.readLines()
                cardList.forEach {
                    val (term, definition) = it.split('=')
                    flashcards[term] = definition
                }
                println("${cardList.size} cards have been loaded.")
            }
            "ask" -> {
                println("How many times to ask?")
                val times = readLine()!!.toInt()
                repeat(times) {
                    val index = Random.nextInt(0, until = flashcards.size)
                    val (term, definition) = flashcards.entries.elementAt(index)
                    println("Print the definition of \"$term\":")
                    val answer = readInput()
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
            "exit" -> return println("Bye bye!")
        }
    }
}

private fun readInput() = readLine()!!.trim()

private fun getWrongMessage(definition: String, anotherTerm: String? = null): String {
    val message = "Wrong answer. The correct one is \"$definition\""
    if (anotherTerm.isNullOrEmpty()) {
        return "$message."
    }
    return "$message, you've just written the definition of \"$anotherTerm\"."
}