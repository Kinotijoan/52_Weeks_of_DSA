package main

import (
	"errors"
	"fmt"
)

type Node struct {
	data int
	next *Node
}

type Context struct {
	first  *Node
	second *Node
}

func alternatingLinkedList(head *Node) (*Context, error) {
	if head == nil || head.next == nil {
		return nil, errors.New("list must contain at least two nodes")
	}

	var firstHead, secondHead, firstTail, secondTail *Node
	var isFirst = true
	var current = head

	for current != nil {
		nextNode := current.next
		current.next = nil

		if isFirst {
			if firstHead == nil {
				firstHead = current
				firstTail = current
			} else {
				firstTail.next = current
				firstTail = current
			}
		} else {
			if secondHead == nil {
				secondHead = current
				secondTail = current
			} else {
				secondTail.next = current
				secondTail = current
			}
		}

		current = nextNode
		isFirst = !isFirst
	}
	return &Context{first: firstHead, second: secondHead}, nil
}

func ArrayToLinkedList(arr []int) *Node {
	var head, tail *Node

	for _, val := range arr {
		newNode := &Node{data: val}
		if head == nil {
			head = newNode
			tail = newNode
		} else {
			tail.next = newNode
			tail = newNode
		}
	}

	return head
}

func PrintLinkedList(head *Node) {
	current := head
	for current != nil {
		fmt.Printf("%d -> ", current.data)
		current = current.next
	}
	fmt.Println("nil")
}

func main() {

	var arr = []int{1, 2, 3, 4, 5}
	list := ArrayToLinkedList(arr)
	PrintLinkedList(list)

	result, err := alternatingLinkedList(list)

	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("First List:")
	PrintLinkedList(result.first)

	fmt.Println("Second List:")
	PrintLinkedList(result.second)
}
