class Solution {
    /* Complexity
     *   ----------
     * N is the number of nodes and E is the number of edges between those nodes.
     * - Runtime O(N + E)
     * - Space O(N + E)
     *
     */
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val adjacencyMap = mutableMapOf<Int, MutableList<Int>>()

        // Convert the edge list into an adjacency map
        for ((start, end) in edges) {
            if (start in adjacencyMap) {
                adjacencyMap[start]!!.add(end)
                if (end in adjacencyMap) {
                    adjacencyMap[end]!!.add(start)
                } else {
                    adjacencyMap[end] = mutableListOf(start)
                }
            } else {
                adjacencyMap[start] = mutableListOf(end)
                if (end in adjacencyMap) {
                    adjacencyMap[end]!!.add(start)
                } else {
                    adjacencyMap[end] = mutableListOf(start)
                }
            }
        }

        val visitedSet = mutableSetOf<Int>();
        val queue = ArrayDeque<Int>();

        queue.addLast(source);

        // Do a breadth-first search starting from `source`
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            if (node == destination) return true
            if (node in visitedSet) continue

            visitedSet.add(node)

            for (neighbour in adjacencyMap[node]!!) {
                queue.addLast(neighbour)
            }
        }

        // It was not possible to reach `destination` from `source`
        return false 
    }
}