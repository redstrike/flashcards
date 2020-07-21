fun main() {
    val index = readLine()!!.toInt()
    val word = readLine()!!
    if (!word.indices.contains(index)) {
        return println("There isn't such an element in the given string, please fix the index!")
    }
    println(word[index])
}