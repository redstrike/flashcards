import java.io.File

fun main() {
    val projectRoot = System.getProperty("user.dir")
    val fileName = "$projectRoot\\Problems\\Calculate the length\\new.txt"
    val linesLength = File(fileName).length()
    val lines = File(fileName).readLines().size
    print("$linesLength $lines")
}