/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class Solution {
    /**
    Makes a deep copy of the graph that `node` is a part of.

    Returns a copy of `node` as an entry point to the deep copy.

    Complexity
    ------------
    - Runtime -> O(e) where e is the number of edges
    - Space -> O(n) where n is the number of nodes
     */
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        if (node.neighbors.isEmpty()) return Node(node.`val`)

        // `map` maps a value to a node. This is useful
        // in allowing the nodes for the clone
        // to be linked to existing node as needed.
        // This only works because one of the constraints is that
        // all the values in the original graph are unique.
        val map = mutableMapOf<Int, Node>()

        // Setup for breadth-first search
        val visitedSet = mutableSetOf<Int>()
        val queue = ArrayDeque<Node>()
        queue.addLast(node)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val value = current.`val`

            if (value in visitedSet) continue
            visitedSet.add(value)

            if (value !in map) {
                map[value] = Node(value)
            }

            for (neighbor in current.neighbors) {
                val neighborValue = neighbor!!.`val`
                queue.addLast(neighbor!!)

                if (neighborValue !in map) {
                    map[neighborValue] = Node(neighborValue)
                }

                map[value]!!.neighbors.add(map[neighborValue]!!)
            }
        }

        return map[node.`val`]!!
    }
    
}