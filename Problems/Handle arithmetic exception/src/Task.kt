import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n1 = scanner.nextInt()
    val n2 = scanner.nextInt()
    println(if (n2 == 0) "Division by zero, please fix the second argument!" else n1 / n2)
}