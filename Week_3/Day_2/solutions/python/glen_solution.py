# from preloaded import TreeNode

class TreeNode:
    def __init__(self, value, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right


def max_sum(root: TreeNode) -> int:
    """
    Intuition
    ----------------
    The max sum of a tree is the sum of the greatest value between the left, and right
    subtrees, plus the value of the root.

    The time and space complexity are the same as in Day 1 of week 1, even though
    the function seems more involved. 
    We are still traversing the tree in a depth-first manner,
    hitting every node exactly once along the way.

    Time complexity
    ----------------
    O(n)

    Space complexity
    ----------------
    O(n)
    """
    if root is None:
        return 0
    elif not root.left and root.right:
        greater = max_sum(root.right)
    elif not root.right and root.left:
        greater = max_sum(root.left)
    elif not root.right and not root.left:
        return root.value
    else:
        left_max = max_sum(root.left)
        right_max = max_sum(root.right)
        greater = max(left_max, right_max)

    return greater + root.value
