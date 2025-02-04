package main

import "fmt"

func main() {
	// declare a 6x6 2D array
	arr := [6][6]int32{
		{-9, -9, -9, 1, 1, 1},
		{0, -9, 0, 4, 3, 2},
		{-9, -9, -9, 1, 2, 3},
		{0, 0, 8, 6, 6, 0},
		{0, 0, 0, -2, 0, 0},
		{0, 0, 1, 2, 4, 0},
	}

	// call the hourGlassSum function
	result := hourGlassSum(arr)
	fmt.Println(result)

}

func hourGlassSum(arr [6][6]int32) int32 {
	maxSum := int32(0)
	for i := 0; i < 4; i++ {
		for j := 0; j < 4; j++ {
			sum := arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2]
			if sum > maxSum {
				maxSum = sum
			}
		}
	}
	return maxSum
}
