package com.mie;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/12/31.
 */
public class MergeSort {

    public  static int[] mergeSort(List<int[]> dataList){
        if (dataList == null || dataList.size() == 0)
            return null;

        int size = dataList.size();
        int[] next = dataList.get(0);
        for (int i = 1; i < size; i++) {
            next = merge(next, dataList.get(i));//归并
            SortTask_C.quickSort(next);         //排序
        }

        return next;
    }

    public static int[] merge(int[] a, int[] b) {
        if (a == null || a.length == 0)
            return b;

        if (b == null || b.length == 0)
            return a;

        int[] c = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length&&i<b.length) {
            if (a[i] < b[i]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        while (i < a.length) {
            c[k++] = a[i++];
        }
        while (j < b.length) {
            c[k++] = b[j++];
        }
        return c;
    }
}
