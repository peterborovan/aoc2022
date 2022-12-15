import java.io.File

private fun dist(s : Pair<Int,Int>, b:Pair<Int,Int>) : Int {
    return Math.abs(s.first - b.first) + Math.abs(s.second - b.second)
}
fun main() {
    val inp = File(
        "in/day15.txt"
    ).readLines()
    val no = mutableListOf<Pair<Int, Int>>()
    //val y = 10
    val y = 2000000
    val bs = mutableListOf<Pair<Int, Int>>()
    val ss = mutableListOf<Pair<Int, Int>>()
    for (l in inp) {
        // :) val parts = l.filter{it.isDigit() || it == ',' || it == ':'}.replace(":",",").split(",").map{it.toInt()}
        val parts = l.filter { it.isDigit() || it == ',' || it == ':' || it == '-' }.replace(":", ",").split(",")
            .map { it.toInt() }
        val s = Pair(parts[0], parts[1])
        ss.add(s)
        val b = Pair(parts[2], parts[3])
        bs.add(b)
        val d = dist(s, b)
        println("$s $b $d")
    }

    fun isOK(x: Int, y: Int): Boolean {
        var ok = true
        for (i in 0 until ss.size) {
            val d = dist(ss[i], bs[i])
            if (dist(Pair(x, y), ss[i]) <= d) {
                ok = false
                break
            }
        }
        return ok
    }
    //####B######################
    //123456789012345678901234567
    for (x in -5000000..5000000) {
        if (!isOK(x, y) && Pair(x, y) !in bs)
            no.add(Pair(x, y))
    }
    println("PartA: ${no.size}")  //5256611

    jedenStaci@
    for (deltaD in 1..100) {
        for (i in 0 until ss.size) {
            val d = dist(ss[i], bs[i]) + deltaD
            for (dx in -d..d)
                for (k in listOf(-1, 1)) {
                    val x = ss[i].first + dx
                    val y = ss[i].second + k * (d - dx)
                    if (x in 0..4000000 && y in 0..4000000 && isOK(x, y)) {
                        println("PartB: $x $y $deltaD ${x * 4000000L + y}")
                        break@jedenStaci
                    }
                }
        }
    }
}

