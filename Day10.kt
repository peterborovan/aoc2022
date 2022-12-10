import java.io.File

fun main() {
    val inp = File(
        "in/day10.txt"
        ).readLines()
    var x = 1
    var cycle = 1
    val cycles = listOf(20, 60, 100, 140, 180, 220)
    var sum = 0L
    var str = ""

    fun update(arg : Int) {
        if ((cycle - 1) % 40 in listOf(x - 1, x, x + 1)) str += "#" else str += " "
        cycle++
        x += arg
        if (cycle in cycles) sum += cycle * x
    }

    for(l in inp) {
        val parts = l.split(" ")
        update(0)
        if (parts[0] == "addx") update(parts[1].toInt())
    }
    println("partA: ${sum}")
    println("partB:") // PGHFGLUG
    for(i in 0..5)
        println(str.substring(i*40,(i+1)*40))
}
