class Node(object):
    def __init__(self, data=None):
        self.data = data
        self.next = None
    
class Context(object):
    def __init__(self, first, second):
        self.first = first
        self.second = second
    
def alternating_split(head):
    if not head or not head.next:
        raise Exception("The list should have at least two elements")
        
    first = None
    second = None
    
    first_current = None
    second_current = None
    
    head_current = head
    
    # `count` is what will help with handling the alternation
    # The alternation is based on whether `count` is even or odd
    count = 0
    
    while head_current:
        if count % 2 == 0:
            node = Node(head_current.data)            
            if first == None:
                first = node
                first_current = first    
            else:
                first_current.next = node
                first_current = first_current.next
                
        else:
            node = Node(head_current.data)            
            if second == None:
                second = node
                second_current = second    
            else:
                second_current.next = node
                second_current = second_current.next
            
        count += 1
        head_current = head_current.next
        
    return Context(first, second)