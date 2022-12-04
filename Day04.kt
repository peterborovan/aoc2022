import java.io.File

fun main() {
    val inp = File(
        "in/day04.txt"
        //  "in/day04_.txt"
        ).readLines()
        .map{ it.split(",")}
        .map {
            val p1 = it[0].split("-").map { it.toInt() }
            val p2 = it[1].split("-").map { it.toInt() }
            Pair((p1[0]..p1[1]).toSet(), (p2[0]..p2[1]).toSet())
        }
    println("partA: ${inp.count { (r1,r2) -> r1.containsAll(r2) || r2.containsAll(r1)}}")
    println("partB: ${inp.count { (r1,r2) -> r1.intersect(r2).isNotEmpty()}}")
}