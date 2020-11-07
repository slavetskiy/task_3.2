import kotlin.math.*

fun main() {
    println("Для перевода выберите тип карты/счёта. По умолчанию перевод производится с VK Pay\n" +
            "1 - MasterCard\n" +
            "2 - Maestro\n" +
            "3 - Visa\n" +
            "4 - Мир\n" +
            "Любой другой символ - VK Pay")
    val userCardType = when (readLine()) {
        "1" -> "MasterCard"
        "2" -> "Maestro"
        "3" -> "Visa"
        "4" -> "Мир"
        else -> "VK Pay"
    }
    println("Введите сумму сумму перевода")
    val userAmount = (readLine()!!.toInt())

    println("Введите сумму, которую вы уже перевели за месяц с этой карты/счёта")
    val userMonthSum = (readLine()!!.toInt())


    if (isLimitOk(userCardType, userMonthSum, userAmount)) {
        println("Комиссия составит: " + commission(userCardType, userMonthSum, userAmount))
    }
        else {
        println("Перевод невозможен")
    }
}



fun commission(cardType: String = "VK Pay", monthSum: Int = 0, amount: Int): Int {
    return when (cardType) {
        "MasterCard", "Maestro" -> {
            if (monthSum < 7500000) 0
            else round(amount * 0.006).toInt() + 2000
        }
        "Visa", "Мир" -> {
            if (round(amount * 0.0075) < 3500) 3500
            else round(amount * 0.0075).toInt()
        }
        "VK Pay" -> 0
        else -> 0
    }
}

fun isLimitOk(cardType: String, monthSum: Int, amount: Int): Boolean {
    return when (cardType) {
        "VK Pay" -> {
            if ((monthSum < 4000000) && (amount <= 1500000)) true
            else {
                println("Превышен лимит")
                false
            }
        }

        else -> {
            if ((monthSum < 60000000) && (amount <= 15000000)) true
            else {
                println("Превышен лимит")
                false
            }
        }
    }
}