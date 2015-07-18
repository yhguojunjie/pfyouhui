package com.yoxi.pfhudongtui.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 加密工具类
 * 
 * 
 */
@SuppressWarnings("restriction")
public class EncryptUtils {
	// 向量
	private final static String iv = "01234567";
	// aes向量必须16位
	private static final String VIPARA = "0102030405060708";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * AES加密输入流
	 * 
	 * @param in
	 *            输入流
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encryptByAES(InputStream in, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key,
					new IvParameterSpec(VIPARA.getBytes()));
			;// 初始化
			CipherInputStream cin = new CipherInputStream(in, cipher);
			byte[] result = inputStreamToByte(cin);// cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES加密字节数组
	 * 
	 * @param byteContent
	 *            字节数组
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encryptByAES(byte[] byteContent, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key,
					new IvParameterSpec(VIPARA.getBytes()));
			// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES加密字符串
	 * 
	 * @param content
	 *            加密字符串
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encryptByAES(String content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key,
					new IvParameterSpec(VIPARA.getBytes()));
			// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Base64(AES加密字符串)
	 * 
	 * @param content
	 *            加密字符串
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encryptBase64AES(String content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key,
					new IvParameterSpec(VIPARA.getBytes()));
			// 初始化
			byte[] result = cipher.doFinal(byteContent);
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decryptByAES(byte[] content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key,
					new IvParameterSpec(VIPARA.getBytes()));
			// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对字符串加密(32位)
	 * 
	 * @param str
	 * @return
	 */
	public static final String encodeByMd5(String str) {
		if (str == null) {
			return "";
		} else {
			String newstr = "";
			MessageDigest md5;
			try {
				md5 = MessageDigest.getInstance("MD5");
				md5.update(str.getBytes("utf-8"));
				byte resultData[] = md5.digest();
				newstr = convertToHexString(resultData);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return newstr;
		}
	}

	/**
	 * 对字符串加密(32位)
	 * 
	 * @param str
	 * @return
	 */
	public static final String encryptBase64MD5(String str) {
		if (str == null) {
			return "";
		} else {
			String newstr = "";
			MessageDigest md5;
			try {
				md5 = MessageDigest.getInstance("MD5");
				BASE64Encoder base64en = new BASE64Encoder();
				// 加密后的字符串
				newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return newstr;
		}
	}

	/**
	 * 3DES加密后Base64
	 * 
	 * @param source
	 * @param key
	 * @return
	 */
	public static String encryptBase643DES(String source, String key) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(source.getBytes(encoding));
			return Base64Util.encodeB64(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Base643DES解密
	 * 
	 * @param source
	 * @param key
	 * @return
	 */
	public static String decryptBase643DES(String source, String key) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

			byte[] decryptData = cipher.doFinal(new BASE64Decoder()
					.decodeBuffer(source));

			return new String(decryptData, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字节数组转为十六进制形式字符串  byte[]转String
	 * 
	 * @param data
	 * @return
	 */
	public static String convertToHexString(byte data[]) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			if ((0xff & data[i]) < 0x10) {
				strBuffer.append("0" + Integer.toHexString((0xFF & data[i])));
			} else {
				strBuffer.append(Integer.toHexString(0xFF & data[i]));
			}
		}
		return strBuffer.toString();
	}

	/**
	 * 流转byte[]
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte data[] = bytestream.toByteArray();
		bytestream.close();
		return data;
	}

	/**
	 * 对byte数组base64加密
	 * 
	 * @param b
	 * @return
	 */
	public static String base64Encode(byte b[]) {
		try {
			return (new BASE64Encoder()).encode(b);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

		System.out
				.println("xxx"
						+ encodeByMd5("100000001510000000051100000000510000000151.050.0.1zxzfV0.0.1secretKey12345"));

		// md5
		String md5 = EncryptUtils
				.encodeByMd5("100000001510000000051100000000510000000151.050.0.1zxzfV0.0.1secretKey12345");
		System.out.println(md5);// e10adc3949ba59abbe56e057f20f883e
		// 3DES
		String encryByDES = EncryptUtils.encryptBase643DES(
				"1.010000000011000000001", "lereaderV1.0secretkey123");// 24
		System.out.println("digest:" + encryByDES);// digest:4kUGCk1X8zAZsoKe5oSvUfiaDcC3gK6H
		String decryByDES = EncryptUtils.decryptBase643DES(encryByDES,
				"lereaderV1.0secretkey123");
		System.out.println(decryByDES);
		// REK
		String rek = EncryptUtils.encodeByMd5("1.010000000011000000001"
				+ "lereaderV1.0secretkey123");
		System.out.println("rek:" + rek);// rek:263edb7c516354a7b756deb74eb37d30
		// secretKey
		String byteArr = EncryptUtils.encryptBase64AES("b8403b65f815",
				"263edb7c516354a7");
		System.out.println("byteArr:" + byteArr);// ceiUxvcjxJI2IhI3Ez2XVQ==
		String secretKey = new String(EncryptUtils.decryptByAES(
				Base64.decode(byteArr), "263edb7c516354a7"), "utf-8");
		System.out.println("secretKey:" + secretKey);
		// b8403b65f815
		String key = "lereaderV1.0secretkey123";
		String encryString = EncryptUtils.encryptBase643DES(
				"110000000011000000108", key);
		System.out.println("encry== " + encryString);
		System.out.println(EncryptUtils.decryptBase643DES(encryString, key));

		String test = new String(
				EncryptUtils.decryptByAES(
						Base64.decode(("RmJrn05x9GwG2QOMYSLkLlipe1r89ceYlkpoysumOB0=")),
						"497832882ae67824"), "utf-8");

		System.out.println(test + "---111");
		String encryString1 = EncryptUtils.encryptBase643DES(
				"1000000001210000000011.002.0.0lereaderV1.0secretkey123", key);
		System.out.println("encryString1== " + encryString1);

		// String md5 = EncryptUtils.encodeByMd5("353440050707737");
		// System.out.println(md5);
	}
}
