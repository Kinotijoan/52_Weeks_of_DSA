public class Solution {
    /// Runtime -> O(log n)
    /// Space -> O(1)
    public int SearchInsert(int[] nums, int target) {
        // The code here is just the iterative version of the
        // binary search algorithm
        var left = 0;
        var right = nums.Length - 1;

        while (left <= right) {
            var mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
