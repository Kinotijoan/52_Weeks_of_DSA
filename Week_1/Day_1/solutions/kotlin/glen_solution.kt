class ListNode (var `val`: Int, var next: ListNode?=null)

class Solution {
    /**
    * Returns whether the linked list with the head at `head` is a palindrome.
    * Runs in O(n) time, with O(n) space**/
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) {
            return true
        }
        val elements = mutableListOf<Int>()
        var current = head

        while (current != null) {
            elements.add(current.`val`)
            current = current.next
        }

        // Reset current to head for value comparison
        current = head

        for (i in elements.indices.reversed()) {
            if (elements[i] != current?.`val`) {
                return false
            }
            current = current.next
        }

        return true
    }

    /**
    * The goal is to reduce the space complexity of `isPalindrome` above to O(1),
    * albeit with an increase in the constant term for the O(n) runtime.
    * The approach is to:
    * - find the middle of the list
    * - split the list at that halfway-point
    * - reverse one of the two halves
    * - compare the two elements from both halves together.
    **/
    fun isPalindromeWithConstantSpace(head: ListNode?) : Boolean {
        val midpoint = findLinkedListMidpoint(head)

        val right = reverseLinkedList(midpoint)

        var currentLeft = head
        var currentRight = right

        while (currentLeft != null && currentRight != null) {
            if (currentLeft.`val` != currentRight.`val`) return false

            currentLeft = currentLeft.next
            currentRight = currentRight.next
        }

        return true
    }

    /**
    * Uses the tortoise-and-hare method to find the midpoint of a linked list rooted at `head`.
    *
    * I've always understood this method like so:
    * if something goes twice as fast (the hare) as another (the tortoise) towards the same (linear) goal,
    * then by the time the faster one reaches the end, the slower one should have covered half the distance.
    * */
    fun findLinkedListMidpoint(head: ListNode?): ListNode? {
        if (head == null) return head
        var slow = head
        var fast = head

        while (fast?.next != null && slow?.next != null) {
            slow = slow.next
            fast = fast.next?.next
        }

        return slow
    }

    fun reverseLinkedList(head: ListNode?): ListNode? {
        if (head == null) return head

        var prev: ListNode? = null
        var current = head

        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        return prev
    }
}
