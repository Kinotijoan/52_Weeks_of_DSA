/// Disclaimer
/// ==============
/// I could not figure out the solution for this on my own, and the explanation presented
/// below is my interpretation of the editorial for this question. You can find said editorial at: 
/// https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/make-an-array-85abd7ad/editorial/
/// ==============
/// 
/// Since we are allowed to perform the 'add 1' operation
/// to (N - 1) elements at a time, every time we do the operation the sum of the elements
/// in the array increases by (N - 1).
/// 
/// So, if it is possible for the array B to be converted into array A,
/// then that will be after k tries, at which point the sum
/// of array B will be k * (N - 1). This should be equal to the sum of elements of array A.
/// 
/// i.e. k * (N - 1) = sum(A)
/// 
/// This means that if it is possible to convert B into A, then sum(A) must be divisible by (N - 1).
/// Otherwise, it is not possible to convert A to B.
/// 
/// To get the number of operations k, we can rearrange the equation we have up above to make k the subject.
/// We have:
/// 
/// k = sum(A) / (N - 1)
/// 
/// This number of operations should be at most equal to the largest number in A. If it greater than that, it means
/// that in array B there is a value that is greater than the largest value in A. This situation would mean
/// that it is not possible to convert B to A.
/// 
/// Otherwise, we return k as the number of operations required to convert B into A.
/// 
/// Complexity Analysis
/// ---------------------
/// - Runtime -> O(n)
/// - Space -> O(1)
/// 
/// 

static int solve(int N, int[] A)
{
    var max = A[0];

    // We are using a long value here since the problem states that sum
    // over all test cases can be at most 2 * (10 ^ 5).
    long sum = 0;

    for (long i = 0; i < A.Length; i++)
    {
        sum += A[i];
        max = Math.Max(max, A[i]);
    }

    if (sum % (N - 1) != 0)
    {
        return -1;
    }

    var noOfOperations = sum / (N - 1);

    if (max > noOfOperations)
    {
        return -1;
    }

    return (int)noOfOperations;
}