package com.shoufeng.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BioDemo {
    public static void main(String[] args) throws IOException {
//        try (FileOutputStream fileOutputStream = new FileOutputStream("./1.txt");){
//            fileOutputStream.write("test".getBytes());
//        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("./1.txt");
             FileInputStream fileInputStream = new FileInputStream("./3.test")){
            byte[] bytes = new byte[1024];
            int len;
            while ( (len = fileInputStream.read(bytes)) > 0){
                fileOutputStream.write(bytes,0,len);
            }
        }

    }
}
