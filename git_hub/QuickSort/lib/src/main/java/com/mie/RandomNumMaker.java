package com.mie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class RandomNumMaker {
    public static String FILE_PATH = "D:\\num.txt";
    public void makefile(int numLength) throws IOException {
        File file = new File(FILE_PATH);
        Random random = new Random();
        int[] array = new int[numLength];
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (int i : array) {
                array[i] = random.nextInt(1000);
                bufferedWriter.write(String.valueOf(array[i]) + ",");
                // bufferedWriter.newLine();
            }
        }catch (Exception e){

        }finally {
            if(bufferedWriter!=null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }

        System.out.println("Write file success");
    }

    public void makefile(int[] array,String filepath) throws IOException {
        File file = new File(filepath);
        BufferedWriter bufferedWriter =null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (int i = 0; i < array.length; i++) {
                bufferedWriter.write(String.valueOf(array[i]) + ",");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bufferedWriter!=null){
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }

        System.out.println("Write file success");
    }
}
