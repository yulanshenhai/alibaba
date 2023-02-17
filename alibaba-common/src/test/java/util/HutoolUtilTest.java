package util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.xiao.entity.User;
import com.xiao.util.Result;
import org.junit.Test;

import java.time.Year;
import java.util.*;

/**
 * @author xiao
 */
public class HutoolUtilTest {

    @Test
    public void testConvert() {

        // 转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);
        System.out.println("aStr: " + aStr);

        // 转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        System.out.println("bArr: " + Arrays.toString(bArr));

        // 转换为日期对象
        String dateStr = "2017年05月06日 12:23:46";
        Date date = Convert.toDate(dateStr);
        System.out.println(date);

        // 转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);
        System.out.println(strList);

        // bytes转换int
        byte[] bytesToIntStr = {1,2,3,4};
        int bytes = Convert.bytesToInt(bytesToIntStr);
        System.out.println(bytes);

        //number转换Chinese
        String Chinese = Convert.numberToChinese(26,false);
        System.out.println(Chinese);

        //number转换word
        String word = Convert.numberToWord(18);
        System.out.println(word);
    }

    @Test
    public void testDateUtil() {

        //Date、long、Calendar之间的相互转换
        //当前时间
        Date date = DateUtil.date();
        System.out.println(DateUtil.date());

        //Calendar转Date
        System.out.println(DateUtil.date(Calendar.getInstance()));

        //时间戳转Date
        System.out.println(DateUtil.date(System.currentTimeMillis()));

        //自动识别格式转换
        String dateStr = "2017-03-01";
        System.out.println(DateUtil.parse(dateStr));

        //自定义格式化转换
        System.out.println(DateUtil.parse(dateStr, "yyyy-MM-dd"));

        //格式化输出日期
        System.out.println(DateUtil.format(date, "yyyy-MM-dd"));

        //获得年的部分
        System.out.println(DateUtil.year(date));

        //获得月份，从0开始计数
        System.out.println(DateUtil.month(date));

        //获取某天的开始、结束时间
        System.out.println(DateUtil.beginOfDay(date));
        System.out.println(DateUtil.endOfDay(date));

        //计算偏移后的日期时间
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        System.out.println(DateUtil.offset(date, DateField.DAY_OF_MONTH, 2));
        //计算日期时间之间的偏移量
        System.out.println(DateUtil.between(date, newDate, DateUnit.DAY));

        // 获得指定日期所属季度，从1开始计数
        System.out.println(DateTime.of(date).quarter());

        // 获得指定日期所属季度
        System.out.println(DateTime.of(date).quarterEnum());

        // 通过生日计算星座
        System.out.println(Zodiac.getZodiac(5,2));

        // 计算生肖，只计算1900年后出生的人
        System.out.println(Zodiac.getChineseZodiac(2002));

        // 是否闰年
        System.out.println(new GregorianCalendar().isLeapYear(2023));

        // 获得指定年份的总天数
        System.out.println(Year.of(2023).length());
    }

    @Test
    public void testHttpUtilAndJsonUtil() {

        // 远程调用接口
        String response = HttpUtil.get("http://localhost:8010/api/v1/user/list");
        System.out.println("response: " + response);

        // 将JSON串转为Bean
        Result result = JSONUtil.toBean(response, Result.class);
        System.out.println("code: " + result.getCode());
        System.out.println("message: " + result.getMessage());
        System.out.println("data: " + result.getData());

        // 将JSONArray对象转为List列表
        List<User> users = JSONUtil.toList(new JSONArray(result.getData()), User.class);
        users.forEach(System.out::println);

        // 准备一个Entity实体
        User user = new User();
        user.setUserId(1);
        user.setRealName("realName");
        user.setCreateTime(new Date());

        // Json解析配置
        JSONConfig jsonConfig = new JSONConfig();
        jsonConfig.setDateFormat("yyyy-MM-dd hh:mm:ss");

        // 将Bean转为JSON串
        System.out.println(JSONUtil.parse(user, jsonConfig).toString());

    }
}
