class Solution:
    def findCenter(self, edges: List[List[int]]) -> int:
        # Time and space complexity: O(1)
        # We will always just pick the first two edges, and check
        # the node they have in common.
        # The star graph has a unique property that every
        # node will have the center node as a common connection
        a, b = edges[0]
        c, d = edges[1]

        if a == b or a == c or a == d:
            return a
        if b == c or b == d:
            return b
        if c == d:
            return c

        # Since the star graph is always valid, this line should never run
        # i.e. we will always find the center of the star graph
        return -1
        
