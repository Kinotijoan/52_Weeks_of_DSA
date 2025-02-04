package main

import "fmt"

func main() {
	nums := []int{16601, 78714, 11653, -45162, 0, -22859, 0, 36007, 19143, -91750, 0, -45361, -10715, 46528, -91518, -36985, 59578, 76628, -87592, 89803, 0, -41430, 44290, 59831, 41824, -30916, -6521, 61614, 46035, 39346, 0, 0, 32417}
	moveZeroes(nums)
	fmt.Println(nums)
}

func moveZeroes(nums []int) {
	var left int = 0
	var right int = 1

	for right < len(nums) {
		if nums[left] == 0 && nums[right] == 0 {
			right++
		} else if nums[left] == 0 && nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			left++
			right++
		} else {
			left++
			right++
		}
	}
}
