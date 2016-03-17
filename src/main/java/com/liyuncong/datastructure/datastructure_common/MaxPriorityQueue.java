package com.liyuncong.datastructure.datastructure_common;

import java.lang.reflect.Array;

import com.liyuncong.sort.sort_common.impl.HeapSortBasedOnMaxHeap;

public class MaxPriorityQueue<T extends Comparable<T>> {
	private T[] heap;
	private int heapLength;
	private HeapSortBasedOnMaxHeap<T> sortBasedOnMaxHeap = new HeapSortBasedOnMaxHeap<>();
	
	public MaxPriorityQueue(T[] a, int heapLength) {
		super();
		sortBasedOnMaxHeap.buildHeap(a, heapLength);
		this.heap = a;
		this.heapLength = heapLength;
	}
	
	public T heapMaximum() {
		return this.heap[0];
	}
	
	public T heapExtractMax() {
		if (this.heapLength < 1) {
			return null;
		}
		T max = heap[0];
		heap[0] = heap[heapLength - 1];
		heapLength--;
		sortBasedOnMaxHeap.heapify(this.heap, 0, heapLength);
		return max;
	}
	
	public void heapIncreaseKey(int i, T key) {
		if (key.compareTo(this.heap[i]) < 0) {
			try {
				throw new Exception("the key is less than heap[i]");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		this.heap[i] = key;
		/**
		 * 向上移动heap[i]的位置;
		 * 移动的条件是heap[i]不是根节点，并且heap[i]比双亲结点大
		 */
		while(i > 0 && heap[i].compareTo(this.heap[sortBasedOnMaxHeap.parent(i)]) > 0){
			T temp = this.heap[i];
			this.heap[i] = this.heap[sortBasedOnMaxHeap.parent(i)];
			this.heap[sortBasedOnMaxHeap.parent(i)] = temp;
			i = sortBasedOnMaxHeap.parent(i);
		}
	}
	
	public void maxHeapInsert(T key) {
		this.heapLength++;
		// 如果保存堆的数组已经被填满
		if (this.heapLength == this.heap.length) {
			// 新建一个更大的数组，用于保存旧数组中的元素
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) Array.newInstance(this.heap.getClass().getComponentType(),
					2 * this.heapLength);
			// 把旧数组中的元素复制进新数组中
			for(int i = 1; i < this.heapLength; i++){
				temp[i] = this.heap[i];
			}
			this.heap = temp;
			
		}
		this.heap[heapLength] = key;
		this.heapIncreaseKey(heapLength, key);
	}
	
	
	
	public T[] getHeap() {
		return heap;
	}

	public void setHeap(T[] heap) {
		this.heap = heap;
	}

	public int getHeapLength() {
		return heapLength;
	}

	public void setHeapLength(int heapLength) {
		this.heapLength = heapLength;
	}
	
	public void printHeap() {
		for(int i = 1; i <= this.heapLength; i++){
			System.out.println(this.heap[i]);
		}
	}

	public static void main(String[] args) {
		Integer[] heap = new Integer[]{0, 3, 1, 5, 12, 7};
		MaxPriorityQueue<Integer> priorityQueue = new MaxPriorityQueue<Integer>(heap, 5);
		System.out.println(priorityQueue.heapMaximum());
	    priorityQueue.printHeap();
	    System.out.println(priorityQueue.getHeapLength());
	    
	    System.out.println(".................");
	    
		System.out.println(priorityQueue.heapExtractMax());
		priorityQueue.printHeap();
		System.out.println(priorityQueue.getHeapLength());
		
		System.out.println(".................");
		priorityQueue.heapIncreaseKey(3, 5);
		priorityQueue.printHeap();
		System.out.println(priorityQueue.getHeapLength());
		
		System.out.println(".................");
		priorityQueue.maxHeapInsert(9);
		priorityQueue.printHeap();
		System.out.println(priorityQueue.getHeapLength());
	}
}