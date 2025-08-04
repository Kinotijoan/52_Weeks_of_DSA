import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

/*
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts STRING_ARRAY operations as parameter.
 * 
 * Approach
 * ---------
 * Use a deque to handle the stack operations, and a max heap to efficiently
 * get the maximum element (only takes O(1))
 * 
 * Runtime Complexity
 * ---------
 * I'll split this by operation.
 * - Operation 1 (adding an element)
 *      O(1) for adding to the stack, and O(log n) for adding to the heap
 * - Operation 2 (removing an element)
 *      O(1) for removing from the stack, and O(n) + O(log n) for removing from the heap
 * - Operation 3 (finding the maximum)
 *       O(1) as we are just reading from the heap
 */

fun getMax(operations: Array<String>): List<Int> {
    val stack = ArrayDeque<Int>()
    val maxHeap = PriorityQueue<Int>(
        Comparator.reverseOrder()
    )
    val answers = mutableListOf<Int>()
        
    for (operation in operations) {
        when (operation) {
            "2" -> {
                maxHeap.remove(stack.removeLast())
            }
            "3" -> {
                answers.add(maxHeap.peek())
            }
            else -> {
                val num = operation.split(" ")[1].toInt()
                stack.add(num)
                maxHeap.add(num)
            }
        }
    }
    
    return answers
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val ops = Array<String>(n, { "" })
    for (i in 0 until n) {
        val opsItem = readLine()!!
        ops[i] = opsItem
    }

    val res = getMax(ops)

    println(res.joinToString("\n"))
}
