
/*
* Returns the maximum hourglass sum of a 6 x 6 array (see problem description).
* Takes O(1) time and space, because the input size is known,
* and never changes (a 6 x 6 array).
*/
fun hourglassSum(arr: Array<Array<Int>>): Int {
    var maxSum = Int.MIN_VALUE

    for (i in 0..3) {
        for (j in 0..3) {
            var sum = 0
            // Top
            sum += arr[i][j] + arr[i][j+1] + arr[i][j+2]
            // Middle
            sum += arr[i+1][j+1]
            // Bottom
            sum += arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2]

            if (sum > maxSum) {
                maxSum = sum
            }
        }
    }

    return maxSum
}