import java.io.File

fun main() {
    val projectRoot = System.getProperty("user.dir")
    val fileName = "$projectRoot\\Problems\\The longest word\\words_sequence.txt"
    println(File(fileName).readLines().map { it.length }.max())
}