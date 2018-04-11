package com.ithc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
@SuppressWarnings("all")
public class Base64 {

	public static void main(String[] args) {
		//加密
		String encode = getEncode("456");//TVRJeg==
		System.out.println(encode);
		//把加密出来的值解密
		String decode = getDecode(encode);
		System.out.println(decode);
	}

	// 加密
	public static String getEncode(String base64e) {
		try {
			byte[] b = base64e.getBytes("utf-8");
			return new BASE64Encoder().encode(b).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 解密
	public static String getDecode(String base64d) {
		String value = "";
		try {
			byte[] b = new BASE64Decoder().decodeBuffer(base64d);
			value = new String(b, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
