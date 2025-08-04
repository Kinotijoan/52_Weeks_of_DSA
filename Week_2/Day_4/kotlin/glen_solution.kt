class Solution {
    /* 
        Complexity
        -----------
        - Runtime -> O(n)
            The algorithm must go through every character in `s`
        - Space -> O(n)
            The stack can end up storing all of the characters in case
            `s` consists only of opening bracket characters
     */
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        val openingBrackets = "({["
        val closingBrackets = ")}]"

        for (c in s) {
            if (c in openingBrackets) {
                stack.addLast(c)
            } 
            else {
                if (stack.isEmpty()) return false
                val current = stack.removeLast()
                if (openingBrackets.indexOf(current) != closingBrackets.indexOf(c))
                return false
            }
        }

        return stack.isEmpty()
    }
}