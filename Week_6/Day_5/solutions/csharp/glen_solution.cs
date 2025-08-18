public class Solution
{
    /// Performs binary search on a matrix with elements arranged
    /// in ascending order. The approach is to treat the 2D array matrix as
    /// a flattened 1D array while calculating the indices for the binary search,
    /// and converting that back into row and column values in order to index the
    /// matrix and get the value for comparison.
    /// 
    /// Complexity analysis
    /// --------------------
    /// - Runtime -> O(log(m + n))
    /// - Space -> O(1)
    public bool SearchMatrix(int[][] matrix, int target)
    {
        var m = matrix.Length;
        var n = matrix[0].Length;

        var elements = m * n;

        var start = 0;
        var end = elements - 1;

        while (start <= end)
        {
            var mid = (start + end) / 2;
            var (row, col) = GetRowAndCol(mid, n);
            var value = matrix[row][col];

            if (target > value)
            {
                start = mid + 1;
            }
            else if (target < value)
            {
                end = mid - 1;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    /// Expands a flattened index of a matrix with n columns into
    /// the the corresponding row and column
    private (int row, int col) GetRowAndCol(int flatNdx, int n)
    {
        return (flatNdx / n, flatNdx % n);
    }
}