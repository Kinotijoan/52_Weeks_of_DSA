class Node:
    def __init__(self, value, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right


def array_to_tree(arr):
    """
    Aside
    ----------
    This question reminded me of heaps implemented as arrays.
    Since heaps are complete binary trees, the method of accessing the child
    nodes in this question is similar to that in heaps.

    Time complexity
    ----------------
    O(2n) = O(n) as we are looping through `arr` twice.
    Once, to create the nodes and once to connect the children appropriately.

    Space complexity
    -----------------
    O(n) as we are creating `n` nodes.
    """

    if len(arr) == 0:
        return None

    nodes = [Node(val) for val in arr]

    for i in range(len(arr)):
        left = 2 * i + 1
        right = 2 * i + 2

        if left < len(arr):
            nodes[i].left = nodes[left]
        if right < len(arr):
            nodes[i].right = nodes[right]

    return nodes[0]
