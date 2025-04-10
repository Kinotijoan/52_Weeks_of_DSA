from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        """
        Time complexity
        ----------------
        O(n) where n is the number of nodes in the tree. We visit each node exactly once.

        Space complexity
        ----------------
        The recursion stack has a maximum number of nodes equal to height of the tree.
        The time complexity is O(n) because the worst case is that the tree 
        is just a single long chain, and here, we'll need 
        to store all n nodes in the recursion stack.
        """
        result = []

        def traverse(root: Optional[TreeNode]):
            if root is not None:
                traverse(root.left)
                result.append(root.val)
                traverse(root.right)

        traverse(root)
        return result
