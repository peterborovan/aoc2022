import java.io.File

private data class Monkey(var list : MutableList<Long>, val op : String,
                  val test : Long, val iftrue:Int, val iffalse:Int, var inspect :Long)

fun main() {
    val inp = File(
        "in/day11.txt"
    ).readLines()
    repeat (2) { p ->
        var m = mutableMapOf<Int, Monkey>()
        var mid = -1
        var mlist = mutableListOf<Long>()
        var mop: String = ""
        var miftrue = -1
        var miffalse = -1
        var md = -1L
        var prvoc = 1L
        for (l1 in inp) {  // nothing to write home about ...
            val l = l1.trim()
            if (l.isEmpty()) {
                m[mid] = Monkey(mlist, mop, md, miftrue, miffalse, 0)
            } else if (l.startsWith("Monkey ")) {
                mid = l.substringAfter("Monkey ").substringBefore(":").toInt()
            } else if (l.startsWith("Starting items: ")) {
                mlist = l.substringAfter("Starting items: ").split(", ").map { it.toLong() }.toMutableList()
            } else if (l.startsWith("Operation: new = old ")) {
                mop = l.substringAfter("Operation: new = old ")
            } else if (l.startsWith("Test: divisible by ")) {
                md = l.substringAfter("Test: divisible by ").toLong()
                prvoc *= md
            } else if (l.startsWith("If true: throw to monkey ")) {
                miftrue = l.substringAfter("If true: throw to monkey ").toInt()
            } else if (l.startsWith("If false: throw to monkey ")) {
                miffalse = l.substringAfter("If false: throw to monkey ").toInt()
            }
        }
        m[mid] = Monkey(mlist, mop, md, miftrue, miffalse, 0L)
        var n = 20
        if (p == 1) n = 10000
        repeat(n) {
            for (mid in 0 until m.size) {
                //println("playing monkey $mid")
                val monkey = m[mid]!!
                for (x in monkey.list) {
                    m[mid]!!.inspect++
                    var res = -1L
                    if (monkey.op.startsWith("* old")) {
                        res = x * x
                    } else if (monkey.op.startsWith("+")) {
                        val arg = monkey.op.substringAfter("+ ").toInt()
                        res = x + arg
                    } else if (monkey.op.startsWith("*")) {
                        val arg = monkey.op.substringAfter("* ").toInt()
                        res = x * arg
                    } else {
                        println("------------------------------------------ something wrong")
                    }
                    if (p == 0)
                        res = res / 3
                    else
                        res = res % prvoc
                    //println("$x $res")
                    if (res % monkey.test == 0L) {
                        m[monkey.iftrue]!!.list.add(res)
                    } else {
                        m[monkey.iffalse]!!.list.add(res)
                    }
                }
                m[mid]!!.list = mutableListOf()
            }
            //println(m.map { (k, v) -> v.inspect })
        }
        val res = m.map { (k, v) -> v.inspect }.sortedDescending().take(2)
        println("part ${p+1}: ${res[0] * res[1]}")
    }
}
