import java.util.Scanner
import kotlin.math.max

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n1 = scanner.nextInt()
    val n2 = scanner.nextInt()
    val n3 = scanner.nextInt()
    val n4 = scanner.nextInt()
    println(max(max(n1, n2), max(n3, n4)))
}