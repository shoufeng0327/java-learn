package com.shoufeng.mybatis;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;

public class MybatisDemo {

  public static void main(String[] args) throws IOException {
    InputStream inputStream = Resources.getResourceAsStream("/Users/shoufeng/work/java-learn/1.test");
    byte[] bytes = new byte[64];
    inputStream.read(bytes);
    System.out.println(bytes.toString());
  }
}
