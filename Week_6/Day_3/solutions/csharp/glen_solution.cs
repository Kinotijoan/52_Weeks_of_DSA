public class Solution
{
    /// m - nums1.Length, n - nums2.Length
    /// Runtime: O(log(min(m, n)))
    /// Space: O(1)
    /// 
    /// Another solution that I haven't really understood yet. The approach written out
    /// below involves performing a binary search over the smaller array
    /// of nums1 and nums2, and using this to try and partition a virtual merged
    /// list in search of the median elements. 
    /// 
    /// The Leetcode editorial for this question
    /// (https://leetcode.com/problems/median-of-two-sorted-arrays/editorial/?envType=problem-list-v2&envId=binary-search)
    /// explains three different approaches
    /// to solving this question, and this video by the channel Neetcode
    /// (https://www.youtube.com/watch?v=q6IEA26hvXc)
    /// explains a solution that is essentially the third approach from the editorial.
    /// 
    /// PS: This third approach is what is depicted in the code below
    public double FindMedianSortedArrays(int[] nums1, int[] nums2)
    {
        // Set a to the smaller of the two arrays
        var (a, b) = nums1.Length <= nums2.Length ? (nums1, nums2) : (nums2, nums1);

        var (m, n) = (a.Length, b.Length);

        var half = (m + n + 1) / 2; // half of total no of elements in merged array

        // We are performing binary search over array a (the smaller one)
        var (left, right) = (0, m);

        var mergedArrayLengthIsEven = (m + n) % 2 == 0;

        while (left <= right)
        {
            // Compute partition indices
            var partitionA = (left + right) / 2;
            var partitionB = half - partitionA;

            // Obtain edge elements. 
            // For the indices checks, the values
            // that will be compared against e.g. maxLeftA and minRightB, at least
            // one must be set to a value from either of the arrays i.e. both can't be
            // int.MinValue and int.MaxValue. This is because both arrays 
            // are sorted, I think.
            var maxLeftA = partitionA - 1 < 0 ? int.MinValue : a[partitionA - 1];
            var minRightA = partitionA >= m ? int.MaxValue : a[partitionA];

            var maxLeftB = partitionB - 1 < 0 ? int.MinValue : b[partitionB - 1];
            var minRightB = partitionB >= n ? int.MaxValue : b[partitionB];

            // Compare and recalculate
            if (maxLeftA > minRightB)
            {
                // maxLeftA is too large to be in the smaller half
                right = partitionA - 1;
            }
            else if (maxLeftB > minRightA)
            {
                // minRightA is too small to be in the larger half
                left = partitionA + 1;
            }
            else if (maxLeftA <= minRightB && maxLeftB <= minRightA)
            {
                if (mergedArrayLengthIsEven)
                {
                    return (
                        Math.Max(maxLeftA, maxLeftB) +
                        Math.Min(minRightA, minRightB)
                    ) / 2.0;
                }
                return Math.Max(maxLeftA, maxLeftB);
            }
        }

        return -1;
    }
}