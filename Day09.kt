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
fun main() {
    val inp = File(
        "in/day09.txt"
        ).readLines()
    val dirs = listOf(
        Pair(1, 0), Pair(-1, 0), Pair(0, -1), Pair(0, 1),
        Pair(1, 1), Pair(-1, 1), Pair(-1, -1), Pair(1, -1),
    )

    var hx = 0
    var hy = 0
    var tx = 0
    var ty = 0
    val s = mutableSetOf<Pair<Int,Int>>()
    s.add(Pair(tx,ty))
    for (l in inp) {
        val parts = l.split(" ")
        val d = parts[0]
        val n = parts[1].toInt()
//        println("$d $n")
        repeat(n) {
            if (d == "U") {
                 hx++
            } else if (d == "D") {
                hx--
            } else if (d == "L") {
                hy--
           } else if (d == "R") {
               hy++
           }
            if (!((hx == tx && hy == ty) || touch(tx,ty,hx,hy))) {
                for ((dtx,dty) in dirs) {
                    if (touch(tx, ty , hx-dtx, hy-dty)) {
                        tx = hx-dtx
                        ty = hy-dty
                        break
                    }
                }
            }
//            println("[$hx $hy] [$tx $ty]")
            s.add(Pair(tx,ty))
        }
    }
    val tails =
    arrayOf(
        Pair(0,0),  //H
        Pair(0,0), //1
        Pair(0,0), //2
        Pair(0,0), //3
        Pair(0,0), //4
        Pair(0,0), //5
        Pair(0,0), //6
        Pair(0,0), //7
        Pair(0,0),//8
        Pair(0,0) //9
    )

    val s9 = mutableSetOf<Pair<Int,Int>>()
    s9.add(Pair(0,0))
    for (l in inp) {
        val parts = l.split(" ")
        val d = parts[0]
        val n = parts[1].toInt()
//        println("$d $n")
        repeat(n) {
            if (d == "U") {
                tails[0] = Pair(tails[0].first+1, tails[0].second)
            } else if (d == "D") {
                tails[0] = Pair(tails[0].first-1, tails[0].second)
            } else if (d == "L") {
                tails[0] = Pair(tails[0].first, tails[0].second-1)
            } else if (d == "R") {
                tails[0] = Pair(tails[0].first, tails[0].second+1)
            }
            for(i in 1..9) {
                hx = tails[i-1].first
                hy = tails[i-1].second
                tx = tails[i].first
                ty = tails[i].second

                if (!((hx == tx && hy == ty) || touch(tx, ty, hx, hy))) {
                    for ((dtx, dty) in dirs) {
                        if (touch(tx, ty, hx - dtx, hy - dty)) {
                            tx = hx - dtx
                            ty = hy - dty
                            break
                        }
                    }
                }
                tails[i] = Pair(tx,ty)
            }
            s9.add(Pair(tails[9].first, tails[9].second))
        }
    }
    println("partA: ${s.size}")
    println("partB: ${s9.size}")
}
