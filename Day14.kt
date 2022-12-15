import java.io.File

fun main() {
    val inp = File(
        "in/day14.txt"
    ).readLines()
    var max_y = Int.MIN_VALUE
    repeat (2) { p ->
        val a = mutableSetOf<Pair<Int,Int>>()
        for (l in inp) {
            val parts = l.split(" -> ")
            for (i in 1 until parts.size) {
                val from = parts[i - 1].split(",").map { it.toInt() }
                val to = parts[i].split(",").map { it.toInt() }
                for(x in Math.min(from[0], to[0]) as Int .. Math.max(from[0], to[0]))
                    for(y in Math.min(from[1], to[1]) as Int .. Math.max(from[1], to[1])) {
                        a.add(Pair(x,y))
                        max_y = Math.max(max_y, y)
                    }
            }
        }
        if (p == 1) {
            for (x in 0 until a.size) a.add(Pair(x,max_y + 2))
        }
        lab@
        for (n in 0..100000) {
            var sx = 500
            var sy = 0
                while (true) {
                    if (p == 1 && Pair(sx,sy) in a) {  // neda sa viac sypat
                        println("PartB: $n")
                        break@lab
                    }
                    if (Pair(sx,sy + 1) !in a) {
                        sy++
                    } else if (Pair(sx - 1,sy + 1) !in a) {
                        sy++
                        sx--
                    } else if (Pair(sx + 1,sy + 1) !in a) {
                        sy++
                        sx++
                    } else
                        break
                    if (p == 0 && sy > 1000) {
                        println("PartA: $n")
                        break@lab
                    }
                }
                a.add(Pair(sx,sy))
        }
    }
}