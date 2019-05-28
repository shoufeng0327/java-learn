package com.shoufeng.chuanglin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题目1
 * 小明的女朋友最喜欢在网上买买买了，可是钱包里钞票有限，不能想买啥就买啥。面对琳琅满目的物品，她想买尽可能多的种类，每种只买一件，同时总价格还不能超过预算上限。于是她请小明写程序帮她找出应该买哪些物品，并算出这些物品的总价格。
 *
 * 输入规范：
 * 每个输入包含两行。第一行是预算上限。第二行是用空格分隔的一组数字，代表每种物品的价格。所有数字都为正整数并且不会超过10000。
 *
 * 输出规范：
 * 对每个输入，输出应买物品的总价格。
 *
 * 输入示例1:
 * 100
 * 50 50
 * 输出示例1:
 * 100
 *
 * 输入示例2:
 * 188
 * 50 42 9 15 105 63 14 30
 * 输出示例2:
 * 160
 */
public class XiaoMing {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int sum = Integer.valueOf(sc.nextLine());
    String pricesStr = sc.nextLine();
    List<Integer> priceList = new ArrayList<>();
    String[] pricesArr = pricesStr.split(" ");
    for (String s : pricesArr) {
      priceList.add(Integer.valueOf(s));
    }
    Collections.sort(priceList);
    int out = 0;
    for (Integer integer : priceList) {
      if (sum - integer >= 0) {
        out = out + integer;
        sum = sum - integer;
      } else {
        break;
      }
    }
    System.out.println(out);
  }
}
