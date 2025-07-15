import kotlin.collections.ArrayDeque

class MyStack() {
    private val queue = ArrayDeque<Int>()

    fun push(x: Int) {
        queue.addLast(x)
        // After adding x to the queue, move
        // every other element to the back of the queue
        // so that x sits at the very front
        repeat(queue.size - 1) {
            val value = queue.removeFirst()
            queue.addLast(value)
        }
    }

    fun pop(): Int {
        return queue.removeFirst()
    }

    fun top(): Int {
        return queue.first()
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */