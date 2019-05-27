package com.beijia.example.util;

import com.alibaba.druid.util.StringUtils;

/**
 * 类名: StringUtil <br/>
 * 说明: 字符串处理工具 <br/>
 * 修改时间: 2017年9月18日 <br/>
 * 
 * @author 北佳软件
 * @version
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * 
	 * @param valStr
	 *            需转换的字符串
	 * @return String[] 字符串数组
	 */
	public static String[] StrList(String valStr) {
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0) {
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

			i++;
		}
		return returnStr;
	}

	/**
	 * 获取字符串编码
	 * 
	 * @param str
	 *            需获取的字符串
	 * @return 字符串编码
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 过滤特殊字符 <br/>
	 * 
	 * @param content
	 *            要过滤的内容
	 * @return 过滤后的内容
	 */
	public static String filtrateString(String content) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\t", "    ");
		content = content.replaceAll("\r\n", "\n");
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("'", "&#39;");
		content = content.replaceAll("\\\\", "&#92;");
		content = content.replaceAll("\\(", "&#40;");
		content = content.replaceAll("\\)", "&#41;");
		content = content.replaceAll("eval\\((.*)\\)", "");
		content = content.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		content = content.replaceAll("script", "");
		/*
		 * content = content.replaceAll("&", "&amp;"); content =
		 * content.replaceAll("\"", "&quot;");
		 */
		return content;
	}

	/**
	 * 特殊字符转文本 <br/>
	 * 
	 * @param content
	 *            要转换的内容
	 * @return 转换后的内容
	 */
	public static String reverseString(String content) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("    ", "\t");
		content = content.replaceAll("\n", "\r\n");
		content = content.replaceAll("<br/>", "\n");
		content = content.replaceAll("&#39;", "'");
		content = content.replaceAll("&#92;", "\\\\");
		content = content.replaceAll("&#40;", "\\(");
		content = content.replaceAll("&#41;", "\\)");
		content = content.replaceAll("＂", "\"");
		/*
		 * content = content.replaceAll("&amp;", "&"); content =
		 * content.replaceAll("&quot;", "\""); content = content.replaceAll("＜",
		 * "<"); content = content.replaceAll("＞", ">");
		 */
		return content;
	}
}
