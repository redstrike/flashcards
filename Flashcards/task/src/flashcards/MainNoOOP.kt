package flashcards

import java.io.File
import kotlin.random.Random

private val cards = mutableMapOf<String, String>()
private val stats = mutableMapOf<String, Int>()
private val logs = mutableListOf<String>()

fun main(args: Array<String>) {
    // Get command line arguments
    var importPath = ""
    var exportPath = ""
    if (args.isNotEmpty()) {
        if (args.size % 2 != 0) return println("Error: Missing one or more arguments!")
        for (i in args.indices step 2) {
            when (args[i]) {
                "-import" -> importPath = args[i + 1]
                "-export" -> exportPath = args[i + 1]
            }
        }
    }
    // Import cards on init
    if (importPath.isNotEmpty()) importCards(importPath)
    // Start flashcards
    while (true) {
        printLine("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        when (readInput().toLowerCase()) {
            "add" -> addCard()
            "remove" -> removeCard()
            "export" -> exportCards(getFilename())
            "import" -> importCards(getFilename())
            "ask" -> ask()
            "log" -> exportLogs()
            "hardest card" -> getHardestCards()
            "reset stats" -> resetStats()
            "exit" -> {
                printLine("Bye bye!")
                // Export cards on exit
                if (exportPath.isNotEmpty()) exportCards(exportPath)
                return
            }
        }
    }
}

private fun printLine(message: String) {
    println(message)
    logs.add(message)
}

private fun readInput(): String {
    val input = readLine()!!.trim()
    logs.add(input)
    return input
}

private fun getTerm(): String {
    printLine("The card:")
    return readInput()
}

private fun addCard() {
    val term = getTerm()
    if (cards.containsKey(term)) {
        printLine("The card \"$term\" already exists.")
        return
    }
    printLine("The definition of the card:")
    val definition = readInput()
    if (cards.containsValue(definition)) {
        printLine("The definition \"$definition\" already exists.")
        return
    }
    cards[term] = definition
    stats[term] = 0 // error count
    printLine("The pair (\"$term\":\"$definition\") has been added.")
}

private fun removeCard() {
    val term = getTerm()
    if (!cards.containsKey(term)) {
        printLine("Can't remove \"$term\": there is no such card.")
        return
    }
    cards.remove(term)
    stats.remove(term)
    printLine("The card has been removed.")
}

private fun getFilename(): String {
    printLine("File name:")
    return readInput()
}

private fun exportCards(pathname: String) {
    val data = StringBuilder()
    cards.forEach {
        val (term, definition) = it
        val errorCount = stats[term]
        data.append("$term=$definition=$errorCount\n")
    }
    File(pathname).writeText(data.toString())
    printLine("${cards.size} cards have been saved.")
}

private fun importCards(pathname: String) {
    val file = File(pathname)
    if (!file.exists()) {
        printLine("File not found.")
        return
    }
    val cardList = file.readLines()
    cardList.forEach {
        val (term, definition, errorCount) = it.split('=')
        cards[term] = definition
        stats[term] = errorCount.toInt()
    }
    printLine("${cardList.size} cards have been loaded.")
}

private fun ask() {
    printLine("How many times to ask?")
    val times = readInput().toInt()
    repeat(times) {
        val index = Random.nextInt(0, until = cards.size)
        val (term, definition) = cards.entries.elementAt(index)
        printLine("Print the definition of \"$term\":")
        val answer = readInput()
        val errorCount = if (stats[term] != null) stats[term]!! else 0
        printLine(when {
            answer == definition -> "Correct answer."
            cards.containsValue(answer) -> {
                stats[term] = errorCount + 1
                val anotherTerm = cards.filter { it.value == answer }.keys.first()
                getWrongMessage(definition, anotherTerm)
            }
            else -> {
                stats[term] = errorCount + 1
                getWrongMessage(definition)
            }
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

private fun exportLogs() {
    val file = File(getFilename())
    printLine("The log has been saved.")
    file.writeText(logs.joinToString("\n"))
}

private fun getHardestCards() {
    if (stats.isEmpty() || stats.filter { it.value > 0 }.isEmpty()) {
        printLine("There are no cards with errors.")
        return
    }
    val largestWrongCount = stats.maxBy { it.value }!!.value
    val hardestCards = stats.filterValues { it == largestWrongCount }
    if (hardestCards.size == 1) {
        val (term) = hardestCards.entries.elementAt(0)
        printLine("The hardest card is \"$term\". You have $largestWrongCount errors answering it.")
        return
    }
    val hardestTerms = hardestCards.keys.joinToString { "\"$it\"" }
    printLine("The hardest cards are $hardestTerms. You have $largestWrongCount errors answering them.")
}

private fun resetStats() {
    stats.clear()
    printLine("Card statistics have been reset.")
}
