
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __repr__(self):
        return f'TreeNode({self.val})'


def list_to_tree(lst):

    if len(lst) == 0:
        return None

    nodes= [TreeNode(val) for val in lst]

    for i in range(len(lst)):
        if nodes[i] is not None:
            left=2*i+1
            right=2*i+2

            if left < len(lst):
                nodes[i].left = nodes[left]
            if right < len(lst):
                nodes[i].right = nodes[right]

    return nodes

lst = [17, 0, -4, 3, 15]

my_tree = list_to_tree(lst)

print(my_tree)

#Just testing
print(my_tree[1].left)
print(my_tree[1].right)