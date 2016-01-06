package com.mie;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortTask_C implements Runnable {
	private final int[] array;
	private final AtomicInteger mCounter;
	private final CountDownLatch countDownLatch;
	public SortTask_C(int[] array, AtomicInteger counter,CountDownLatch countDownLatch) {
		this.array = array;
		this.mCounter = counter;
		this.countDownLatch = countDownLatch;
	}

	//	public SortTask_C(int[] array, AtomicInteger counter) {
//		this.array = array;
//		this.mCounter = counter;
//	}
	@Override
	public void  run() {
		//quickSort(array,0,array.length-1);
		//mCounter.decrementAndGet();
		try {
			quickSort(array, 0, array.length-1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			countDownLatch.countDown();
			//System.out.println(countDownLatch.getCount());
		}
	}
	public static void quickSort(int[] arr) {
		quickSort(arr,0,arr.length-1);
	}
	public static void quickSort(int[] arr, int low, int high) {
		if(low<high){
			int middle = getMiddle(arr,low,high);
			quickSort(arr,low,middle-1);
			quickSort(arr,middle+1,high);
		}
	}

	public static int getMiddle(int[] arr,int low,int high) {
		if (low < high) {
			int temp = arr[low];// 基准元素
			while (low < high) {
				// 找到比基准元素小的元素位置
				while (low < high && arr[high] >= temp) {
					high--;
				}
				//if (low < high)
					arr[low] = arr[high];
				while (low < high && arr[low] <= temp) {
					low++;
				}
				//if (low < high)
					arr[high] = arr[low];
			}
			arr[low] = temp;
		}
		return low;
	}

//	public int  quickSort() {
//		int head, tail;
//		head = start;
//		tail = end + 1;
//		while (true) {
//			/*找到一个比head大的*/
//			do {
//				//if(head<end-1)
//					head++;
//			} while (!(array[head] >= array[start] || head == end));
//			/* 找到一个比head小的*/
//			do {
//				//if(tail>1)
//					tail--;
//			} while (!(array[start] >= array[tail] || tail == start));
//			if (head < tail)
//				swap(head, tail);
//			else
//				break;
//		}
//		swap(start, tail);
//		middle = tail;
//		return middle;
//	}

	public void swap(int a, int b) {
		int temp = 0;
		temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
