package ru.netology.nmedia.dto

import java.math.RoundingMode
import java.text.DecimalFormat

data class Post(
    val id: Long = 0L,
    val content: String = "",
    val published: String = "",
    val author: String = "",
    val likedByMe: Boolean = false,
    val likes: Long = 16123L,
    val shares: Long = 1598L,
    val views: Long = 1135000L
)

// функция рассчета количества и изменения порядка
fun counter(item: Long): String {
    return when (item) {
        in 1000..1099 -> {
            val num = roundOffDecimal(item / 1000.0)
            (num + "K")
        }
        in 1100..9999 -> {
            val num = roundOffDecimal(item / 1000.0)
            (num + "K")
        }
        in 10_000..999_999 -> {
            ((item / 1000).toString() + "K")
        }
        in 1_000_000..1_000_000_000 -> {
            val num = roundOffDecimal(item / 1_000_000.0)
            (num + "M")
        }
        else -> item.toString()
    }
}

private fun roundOffDecimal(number: Double): String {
    val decimalFormat = DecimalFormat("#.#")
    decimalFormat.roundingMode = RoundingMode.FLOOR
    return decimalFormat.format(number).toString()
}
