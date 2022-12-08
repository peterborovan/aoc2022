import java.io.File

private fun view(i:Int, j:Int, inp:List<String>) :Long{
    var res1 = 0L
    for (j1 in j-1 downTo  0) { //left
        res1++
        if (inp[i][j1] >= inp[i][j])  break
    }
    var res2 = 0L
    for (j1 in j+1 until inp[i].length) { // right
        res2++
        if (inp[i][j1] >= inp[i][j]) break
    }
    var res3 = 0L
    for (i1 in i-1 downTo  0) {  //up
        res3++
        if (inp[i1][j] >= inp[i][j]) break
    }
    var res4 =  0L
    for (i1 in i+1 until inp.size) { // down
        res4++
        if (inp[i1][j] >= inp[i][j]) break
    }
    //println("${inp[i][j]} left=$res1 right=$res2 up=$res3 down=$res4 ${res1 * res2 * res3 * res4}")
    return res1 * res2 * res3 * res4
}


private fun visible(i:Int, j:Int, inp:List<String>) :Boolean{
    var res1 = true
    for (j1 in 0 until j) {
        res1 = res1 && (inp[i][j1] < inp[i][j])
    }
    var res2 = true
    for (j1 in j+1 until inp[i].length) {
        res2 = res2 && (inp[i][j1] < inp[i][j])
    }
    var res3 = true
    for (i1 in 0 until i) {
        res3 = res3 && (inp[i1][j] < inp[i][j])
    }
    var res4 = true
    for (i1 in i+1 until inp.size) {
        res4 = res4 && (inp[i1][j] < inp[i][j])
    }
    //println("${inp[i][j]} $res1 $res2 $res3 $res4")
    return res1 || res2 || res3 || res4
}
fun main() {
    val inp = File(
        "in/day08.txt"
        ).readLines()
    val partA = (0 until inp.size).sumOf { i->
        (0 until inp[i].length).count { j-> visible(i,j,inp) }
    }
    val partB = (0 until inp.size).maxOf { i->
        (0 until inp[i].length).maxOf { j-> view(i,j,inp) }
    }
    println("partA: ${partA}")
    println("partB: ${partB}")
}
