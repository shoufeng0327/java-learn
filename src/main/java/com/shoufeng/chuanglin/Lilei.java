package com.shoufeng.chuanglin;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 李雷和韩梅梅坐前后排，上课想说话怕被老师发现，所以改为传小纸条。为了不被老师发现他们纸条上说的是啥，他们约定了如下方法传递信息：
 * 将26个英文字母（全为大写），外加空格，一共27个字符分成3组，每组9个。也就是ABCDEFGHI是第一组，JKLMNOPQR是第二组，STUVWXYZ*是第三组（此处用*代表空格）。
 * 然后根据传递纸条那天的日期，改变字母的位置。
 * 先根据月份数m，以整个分组为单位进行循环左移，移动(m-1)次。
 * 然后根据日期数d，对每个分组内的字符进行循环左移，移动(d-1)次。
 * 以3月8日为例，首先移动分组，3月需要循环左移2次，变成：
 * STUVWXYZ*，ABCDEFGHI，JKLMNOPQR
 * 然后每组内的字符，8日的话需要循环左移7次，最终的编码为：
 * Z*STUVWXY，HIABCDEFG，QRJKLMNOP
 * 对于要传递信息中的每个字符，用组号和组内序号两个数字来表示。
 * 如果在3月8日传递信息“HAPPY”，那么H位于第2组的第1个，A位于第2组第3个，P位于第3组第9个，Y位于第1组第9个，所以纸条上会写成：
 * 21 23 39 39 19
 * 现在给定日期和需要传递的信息，请输出应该写在纸条上的编码。
 *
 * 输入规范：
 * 每个输入包含两行。第一行是用空格分隔的两个数字，第一个数字是月份，第二个数字是日子。输入保证是一个合法的日期。
 * 第二行为需要编码的信息字符串，仅由A~Z和空格组成，长度不超过1024个字符。
 *
 * 输出规范：
 * 对每个输入，打印对应的编码，数字之间用空格分隔，每个输出占一行。
 *
 * 输入示例1:
 * 1 1
 * HI
 * 输出示例1:
 * 18 19
 *
 * 输入示例2:
 * 3 8
 * HAPPY
 * 输出示例2:
 * 21 23 39 39 19
 *
 * 输入示例3:
 * 2 14
 * I LOVE YOU
 * 输出示例3:
 * 35 25 18 12 29 31 25 23 12 28
 */
public class Lilei {

  public static void main(String[] args) {

    String[] arr1 = {"A","B","C","D","E","F","G","H","I"};
    int index1 = 1;
    List<String> list1 = Arrays.asList(arr1);
    String[] arr2 = {"J","K","L","M","N","O","P","Q","R"};
    int index2 = 2;
    List<String> list2 = Arrays.asList(arr2);
    String[] arr3 = {"S","T","U","V","W","X","Y","Z","*"};
    int index3 = 3;
    List<String> list3 = Arrays.asList(arr3);

    Scanner sc = new Scanner(System.in);
    String line1 = sc.nextLine();
    int m = Integer.valueOf(line1.split(" ")[0]) - 1;
    int d = Integer.valueOf(line1.split(" ")[1]) - 1;

    index1 = getNextIndex(index1,m);
    index2 = getNextIndex(index2,m);
    index3 = getNextIndex(index3,m);

    list1 = getNextList(list1,d);
    list2 = getNextList(list2,d);
    list3 = getNextList(list3,d);

    String line2 = sc.nextLine();
    line2 = line2.replace(" ","*");
    String out = "";

    for (int i = 0; i < line2.length(); i++) {
      String v = String.valueOf(line2.charAt(i)).toUpperCase();
      if (list1.contains(v)){
        out = out + index1;
        for (int i1 = 0; i1 < list1.size(); i1++) {
          if (list1.get(i1).equalsIgnoreCase(v)){
            int nexNum = (i1 + 1);
            out = out + nexNum + " ";
          }
        }
      }

      if (list2.contains(v)){
        out = out + index2;
        for (int i1 = 0; i1 < list2.size(); i1++) {
          if (list2.get(i1).equalsIgnoreCase(v)){
            int nexNum = (i1 + 1);
            out = out + nexNum + " ";
          }
        }
      }

      if (list3.contains(v)){
        out = out + index3;
        for (int i1 = 0; i1 < list3.size(); i1++) {
          if (list3.get(i1).equalsIgnoreCase(v)){
            int nexNum = (i1 + 1);
            out = out + nexNum + " ";
          }
        }
      }
    }
    System.out.println(out);
  }

  private static int getNextIndex(int index1, int m){
    while (index1 - m <= 0){
      index1 = index1 + 3;
    }
    index1 = index1 - m;
    return index1;
  }

  private static List<String> getNextList(List<String> list, int d){
    String[] arr = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      int index = i + 1;
      while (index - d <= 0){
        index = index + 9;
      }
      index = index - d - 1;
      arr[index] = list.get(i);
    }
    return Arrays.asList(arr);
  }

}
