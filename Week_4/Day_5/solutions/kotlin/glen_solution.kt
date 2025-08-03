class Solution {
    // Let n = # of nodes, and e = # of edges
    // - Runtime -> O(n + e)
    // - Space complexity -> O(n + e)
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        var result: Long = 0

        val adjacencyList = edgeListToAdjacencyList(n, edges);

        val visitedSet = mutableSetOf<Int>()
        for (node in 0..<n) {
            if (node in visitedSet) continue
            val componentSize = findComponentSize(adjacencyList, node, visitedSet)
            // We are adding the total number of unconnected pairs for all the nodes
            // in the current component
            result += componentSize * (n - componentSize)
        }

        // We divide by two, since we double-counted some pairs while summing up
        // the unconnected pairs per component
        return result / 2
    }

    fun findComponentSize(graph: Map<Int,List<Int>>, current: Int, visitedSet: MutableSet<Int> = mutableSetOf()): Long {
        if (current in visitedSet) return 0
        visitedSet.add(current)

        var componentSize: Long = 1

        for (neighbour in graph[current]!!) {
            componentSize += findComponentSize(graph, neighbour, visitedSet)
        }

        return componentSize
    }

    fun edgeListToAdjacencyList(n: Int, edges: Array<IntArray>): Map<Int, List<Int>> {
        val map = mutableMapOf<Int, MutableList<Int>>()
        if (edges.size == 0) {
            for (i in 0..<n) {
                map.put(i, mutableListOf<Int>())
            }

            return map
        }

        val visited = mutableSetOf<Int>()

        for ((start, end) in edges) {
            if (map.containsKey(start)) {
                map[start]!!.add(end)
            } else {
                map.put(start, mutableListOf(end))
            }
            // Since map is undirected, we also also have to add an
            // entry for the end node
            if (map.containsKey(end)) {
                map[end]!!.add(start)
            } else {
                map.put(end, mutableListOf(start))
            }

            visited.add(start)
            visited.add(end)
        }

        // Account for remaining nodes that don't have neighbours (if any)
        for (i in 0..<n) {
            if (i !in visited) map.put(i, mutableListOf<Int>())
        }

        return map
    }

    
}