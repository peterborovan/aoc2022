import java.io.File
import java.lang.NumberFormatException

fun main() {
    val inp = File("in/day01.txt")
        .readText()
        .split("\r\n\r\n")
        .map { it.split("\r\n").filter{it.isNotEmpty()}.map{it.toInt()}}
        .map{ it.sum() }
    println("partA: ${inp.maxOrNull()}")
    println("partB: ${inp.sortedDescending().take(3).sum()}")
}