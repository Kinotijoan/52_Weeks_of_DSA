import kotlin.collections.ArrayDeque;

class Solution {
    /** Returns true if s and t are equal after being entered
        in a text editor and false otherwise 
        (see problem description for more details)

        Approach
        ---------
        Two stacks are used for s and t. A character is pushed onto the stack
        and a '#' means that we have to use backspace, therefore we pop a character 
        from the stack.

        If the stack is empty and we encounter a '#', nothing is done, similar
        to how nothing happens when pressing backspace on an empty file in
        a text editor.

        We then compare the two stacks to see if they contain the same characters.

        Complexity
        ---------
        Here n represents n1 + n2, the length of the two strings s and t respectively

        - Runtime -> O(n)
            The algorithm is basically a sequence of three linear time operations i.e.
            adding characters from both strings to their stacks and comparing
            the two stacks. 
            Since the stack is being represented by a kotlin ArrayDeque,
            this cost is technically amortised, since the underlying array may need
            to be resized.
        - Space -> O(n)
            The extra space is due to the stacks for each string
     */
    fun backspaceCompare(s: String, t: String): Boolean {
        val stack_s = ArrayDeque<Char>()
        val stack_t = ArrayDeque<Char>()

        for (char in s) {
            if (char != '#') {
                stack_s.addLast(char)
            } else {
                if (stack_s.isNotEmpty()) {
                    stack_s.removeLast()
                }
            }
        }

        for (char in t) {
            if (char != '#') {
                stack_t.addLast(char)
            } else {
                if (stack_t.isNotEmpty()) {
                    stack_t.removeLast()
                }
            }
        }

        if (stack_s.size != stack_t.size) return false
        while (stack_s.isNotEmpty() && stack_t.isNotEmpty()) {
            if (stack_s.removeLast() != stack_t.removeLast()) {
                return false
            }
        }

        return true
    }
}