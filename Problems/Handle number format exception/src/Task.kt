fun parseCardNumber(cardNumber: String): Long {
    val numberParts = cardNumber.split(' ')
    if (numberParts.size == 4) {
        return numberParts.joinToString("").toLong()
    }
    throw NumberFormatException("The card number is incorrect.")
}