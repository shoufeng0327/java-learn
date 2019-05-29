package com.shoufeng.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class InputOutputDemo {

  public static void main(String[] args) throws IOException {
//    Files.copy(Paths.get("/Users/shoufeng/work/java-learn/1.test"),new FileOutputStream("./2.test"));
    Files.copy(Paths.get("/Users/shoufeng/work/java-learn/1.test"),Paths.get("/Users/shoufeng/work/java-learn/3.test"), StandardCopyOption.COPY_ATTRIBUTES);
//    nioIo();
  }

  private static void oldIo() throws IOException {
    FileInputStream fileInputStream = new FileInputStream("1.test");
    FileOutputStream fileOutputStream = new FileOutputStream("./2.test");
    byte[] bytes = new byte[64];
    int len;
    while ((len = fileInputStream.read(bytes)) > 0){
      fileOutputStream.write(bytes,0,len);
    }
    fileInputStream.close();
    fileOutputStream.close();
  }

  private static  void nioIo() throws IOException {
    FileInputStream fileInputStream = new FileInputStream("1.test");
    FileChannel fileInputStreamChannel = fileInputStream.getChannel();
    FileOutputStream fileOutputStream = new FileOutputStream("./2.test");
    FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
    long count = fileInputStreamChannel.size();
    while (count > 0){
      long transferred = fileInputStreamChannel
          .transferTo(fileInputStreamChannel.position(), count, fileOutputStreamChannel);
      fileInputStreamChannel.position(fileInputStreamChannel.position() + transferred);
      count = count - transferred;
    }
  }
}
