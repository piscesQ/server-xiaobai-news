package com.qingdu.xiaobai.news.utils;

import java.text.DecimalFormat;

/**
 * Author : KoreQ
 * Date : 10/27/17
 * Description : 收益计算工具
 * ref: [民工君: 如何用股票给自己养老? - 雪球](https://xueqiu.com/1047509468/75535689)
 * [好买商学院: 提前退休，一份定投方案就能搞定！ - 雪球](https://xueqiu.com/4387435692/84196666)
 * [qs_cn: 为了保证退休后的生活，现在我们能做什么？- 雪球](https://xueqiu.com/9741961586/72364346)
 */
public class MoneyUtils {
    /**
     * 计算投资收益 方式: 现金分红(单利)
     *
     * @param monthMoney 每月定投金额
     * @param rate       年化收益率
     * @param years      定投时间,单位:年
     */
    public static void incomeSimple(double monthMoney, double rate, int years) {

        double principle = monthMoney * 12 * years;     //计算本金
        double monthRate = rate / 12;      //每月收益率
        double monthIncomeUnit = monthMoney * monthRate;    //单位定投金额的每月收益
        double totalIncome = monthIncomeUnit * sum(years * 12);
        double total = principle + totalIncome;
//        System.out.println("principle = " + principle + ",\t rate = " + totalIncome + ",\t total = " + total);
        System.out.printf("principle = %.2f,\t rate = %.2f,\t total = %.2f,\t %.2f倍 %n",
                principle, totalIncome, total, total / principle);
    }

    /**
     * 计算投资收益 方式: 红利再投(复利)
     *
     * @param monthMoney 每月定投金额
     * @param rate       年化收益率
     * @param years      定投时间,单位:年
     */
    public static void incomeCompound(double monthMoney, double rate, int years) {
        double principle = monthMoney * 12 * years;     //计算总投入本金
        double currMoney = monthMoney;  //当前本金
        double monthRate = rate / 12;      //每月收益率

        for (int i = 1; i <= years * 12; i++) {
            currMoney += currMoney * monthRate;
            currMoney += monthMoney;
        }

        System.out.printf("principle = %.2f,\t rate = %.2f,\t currMoney = %.2f,\t %.2f倍  %n",
                principle, currMoney - principle, currMoney, currMoney / principle);
    }

    /**
     * 计算累加和
     *
     * @param num
     * @return
     */
    private static int sum(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 多种数字格式输出测试
     */
    public static void testNumFormat() {

        //---------------------------------------------
        //定义一个数字格式化对象，格式化模板为".##"，即保留2位小数.
        DecimalFormat a = new DecimalFormat(".##");
        String s = a.format(333.335);
        String s2 = a.format(666.6);
        System.err.println(s);
        System.err.println(s2);
        //说明：如果小数点后面不够2位小数，不会补零.参见Rounding小节
        //---------------------------------------------

        //-----------------------------------------------
        //可以在运行时刻用函数applyPattern(String)修改格式模板
        //保留2位小数，如果小数点后面不够2位小数会补零
        a.applyPattern(".00");
        s = a.format(333.3);
        System.err.println(s);
        //------------------------------------------------

        //------------------------------------------------
        //添加千分号
        a.applyPattern(".##\u2030");
        s = a.format(0.789366);
        System.err.println(s);//添加千位符后,小数会进三位并加上千位符
        //------------------------------------------------

        //------------------------------------------------
        //添加百分号
        a.applyPattern("#.##%");
        s = a.format(0.78645);
        System.err.println(s);
        //------------------------------------------------

        //------------------------------------------------
        //添加前、后修饰字符串，记得要用单引号括起来
        a.applyPattern("'这是我的钱$',###.###'美圆'");
        s = a.format(123456789123456789.3333);
        System.err.println(s);
        //------------------------------------------------

        //------------------------------------------------
        //添加货币表示符号(不同的国家，添加的符号不一样
        a.applyPattern("\u00A4");
        s = a.format(34);
        System.err.println(s);
        //------------------------------------------------

        //-----------------------------------------------
        //定义正负数模板,记得要用分号隔开
        a.applyPattern("0.0;'@'-#.0");
        s = a.format(33);
        System.err.println(s);
        s = a.format(-33);
        System.err.println(s);
        //-----------------------------------------------

        //综合运用，正负数的不同前后缀
        String pattern = "'my moneny'###,###.##'RMB';'ur money'###,###.##'US'";
        a.applyPattern(pattern);
        System.out.println(a.format(1223233.456));
    }

    public static void main(String[] args) {
//        testNumFormat();

        System.out.println("--------------");
        incomeSimple(2110, 0.08, 20);
        incomeSimple(1000, 0.03, 10);
        incomeSimple(3000, 0.1, 30);

        System.out.println("--------------");
        incomeCompound(3200, 0.10, 25);
        incomeCompound(1000, 0.03, 10);
        incomeCompound(3000, 0.13, 30);
        incomeCompound(1100, 0.09, 25);
    }
}
