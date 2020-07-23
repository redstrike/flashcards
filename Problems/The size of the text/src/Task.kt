import java.io.File

fun main() {
    val projectRoot = System.getProperty("user.dir")
    val fileName = "$projectRoot\\Problems\\The size of the text\\text.txt"
    val wordList = File(fileName).readText().split(' ')
    println(wordList.size)
}