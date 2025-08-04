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

/**
* Returns the maximum possible height of the stacks such that all of the stacks are exactly the same height.
* It removes zero or more cylinders from the top of zero or more of the three stacks
* until they are all the same height,
*
* Complexity
* -----------
* - Runtime -> O(n1 + n2 + n3 + max(n1, n2, n3)) = O(n1 + n2 + 23)
    Here, n1, n2, n3 represents the length of the stacks h1, h2 and h3 respectively

    The initial calculations for sums have a runtime of O(n1 + n2 + n3) which is simply
    the addition of the time taken to calculate each individual sum.

    Considering the while loop, in the worst-case,
    we could have one list with a height of 1 and other lists with a large number of ones.
    Since we pop at least one element (when the sums are not equal yet)
    at each iteration of the while loop, the number of iterations 
    will be limited by the maximum number of elements in the stacks.
    Hence the runtime for the loop is O(max(n1, n2, n3))
    
    Technically, there should be a multiplication by 3 since the for-loop in the while loop
    runs thrice each time, but that constant becomes less important as n grows larger.

    The equal function takes constant time here since 
    the length of the sums array doesn't change. 

  - Space -> 0(1)
    The arrays created here are all of constant length,
    and the variable created take constant space
 */
fun equalStacks(h1: Array<Int>, h2: Array<Int>, h3: Array<Int>): Int {
    // Store the arrays received in the parameters into an 
    // array for convenient access later
    val heights = arrayOf(h1, h2, h3)
    // `tops` tracks where we are (index-wise) for each stack. 
    // Adding one to an index is equivalent to
    // moving to the next entry of the stack
    val tops = arrayOf(0, 0, 0)
    // Store the totals of each array. As we remove elements from the
    // stack, we subtract the values removed from the relevant sum
    val sums = arrayOf(h1.sum(), h2.sum(), h3.sum())
    
    // We keep track of the minimum sum, since for the heights to be equal
    // we must pop values from different stacks such that
    // they are equal to the stack with the lowest height at the moment
    var minSum = sums.min()
    
    while (!equal(sums)) {
        for (i in heights.indices) {   
            if (tops[i] >= heights[i].size) {
                // Here, the stack is empty, therefore the rest also
                // need to be empty for them to have equal height
                return 0
            }
            
            if (sums[i] > minSum) {
                // Here, we pop a value from the stack, and update
                // the sum. If the sum falls below the current `minSum`
                // it becomes the new `minSum`
                val top = heights[i][tops[i]]
                sums[i] -= top
                minSum = kotlin.math.min(minSum, sums[i])
                tops[i] += 1
            }    
        }
    }
        
    return minSum
}

fun equal(nums: Array<Int>): Boolean {
    for (i in 0..<nums.size) {
        for (j in 0..<nums.size)  {
            if (nums[i] != nums[j]) return false
        }
    }

    return true
}


fun main(args: Array<String>) {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n1 = first_multiple_input[0].toInt()

    val n2 = first_multiple_input[1].toInt()

    val n3 = first_multiple_input[2].toInt()

    val h1 = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val h2 = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val h3 = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = equalStacks(h1, h2, h3)

    println(result)
}