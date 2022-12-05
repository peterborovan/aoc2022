import java.io.File

fun main() {
    val inp = File(
        //"in/day05.txt"
          "in/day05a.txt"
        ).readLines()
    val a = arrayOf(
        arrayOf('N','0','Q','0','0','N','0','0','0'),
        arrayOf('R','0','F','Q','0','G','M','0','0'),
        arrayOf('J','0','Z','T','0','R','H','J','0'),
        arrayOf('T','H','G','R','0','B','N','T','0'),
        arrayOf('Z','J','J','G','F','Z','S','M','0'),
        arrayOf('B','N','N','N','Q','W','L','Q','S'),
        arrayOf('D','S','R','V','T','C','C','N','G'),
        arrayOf('F','R','C','F','L','Q','F','D','P')
    )
    val n= 9
    var stacks = mutableListOf<MutableList<Char>>()
    for (i in 0 until n) {
        val st = mutableListOf<Char>()
        for(j in 7 downTo 0) {
            if (a[j][i] != '0')
                st.add(0,a[j][i])
        }
        stacks.add(st)
    }
    for (l in inp) {
        val parts = l.replace("move " , "").replace(" from ",",")
            .replace(" to ",",").split(",").map{it.toInt()}
        var from = parts[1]-1
        var to = parts[2]-1
        for (k in 1..parts[0]) {
            val what = stacks[from][0]
            stacks[from].removeAt(0)
            stacks[to].add(0,what)
        }
    }
    print("partA: ")
    for (i in 0 until 9)
        print(stacks[i][0])
    println()

    stacks = mutableListOf<MutableList<Char>>()
    for (i in 0 until n) {
        val st = mutableListOf<Char>()
        for(j in 7 downTo 0) {
            if (a[j][i] != '0')
                st.add(0,a[j][i])
        }
        stacks.add(st)
    }

    for (l in inp) {
        val parts = l.replace("move " , "").replace(" from ",",")
            .replace(" to ",",").split(",").map{it.toInt()}
        var from = parts[1]-1
        var to = parts[2]-1
        for (k in 1..parts[0])
            stacks[to].add(0,stacks[from][parts[0]-k])
        for (k in 1..parts[0])
            stacks[from].removeAt(0)
    }
    print("partB: ")
    for (i in 0 until 9)
        print(stacks[i][0])
    println()


}