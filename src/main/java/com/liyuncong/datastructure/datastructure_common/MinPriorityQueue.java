package com.liyuncong.datastructure.datastructure_common;

import java.lang.reflect.Array;

import com.liyuncong.sort.heap.MinHeap;

public class MinPriorityQueue<T extends Comparable<T>> {
	private T[] heap;
	private int heapLength;
	// 用于提供堆的操作
	private MinHeap<T> minHeap = new MinHeap<>();
	public MinPriorityQueue(T[] a, int heapLength) {
		super();
		minHeap.buildHeap(a, heapLength);
		this.heap = a;
		this.heapLength = heapLength;
	}
	
	public T heapMinimum() {
		return this.heap[0];
	}
	
	public T heapExtractMin() {
		if (this.heapLength < 1) {
			return null;
		}
		T min = heap[0];
		heap[0] = heap[heapLength - 1];
		heapLength--;
		minHeap.heapify(this.heap, 0, heapLength);
		return min;
	}
	
	public void heapDecreaseKey(int i, T key) {
		if (key.compareTo(this.heap[i]) > 0) {
			try {
				throw new Exception("the key is more than heap[i]");
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
		while(i > 0 && heap[i].compareTo(this.heap[minHeap.parent(i)]) < 0){
			T temp = this.heap[i];
			this.heap[i] = this.heap[minHeap.parent(i)];
			this.heap[minHeap.parent(i)] = temp;
			i = minHeap.parent(i);
		}
	}
	
	public void minHeapInsert(T key) {
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
		this.heapDecreaseKey(heapLength, key);
		this.heapLength++;
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
}
