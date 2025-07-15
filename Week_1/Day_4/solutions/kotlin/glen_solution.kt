class Solution {
    fun moveZeroes(nums: IntArray) {
        var j = 1
        for (i in nums.indices) {
            if (j == nums.size) return

            if (nums[i] != 0) {
                j += 1
                continue
            }

            while (j < nums.size) {
                if (nums[j] == 0) {
                    j += 1
                    continue
                }

                // We've found a non-zero number
                // to swap with nums[i]
                nums[i] = nums[j]
                nums[j] = 0

                // Move j forward since we are sure that the value
                // at the current index will be zero
                j += 1 
                break
            }
        }
    }
}