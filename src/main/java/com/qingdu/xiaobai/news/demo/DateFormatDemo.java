package com.qingdu.xiaobai.news.demo;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 消息中心 - 每个list的Adapter
 * <p>
 * author : YanBin on 3/29/17
 * version : v7.0
 * description : 时间格式化测试
 */
public class DateFormatDemo {
    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//        holder.msg_date.setText(TimestampUtils.getTimeState(String.valueOf(itemBean.createTime), "yyyy-MM-dd HH:mm:ss"));
        String format = dateFormat.format(timeMillis);
        System.out.print(format);
    }
}
