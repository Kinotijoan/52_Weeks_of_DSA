package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/**
* Time and Space Complexity
* --------------------------
* Same as in the previous solution.
* Time Complexity: O(n)
* Space Complexity: O(n)
**/
func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	if root == nil {
		return root
	}

	if root == p || root == q {
		// Since a node can be its own descendant
		return root
	}

	leftAncestor := lowestCommonAncestor(root.Left, p, q)
	rightAncestor := lowestCommonAncestor(root.Right, p, q)

	if leftAncestor == nil {
		return rightAncestor
	}

	if rightAncestor == nil {
		return leftAncestor
	}

	return root
}
