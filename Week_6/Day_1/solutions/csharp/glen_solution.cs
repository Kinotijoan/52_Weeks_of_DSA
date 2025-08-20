public class Solution
{
    // Runtime -> O(k)
    // Space -> O(1)
    public int FindKthPositive(int[] arr, int k)
    {
        int missingCount = 0;
        // Stores the value that should be the correct value
        // in the array of positive integers at position[i]
        int value = 1;

        for (int i = 0; i < arr.Length; i++)
        {
            if (arr[i] > value)
            {
                // The number of digits the current value
                // is greater than the value meant to be at the current index
                var count = arr[i] - value;
                for (int j = 0; j < count; j++)
                {
                    missingCount += 1;
                    if (missingCount == k)
                    {
                        return value;
                    }
                    value += 1;
                }
            }
            value += 1;
        }

        return arr[arr.Length - 1] + k - missingCount;
    }
}