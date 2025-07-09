class Node:
    def __init__(self,data):
        self.right=None
        self.left=None
        self.data=data

class Solution:
    def insert(self,root,data):
        if root is None:
            return Node(data)
        else:
            if data<=root.data:
                cur=self.insert(root.left,data)
                root.left=cur
            else:
                cur=self.insert(root.right,data)
                root.right=cur
        return root
    def getHeight(self,root):
        if root == None:
            return -1
        else:
            left_subtree=self.getHeight(root.left)
            right_subtree=self.getHeight(root.right)

        return max(left_subtree,right_subtree) +1



T=int(input("Number of Nodes"))
myTree=Solution()
root=None
for i in range(T):
    data=int(input())
    root=myTree.insert(root,data)
height=myTree.getHeight(root)

print("Height:",height)






