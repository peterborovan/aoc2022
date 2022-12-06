import java.io.File

fun main() {
    val inp = File(
        "in/day06.txt"
        //  "in/day06_.txt"
        ).readLines()
    println("partA: ${alldiffs(inp[0], 4)}")
    println("partB: ${alldiffs(inp[0], 14)}")
}

private fun alldiffs(l: String, n: Int):Int {
    for (i in 0..l.length - n) {
        if (l.substring(i,i+n).toSet().size == n) {
            return i + n
        }
    }
    return -9999
}