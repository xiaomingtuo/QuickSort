package com.sort;

import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class MergeSort {

    public  static int[] mergeSort(List<int[]> dataList){
        if (dataList == null || dataList.size() == 0)
            return null;

        int size = dataList.size();
        int[] next = null;
        for (int i = 0; i < size; i++) {
            next = merge(next, dataList.get(i));
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
