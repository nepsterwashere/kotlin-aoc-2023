import java.io.File

val numberStrings = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {
    val lines = File("src/main/resources/input-day-01.txt").readLines()
    val result1 = lines.sumOf { s -> s.toCalibrationValue() }
    val result2 = lines.sumOf { s -> s.parseDigits().toCalibrationValue() }

    println(result1)
    println(result2)
}

fun String.toCalibrationValue(): Int {
    val firstNumber = this.first { it.isDigit() }
    val lastNumber = this.last { it.isDigit() }
    return "$firstNumber$lastNumber".toInt()
}

fun String.parseDigits(): String {
    return this.mapIndexedNotNull { index, c ->
        if (c.isDigit()) c
        else {
            this.toPossibleWords(index).firstNotNullOfOrNull { candidate ->
                numberStrings[candidate]
            }
        }
    }.joinToString()
}

fun String.toPossibleWords(startingAt: Int): List<String> {
    return (3..5).map { len ->
        substring(startingAt, (startingAt + len).coerceAtMost(length))
    }
}