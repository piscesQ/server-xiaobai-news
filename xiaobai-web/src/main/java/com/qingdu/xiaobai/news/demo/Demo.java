package com.qingdu.xiaobai.news.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : KoreQ
 * Date : 2019/1/15
 * Description :
 */
public class Demo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("qqq");
        list.add("www");
        list.add("eee");
        list.add("rrr");

        testMethod(list);
        System.out.println(list.toString());
        System.out.println("main list = " + list);

        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch");
            return;
        } finally {
            System.out.println("finally");
        }
    }

    private static void testMethod(List<String> dataList) {
        System.out.println("testMethod dataList =AudioCount " + dataList);
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        dataList = list;
    }
}
