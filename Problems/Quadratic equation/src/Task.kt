import java.util.Scanner
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()
    val root1 = -(b + sqrt(b.pow(2) - 4 * a * c)) / (2 * a)
    val root2 = -(b - sqrt(b.pow(2) - 4 * a * c)) / (2 * a)
    println(if (root1 > root2) "$root2 $root1" else "$root1 $root2")
}