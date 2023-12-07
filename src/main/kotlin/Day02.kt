import java.io.File

val bag = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)
fun main() {
    val lines = File("src/main/resources/input-day-02.txt").readLines()

    val resultPartOne = lines.sumOf { game ->
        val roundOutcomes = game.parseRounds().map { round ->
            round.map { cube ->
                val number = cube.parseNumber()
                val color = cube.parseColor()

                bag[color]
                    ?.let { it < number }
                    ?: false
            }
                .contains(true)
        }

        if (roundOutcomes.contains(true)) 0 else game.parseGameId()
    }

    val resultPartTwo = lines.sumOf { game ->
        val cubeSet = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0,
        )

        game.parseRounds().forEach { round ->
            round.forEach { cube ->
                val number = cube.parseNumber()
                val color = cube.parseColor()

                if (cubeSet[color]!! < number) cubeSet[color] = number
            }
        }

        cubeSet["red"]!! * cubeSet["green"]!! * cubeSet["blue"]!!
    }

    println(resultPartOne)
    println(resultPartTwo)
}

fun String.parseGameId(): Int =
    split(":")[0].replace("Game ", "").toInt()

fun String.parseRounds(): List<List<String>> =
    split(":")[1].split(";").map { round -> round.split(",") }

fun String.parseNumber(): Int =
    split(" ")[1].toInt()

fun String.parseColor(): String =
    split(" ")[2]
