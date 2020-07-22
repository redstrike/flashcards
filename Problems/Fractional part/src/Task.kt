import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextDouble()
    println((n * 10).toInt() % 10)
//    val n = scanner.nextDouble().toString()
//    for (i in 0..n.lastIndex) {
//        if (n[i] == '.') {
//            return println(n[i + 1])
//        }
//    }
}