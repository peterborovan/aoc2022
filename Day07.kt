import java.io.File

fun main() {
    val inp = File(
        "in/day07.txt"
          //"in/day07_.txt"
        ).readLines()
    var curdir = listOf<String>().toMutableList()
    val subdirs = mapOf<String, Long>().toMutableMap()
    for (l in inp) {
        val parts = l.split(" ")
        if (l[0] == '$') {
            if (parts[1] == "cd") {
                if (parts[2] == "/")
                    curdir = listOf<String>().toMutableList()
                else if (parts[2] == "..") {
                    curdir.removeAt(curdir.size - 1)
                } else {
                    curdir.add(parts[2])
                }
            }
        } else {
            if (parts[0] != "dir") {
                for (i in 0 .. curdir.size) {
                    val cd = curdir.subList(0,i).joinToString(separator = "/")
                    //println("${cd},${parts[0].toLong()}, ${parts[1]}")
                    subdirs[cd] = (subdirs[cd] ?: 0L) +parts[0].toLong()
                }
            }
        }
    }
    println("partA: ${subdirs
        .filter { (k,v) -> v <=  100000}.map { (k,v) -> v}.sum()}")
    println("partB: ${subdirs
        .filter { (k,v) -> v >=  subdirs[""]!! - 40000000}.map { (k,v) -> v}.minOrNull()}")
}
