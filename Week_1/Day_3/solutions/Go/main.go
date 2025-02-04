package main

import (
	"fmt"
)

func closedBrackets(str string) bool {
	runes := []rune(str)
	var min int = 0
	var max int = 0

	for _, char := range runes {
		if char == '(' {
			min++
			max++
		} else if char == ')' {
			min--
			max--
		} else if char == 'J' {
			min--
			max++
		}

		if max < 0 {
			return false
		}
		if min < 0 {
			min = 0
		}
	}

	return min == 0
}

func main() {
	// Test cases from the problem statement
	testCases := map[string]bool{
		"(J))": true,
		"(":    false,
		"":     true,
		"()J(": false,
		"J":    true,
		")(":   false,
		"J)(J": true,
		"()":   true,
	}

	for input, expected := range testCases {
		result := closedBrackets(input)
		fmt.Printf("Input: %q | Expected: %v | Got: %v\n", input, expected, result)
	}
}
