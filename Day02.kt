import java.io.File

val tr = mapOf("A" to "R", "B" to "P", "C" to "S", "X" to "R", "Y" to "P", "Z" to "S")
val game1 = mapOf("RP" to 6, "SR" to 6, "PS" to 6,  // vyhra
                "SS" to 3, "PP" to 3, "RR" to 3, // remiza
                "PR" to 0, "RS" to 0, "SP" to 0)
// X lose, Y draw, Z win
val y = mapOf("X" to 0, "Y" to 3, "Z" to 6)
val game2 = mapOf(
    "RZ" to 2, "SZ" to 1, "PZ" to 3,  // P R S
    "RY" to 1, "SY" to 3, "PY" to 2, // R S P
    "RX" to 3, "SX" to 2, "PX" to 1) //  S P R
val x = mapOf("R" to 1, "P" to 2, "S" to 3)
fun main() {
    val inp = File(
        "in/day02.txt"
      //  "in/day02_.txt"
    ).readLines()
    var scoreA = 0L
    var scoreB = 0L
    for(l in inp) {
        val parts = l.split(" ")
        val g = tr[parts[0]] + tr[parts[1]]
        scoreA += game1[g]!!+ x[tr[parts[1]]]!!
        scoreB += y[parts[1]]!! + game2[tr[parts[0]] + parts[1]]!!
        // println("$parts $scoreA")
        // println("$parts $scoreB")
    }
    println("partA: ${scoreA}")
    println("partB: ${scoreB}")
}