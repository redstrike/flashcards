fun identity(n: Int): Int = n
fun half(n: Int): Int = n / 2
fun zero(n: Int): Int = 0

fun generate(funcName: String): (Int) -> Int = when (funcName) {
    "identity" -> ::identity
    "half" -> ::half
    else -> ::zero
}