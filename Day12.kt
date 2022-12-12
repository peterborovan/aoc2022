import java.io.File


fun main() {
    val inp = File(
        "in/day12.txt"
    ).readLines()
    var start = Pair(-1,-1)
    var end = Pair(-1,-1)

    fun high(i : Int, j : Int):Char =
        if (start == Pair(i,j)) 'a'
        else if (end == Pair(i,j)) 'z'
        else inp[i][j]

    for((i,l) in inp.withIndex())
        for (j in 0 until l.length)
            if (l[j] == 'S') start = Pair(i,j)
            else if (l[j] == 'E') end = Pair(i,j)
    repeat (2) { part ->
        val a = Array(inp.size) { Array(inp[0].length) { 0 } }
        val queue = mutableListOf<Pair<Int, Int>>()
        if (part == 0) queue.add(start)
        else queue.add(end)
        while (queue.size > 0) {
            val top = queue[0]
            if ((part == 0 && queue[0] == end) || (part == 1 && high(top.first, top.second) == 'a')) {
                println("Part ${part+1}: ${a[top.first][top.second]}")
                break
            }
            val (tx, ty) = queue[0]
            queue.removeAt(0)
            for((dx,dy) in listOf(Pair(-1,0),Pair(1,0),Pair(0,-1),Pair(0,1))) {
                val tdx = tx+dx
                val tdy = ty+dy
                if (0<= tdx && tdx < inp.size && 0<= tdy && tdy < inp[0].length) {
                    if ((part == 0 && high(tx,ty) + 1 >= high(tdx, tdy) && a[tdx][tdy] == 0) ||
                        (part == 1 && high(tx,ty) - 1 <= high(tdx, tdy) && a[tdx][tdy] == 0)) {
                        queue.add(Pair(tdx, tdy))
                        a[tdx][tdy] = a[tx][ty]+1
                    }
                }
            }
        }
    }
}
