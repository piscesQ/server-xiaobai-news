package com.qingdu.xiaobai.news.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Author : KoreQ
 * Date : 9/7/17
 * Description : 身份证号校验算法
 */
public class IDCardUtils {

    //身份证各位对应的权重
    private static final int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    private static String[] idNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "x", "X"};
    private static int[] checkCodeNum = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};     //身份证最后一位的所有可能
    private static final List<String> idNumList = strArr2List(idNum);

    public static void main(String[] args) {
        //370481199202240059
        //11010519491231002X
        Scanner scanner = new Scanner(System.in);
        String strIDNum = scanner.nextLine();

        checkIDNum(strIDNum);

        System.out.println("-------------------------------");

        checkIDNum2(strIDNum);

        System.out.println("-------------------------------");

//        listIDNum(strIDNum);
    }

    /**
     * 校验身份证是否有效的方法, 方法: 每一位分别与权重乘积的和  然后模11 结果为1  则有效
     *
     * @param strIDNum 字符串格式的身份证号码
     * @return
     * @throws Exception 数字转换异常
     */
    public static boolean checkIDNum(String strIDNum) {
        int sum = 0;

        if (strIDNum == null || strIDNum.length() != 18) {
            System.out.println("strIDNum 格式错误 : " + strIDNum);
            return false;
        }

        for (int i = 0; i < strIDNum.length(); i++) {
            String strNum = strIDNum.substring(i, i + 1);
            if (strNum.toLowerCase().equals("x")) strNum = "10";
            try {
                int num = Integer.parseInt(strNum);
                sum += num * weight[i];
            } catch (NumberFormatException e) {
                return false;
            }
        }

        int mode = sum % 11;    //模11   如果余数为1,则校验成功
        if (mode == 1) {
            System.out.println("sum = " + sum + ", mode = " + mode + " 校验成功 " + strIDNum);
            return true;
        } else {
            System.out.println("sum = " + sum + ", mode = " + mode + " 校验失败 " + strIDNum);
            return false;
        }
    }

    /**
     * 求前17位和  然后模11  再根据余数找出对应的校验码  即为身份证的最后一位
     *
     * @param strIDNum
     * @return
     */
    public static boolean checkIDNum2(String strIDNum) {
        int sum = 0;

        if (strIDNum == null || strIDNum.length() != 18) {
            System.out.println("strIDNum 格式错误 : " + strIDNum);
            return false;
        }
        strIDNum = strIDNum.toLowerCase();

        for (int i = 0; i < strIDNum.length() - 1; i++) {
            String strNum = strIDNum.substring(i, i + 1);
            try {
                int num = Integer.parseInt(strNum);
                sum += num * weight[i];
            } catch (NumberFormatException e) {
                return false;
            }
        }

        int mode = sum % 11;    //前17位的和模11   如果余数为1,则校验成功
        String checkCode = String.valueOf(checkCodeNum[mode]);
        if (checkCodeNum[mode] == 10) {
            checkCode = "x";
        }

        int lastIndexOf = strIDNum.lastIndexOf(checkCode);
        if (lastIndexOf == strIDNum.length() - 1) {
            System.out.println("checkCode = " + checkCode + ", index = " + lastIndexOf + " 校验成功 " + strIDNum);
            return true;
        } else {
            System.out.println("checkCode = " + checkCode + ", index = " + lastIndexOf + " 校验失败 " + strIDNum);
            return false;
        }
    }

    // 排列组合: http://blog.csdn.net/zmazon/article/details/8315418
    public static void listIDNum(String strIDNum) {
        //11ccc519491231002X
        String[] strArr = strIDNum.split("");
        for (int i = 0; i < strArr.length; i++) {
            if (idNumList.contains(strArr[i])) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    private static List<String> strArr2List(String[] charArr) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, charArr);
        return list;
    }
}
