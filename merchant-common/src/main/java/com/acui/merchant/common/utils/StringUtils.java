package com.acui.merchant.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

import com.alibaba.fastjson.JSON;

/**
 * 字符串工具类，继承lang3字符串工具类
 * 
 * @author Javen 2016年4月3日
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils { 
	/**
	 * 过滤emoji
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern
					.compile(
							"[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
							Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("*");
				return source;
			}
			return source;
		}
		return source;
	}

	/**
	 * 编解码
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		String encode = null;
		try {
			encode = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode;
	}

	public static String decode(String str) {
		String decode = null;
		try {
			decode = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

	/**
	 * 获取UUID，去掉`-`的
	 * 
	 * @return uuid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 将字符串中特定模式的字符转换成map中对应的值
	 * 
	 * use: format("my name is ${name}, and i like ${like}!", {"name":"L.cm",
	 * "like": "Java"})
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @param map
	 *            转换所需的键值对集合
	 * @return 转换后的字符串
	 */
	public static String format(String s, Map<String, String> map) {
		StringBuilder sb = new StringBuilder((int) (s.length() * 1.5));
		int cursor = 0;
		for (int start, end; (start = s.indexOf("${", cursor)) != -1
				&& (end = s.indexOf('}', start)) != -1;) {
			sb.append(s.substring(cursor, start));
			String key = s.substring(start + 2, end);
			sb.append(map.get(StringUtils.trim(key)));
			cursor = end + 1;
		}
		sb.append(s.substring(cursor, s.length()));
		return sb.toString();
	}

	/**
	 * 字符串格式化
	 * 
	 * use: format("my name is {0}, and i like {1}!", "L.cm", "java")
	 * 
	 * int long use {0,number,#}
	 * 
	 * @param s
	 * @param args
	 * @return 转换后的字符串
	 */
	public static String format(String s, Object... args) {
		return MessageFormat.format(s, args);
	}

	/**
	 * 替换某个字符
	 * 
	 * @param str
	 * @param regex
	 * @param args
	 * @return
	 */
	public static String replace(String str, String regex, String... args) {
		int length = args.length;
		for (int i = 0; i < length; i++) {
			str = str.replaceFirst(regex, args[i]);
		}
		return str;
	}

	/**
	 * 转义HTML用于安全过滤
	 * 
	 * @param html
	 * @return
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * 清理字符串，清理出某些不可见字符
	 * 
	 * @param txt
	 * @return {String}
	 */
	public static String cleanChars(String txt) {
		return txt.replaceAll("[ 　	`·•�\\f\\t\\v]", "");
	}

	// 随机字符串
	private static final String _INT = "0123456789";
	private static final String _STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String _ALL = _INT + _STR;

	private static final Random RANDOM = new Random();

	/**
	 * 生成的随机数类型
	 */
	public static enum RandomType {
		INT, STRING, ALL;
	}

	/**
	 * 随机数生成
	 * 
	 * @param count
	 * @return
	 */
	public static String random(int count, RandomType randomType) {
		if (count == 0)
			return "";
		if (count < 0) {
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}
		char[] buffer = new char[count];
		for (int i = 0; i < count; i++) {
			if (randomType.equals(RandomType.INT)) {
				buffer[i] = _INT.charAt(RANDOM.nextInt(_INT.length()));
			} else if (randomType.equals(RandomType.STRING)) {
				buffer[i] = _STR.charAt(RANDOM.nextInt(_STR.length()));
			} else {
				buffer[i] = _ALL.charAt(RANDOM.nextInt(_ALL.length()));
			}
		}
		return new String(buffer);
	}

	/**
	 * 元转换成分
	 *
	 * @param amount
	 * @return
	 */
	public static String getMoney(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	public static int getSecondsToNextDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d2 = new Date();
		LocalDate ld = LocalDate.now();
		try {
			Date d = sdf.parse(ld.getYear() + "-" + ld.getMonthValue() + "-"
					+ ld.getDayOfMonth() + " 23:59:59");
			long val = (d.getTime() - d2.getTime()) / 1000;
			return (int) val;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static Integer getSecondsToNextDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

	/**
	 * 将a=b&c=d转为map
	 * 
	 * @param queryString
	 * @return
	 */
	private static Map<String, String[]> parseQueryString(String queryString) {
		Map<String, String[]> map = new HashMap<String, String[]>();

		String[] params = queryString.split("&");

		for (String param : params) {
			String name = param.split("=")[0];
			String[] value = param.split("=")[1].split(",");

			map.put(name, value);
		}
		return map;
	}

	private static Integer judgeAge(int curYear, int curMonth, int curDate, int userYear, int userMonth, int userDate, int cnd) {
		if (curYear - userYear > cnd) {
			return 1;
		} else if (curYear - userYear == cnd) {
			if (curMonth - userMonth > 0) {
				return 1;
			} else if (curMonth - userMonth == 0) {
				if (curDate - userDate >= 0) {
					return 1;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static String spilt(String str) {
		StringBuffer sb = new StringBuffer();
		String[] temp = str.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (!"".equals(temp[i]) && temp[i] != null)
				sb.append("'" + temp[i] + "',");
		}
		String result = sb.toString();
		String tp = result.substring(result.length() - 1, result.length());
		if (",".equals(tp))
			return result.substring(0, result.length() - 1);
		else
			return result;
	}
	
	 /***
     * @User：Wind
     * @Date： 2018-03-23 14:21
     * @Params： [params]
     * @Return：java.lang.String
     * @Content： ASCII 参数排序
     */
    public static String asciiSorting(Map<String, String> params) {

        Map<String, String> sortMap = new TreeMap<String, String>();
        sortMap.putAll(params);
        // 以k1=v1&k2=v2...方式拼接参数
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> s : sortMap.entrySet()) {
//            String k = s.getKey();
            String v = s.getValue();
            if (StringUtils.isBlank(v)) {// 过滤空值
                continue;
            }
            builder.append(v);
        }
        builder.append("Nikok1UR1Hbq1EdtBakNCYJI2FwJsWT3");
        return builder.toString();
    }
	
    /**
	 * cmd:"new", "bind"
	 * 如果cmd=new,共有两个参数，第二个参数为userId
	 * 如果cmd=bind,三个参数，第三个参数为merchantId
	 * @auther: gp
	 * create time: 2018年4月18日
	 * @param params
	 */
	public static String createOperateJson(String... params){
		Map<String, Object> rm = new HashMap<>();
		rm.put("cmd", params[0]);
		Map<String, String> rm2 = new HashMap<>();
		rm2.put("uid", params[1]);
		if(params.length == 3){
			rm2.put("mid", params[2]);
		}
		rm.put("param", rm2);
		return JSON.toJSONString(rm);
	}
	
	public static String changeF2Y(String amount){    
	    return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();    
	} 
	
	
	/**
	 * 获取当前时间
	 *  yyyyMMddHHmmss
	 * @return String
	 */
	public static String getCurrTime(String type) {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat(type);
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 获取昨天时间
	 * "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getYesterdayCurrTime(String  type) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		SimpleDateFormat sim = new SimpleDateFormat(type);
		String dateStr = sim.format(date);
		return dateStr;
	}
}
