import java.io.File

private fun touch(x1:Int, y1:Int, x2:Int, y2:Int) : Boolean {
    for (dx in -1..1)
        for (dy in -1..1)
            if (dx == 0 && dy == 0) continue
            else
                if (x1+dx == x2 && y1+dy==y2)
                    return true;
    return false
}
val dirs = listOf(
    Pair(1, 0), Pair(-1, 0), Pair(0, -1), Pair(0, 1),
    Pair(1, 1), Pair(-1, 1), Pair(-1, -1), Pair(1, -1),
)

fun main() {
    val inp = File(
        "in/day09.txt"
        ).readLines()

    var head = Pair(0,0)
    var tail = Pair(0,0)
    val s = mutableSetOf<Pair<Int,Int>>()
    s.add(tail)

    val tails = Array(10, {i -> Pair(0,0)})
    val s9 = mutableSetOf<Pair<Int,Int>>()
    s9.add(Pair(0,0))

    for (l in inp) {
        val parts = l.split(" ")
        val d = parts[0]
        val n = parts[1].toInt()
        repeat(n) {
            // part A
            head = updateHead(d, head)
            tail = updateTail(head, tail)
            s.add(tail)
            // part B
            tails[0] = updateHead(d, tails[0])
            for(i in 1..9) {
                tails[i] = updateTail(tails[i-1], tails[i])
            }
            s9.add(tails[9])
        }
    }
    println("partA: ${s.size}")
    println("partB: ${s9.size}")
}

private fun updateTail(head : Pair<Int, Int>, tail : Pair<Int, Int>): Pair<Int, Int> {
    val (hx, hy) = head
    val (tx, ty) = tail
    if (!((hx == tx && hy == ty) || touch(tx, ty, hx, hy))) {
        for ((dtx, dty) in dirs) {
            if (touch(tx, ty, hx - dtx, hy - dty)) {
                return Pair(hx - dtx, hy - dty)
            }
        }
    }
    return Pair(tx,ty)
}

private fun updateHead(d: String, head : Pair<Int, Int>): Pair<Int, Int> {
    val (hx, hy) = head
    return when(d) {
        "U" -> Pair(hx+1, hy)
        "D" -> Pair(hx-1, hy)
        "L" -> Pair(hx, hy-1)
        else -> Pair(hx, hy+1)
    }
}
