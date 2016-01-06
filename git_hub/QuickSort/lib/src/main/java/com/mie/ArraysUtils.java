package com.mie;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/1/4.
 */
public class ArraysUtils  {

    private ArraysUtils() {
    }

    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        //b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.toString();
            //b.append(", ");
        }
    }
}
