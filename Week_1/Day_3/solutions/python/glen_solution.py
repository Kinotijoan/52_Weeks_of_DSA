def closed_brackets(s):
    """
    This is a greedy solution to the problem. Here, we keep track of
    two variables, one representing the highest number 
    of unmatched open parenthesis possible (by turning every 'J' into an opening bracket) 
    and the other representing the lowest number of unmatched open parenthesis possible (by turning 
    every 'J' into a closing bracket).
    
    Complexity
    ------------
    - Runtime -> O(n)
    - Space -> O(1)
    
    """
    min_open_brackets = 0
    max_open_brackets = 0
    
    for c in s:
        if c == "(":
            min_open_brackets += 1
            max_open_brackets += 1
        if c == ")":
            min_open_brackets = max(0, min_open_brackets - 1)
            max_open_brackets -= 1
        if c == "J":
            min_open_brackets = max(0, min_open_brackets - 1)
            # For converting the 'J' into an opening bracket
            max_open_brackets += 1
            
        if max_open_brackets < 0:
            # This means that we have encountered a closing bracket
            # that cannot be matched, no matter what symbol we traded
            # a Joker for
            return False
        
    return min_open_brackets == 0

