import kotlin.math.max

class Solution {
    /** 
    Complexity Analysis
    --------------------
    - Runtime complexity -> O(N + E log E)
        Runtime is consists of the following:
        - The conversion of the edge list into an adjacency list -> O(N + E)
        - Going through the nodes in the adjacency map (O(N)) and doing the logic
          for calculating the maxStarSum. The logic involves sorting the list
          of edges for each each node, which takes, in the worst case (O(E log E)).
    - Space complexity -> O(N + E)
        The space is just taken up by the adjacency map representation of the graph
    */
    fun maxStarSum(vals: IntArray, edges: Array<IntArray>, k: Int): Int {
        val adjacencyMap = edgeListToAdjacencyMap(vals, edges)
        var maxSum = Int.MIN_VALUE

        println(adjacencyMap)

        for ((node, neighbours) in adjacencyMap.entries) {
            if (k == 0 || neighbours.size == 0) {
                maxSum = max(vals[node], maxSum)
                continue
            }

            val sortedNeighbours = neighbours.map{vals[it]}
            .sortedDescending()
            .take(k) // The number of neighbours must be at most k

            var neighbourSum = 0
            for (value in sortedNeighbours) {
                // Values less than 1 will just reduce the sum
                // or impart no change to the sum,
                // so there's no need to consider them 
                // (remember the at most instruction)
                if (value < 1) break
                neighbourSum += value
            }

            val starSum = vals[node] + neighbourSum
            maxSum = max(maxSum, starSum)
        }

        // Consider nodes that do not have any edges
        for (i in vals.indices) {
            if (i in adjacencyMap) continue
            maxSum = max(maxSum, vals[i])
        }

        return maxSum
    }

    fun edgeListToAdjacencyMap(vals: IntArray, edges: Array<IntArray>): Map<Int, List<Int>> {
        val map = mutableMapOf<Int, MutableList<Int>>()
        if (edges.size == 0) {
            for (i in vals.indices) {
                map.put(i, mutableListOf())
            }

            return map
        }

        for ((start, end) in edges) {
            if (map.containsKey(start)) {
                map.get(start)!!.add(end)
            } else {
                map.put(start, mutableListOf(end))
            }
            // Since graph is undirected, we also update 
            // the entry for `end`
            if (map.containsKey(end)) {
                map.get(end)!!.add(start)
            } else {
                map.put(end, mutableListOf(start))
            }
        }

        return map
    }
}