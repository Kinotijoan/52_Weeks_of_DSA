using System;

class Node{
    public Node left,right;
    public int data;
    public Node(int data){
        this.data=data;
        left=right=null;
    }
}
class Solution{
    /// <summary>
    /// Aside
    /// ------
    /// This was inspired by the solution for Day 2's problem. The difference is that instead of
    /// calculating the max sum between the left and right subtrees
    /// and adding it to the value of the current node, we are calculating the max height 
    /// between the subtrees, and then adding one to the value
    /// 
    /// Time and Space Complexity
    /// --------------------------
    /// The time and space complexity are both O(n), for the same reasons as in Day 2's problem.
    /// </summary>
	static int getHeight(Node root){
      if (root == null) {
        return 0;
      }
      if (root.left == null && root.right == null) {
        return 0;
      }
      
      int maxHeight;
      
      if (root.left == null) {
        maxHeight = getHeight(root.right);
      }
      else if (root.right == null) {
        maxHeight = getHeight(root.left);
      } else {
        var leftHeight = getHeight(root.left);
        var rightHeight = getHeight(root.right);
        maxHeight = Math.Max(leftHeight, rightHeight); 
      }
      
      return 1 + maxHeight;
    }
}