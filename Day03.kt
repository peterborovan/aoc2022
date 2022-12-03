import java.io.File

fun Char.value() =
    if ('a' <= this && this <= 'z' ) this-'a'+1
    else this-'A'+27

fun main() {
    val inp = File(
        "in/day03.txt"
      //  "in/day03_.txt"
    ).readLines()
    val partA = inp.map {
        l -> l.substring(0,l.length/2).toSet().intersect(l.substring(l.length/2, l.length).toSet()).first().value()
    }.sum()
    var partB = 0L
    println("partA: ${partA}")
    for(i in 0 until inp.size step 3)
        partB += inp[i].toSet().intersect(inp[i+1].toSet()).intersect(inp[i+2].toSet()).first().value()
    println("partB: ${partB}")
}