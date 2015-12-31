package com.sort;

import com.sort.MergeSort;
import com.sort.RandomNumMaker;
import com.sort.SortTask_C;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.sound.sampled.DataLine;

public class QuickSort {
    public static String FILE_OUTPUTPATH = "_output";
    public static int numLength = 2000;
    public static void main(String[] args) {
        int nThreadCount = 10;
        String filename = "";
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            nThreadCount = Integer.parseInt(args[0]);
        }

        // 参数处理问题
        // 文件名处理

        if(nThreadCount>10){
            nThreadCount = 10;
        }else if(nThreadCount<2){
            nThreadCount = 2;
        }

        final AtomicInteger atomicInteger = new AtomicInteger(nThreadCount);
        RandomNumMaker numMaker = new RandomNumMaker();
        //-------------------------------产生随机数文件------------------------------------
//         try {// 产生文件
//         numMaker.makefile(numLength);
//         } catch (IOException e) {
//         e.printStackTrace();
//         }

        //-------------------------------读取文件------------------------------------
        int[] array = ArrayReader.read(RandomNumMaker.FILE_PATH);
        //-------------------------------平分数组------------------------------------
        List<int[]> dataList = separateArray(array, nThreadCount);
        //-------------------------------把线程放到list中管理，启动线程-------------
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < nThreadCount; i++) {
            Thread thread = new Thread(new SortTask_C(dataList.get(i), atomicInteger));
            threadList.add(thread);
        }

        for (Iterator<Thread> iterator = threadList.iterator(); iterator.hasNext();) {
            iterator.next().start();
        }
        //-------------------------------等待线程结束------------------------------------
        // 稍显复杂，有待优化
        System.out.println(atomicInteger.get());
        while (atomicInteger.get() > 0) {
            System.out.println("left counter: " + atomicInteger.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread " + " is waiting....");
        }
        System.out.println("all Threads are finished");
        //-------------------------------归并排序------------------------------------
        int[] out = MergeSort.mergeSort(dataList);
        //-------------------------------产生文件------------------------------------
        try {
            numMaker.makefile(out, FILE_OUTPUTPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ArrayList<int[]> separateArray(int[] array, int count) {
        ArrayList<int[]> als_array = new ArrayList<>();
        int nThreads = count;// 线程个数，即数组分多少分
        int arrayCount = array.length;// 数组个数
        int divisor = arrayCount / nThreads;// 除数
        int remainder = arrayCount % nThreads;// 余数
        /*
         * @arrayCount 读到的数组长度
         *
         * @nThreads 线程个数，即把数组分成多少份 最后一份要加上arrayCount/nThreads中没有被除尽的个数
         */
        // 为平分后的每个数组赋值
        for (int i = 0; i < nThreads; i++) {
            int data = new Random().nextInt(100);
            int[] aa = null;
            if (i != nThreads - 1) {
                aa = new int[divisor];
            } else {
                aa = new int[divisor + remainder];
            }
            for (int k = 0; k < divisor; k++) {
                aa[k] = array[divisor * i + k];
            }
            als_array.add(aa);
        }
        // 最后一个数组要加上整个数组平分成nThreads份后多出的几个数
        for (int i = 0; i < remainder; i++) {
            als_array.get(nThreads - 1)[divisor + i] = 22;
        }
        // 打印数组长度
        for (int i = 0; i < als_array.size(); i++) {
            for (int j = 0; j < divisor; j++) {
                System.out.println(als_array.get(i)[j]);
                if ((i == als_array.size() - 1) && j == (divisor - 1)) {
                    for (int k = 0; k < remainder; k++)
                        System.out.println(als_array.get(i)[j + k + 1]);
                }
            }
        }

        for (int i = 0; i < als_array.size(); i++) {
            System.out.println(als_array.get(i).length);
        }
        return als_array;
    }

    private static class ArrayReader {

        public static int[] read(String path) {
            int[] read = null;
            String[] str = null;
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("the file not exist!");
                return null;
            }
            String file_name =file.getName();
            String root_path = file.getParent();
            String file_name_nosuffix = file_name.substring(0, file_name.lastIndexOf("."));
            String file_name_suffix = file_name.substring(file_name.lastIndexOf("."));
            FILE_OUTPUTPATH = root_path+file_name_nosuffix+FILE_OUTPUTPATH+file_name_suffix;

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = null;
                int index = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    str = line.split(",");
                }
                read = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    read[i] = Integer.parseInt(str[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (read.length > 0) {
                System.out.println("read file success");
                System.out.println(read.length);
            }
            return read;
        }
    }
}
