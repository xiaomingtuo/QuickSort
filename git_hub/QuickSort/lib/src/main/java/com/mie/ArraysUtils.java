package com.mie;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/1/4.
 */
public class ArraysUtils {

    private ArraysUtils() {}

    public static int toInt(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 10;
    }
}
