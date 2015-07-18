package com.yoxi.pfhudongtui.utils;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Base64Util {

	private static final byte[] encodingTable = { (byte) 'A', (byte) 'B',
			(byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G',
			(byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L',
			(byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q',
			(byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V',
			(byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a',
			(byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f',
			(byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k',
			(byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p',
			(byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u',
			(byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z',
			(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4',
			(byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9',
			(byte) '+', (byte) '/' };

	/**
	 * encode the input data producong a base 64 encoded byte array.
	 * 
	 * @return a byte array containing the base 64 encoded data.
	 */
	public static byte[] encode(byte[] data) {
		byte[] bytes;

		int modulus = data.length % 3;
		if (modulus == 0) {
			bytes = new byte[4 * data.length / 3];
		} else {
			bytes = new byte[4 * ((data.length / 3) + 1)];
		}

		int dataLength = (data.length - modulus);
		int a1, a2, a3;
		for (int i = 0, j = 0; i < dataLength; i += 3, j += 4) {
			a1 = data[i] & 0xff;
			a2 = data[i + 1] & 0xff;
			a3 = data[i + 2] & 0xff;

			bytes[j] = encodingTable[(a1 >>> 2) & 0x3f];
			bytes[j + 1] = encodingTable[((a1 << 4) | (a2 >>> 4)) & 0x3f];
			bytes[j + 2] = encodingTable[((a2 << 2) | (a3 >>> 6)) & 0x3f];
			bytes[j + 3] = encodingTable[a3 & 0x3f];
		}

		/*
		 * process the tail end.
		 */
		int b1, b2, b3;
		int d1, d2;

		switch (modulus) {
		case 0: /* nothing left to do */
			break;
		case 1:
			d1 = data[data.length - 1] & 0xff;
			b1 = (d1 >>> 2) & 0x3f;
			b2 = (d1 << 4) & 0x3f;

			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = (byte) '=';
			bytes[bytes.length - 1] = (byte) '=';
			break;
		case 2:
			d1 = data[data.length - 2] & 0xff;
			d2 = data[data.length - 1] & 0xff;

			b1 = (d1 >>> 2) & 0x3f;
			b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
			b3 = (d2 << 2) & 0x3f;

			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = encodingTable[b3];
			bytes[bytes.length - 1] = (byte) '=';
			break;
		}

		return bytes;
	}

	public static String encode(String data) {
		return new String(encode(data.getBytes()));
	}

	/*
	 * set up the decoding table.
	 */
	private static final byte[] decodingTable;

	static {
		decodingTable = new byte[128];

		for (int i = 0; i < 128; i++) {
			decodingTable[i] = (byte) -1;
		}

		for (int i = 'A'; i <= 'Z'; i++) {
			decodingTable[i] = (byte) (i - 'A');
		}

		for (int i = 'a'; i <= 'z'; i++) {
			decodingTable[i] = (byte) (i - 'a' + 26);
		}

		for (int i = '0'; i <= '9'; i++) {
			decodingTable[i] = (byte) (i - '0' + 52);
		}

		decodingTable['+'] = 62;
		decodingTable['/'] = 63;
	}

	/**
	 * decode the base 64 encoded input data.
	 * 
	 * @return a byte array representing the decoded data.
	 */
	public static byte[] decode(byte[] data) {

		byte[] bytes;
		byte b1, b2, b3, b4;

		data = discardNonBase64Bytes(data);

		if (data[data.length - 2] == '=') {
			bytes = new byte[(((data.length / 4) - 1) * 3) + 1];
		} else if (data[data.length - 1] == '=') {
			bytes = new byte[(((data.length / 4) - 1) * 3) + 2];
		} else {
			bytes = new byte[((data.length / 4) * 3)];
		}

		for (int i = 0, j = 0; i < data.length - 4; i += 4, j += 3) {
			b1 = decodingTable[data[i]];
			b2 = decodingTable[data[i + 1]];
			b3 = decodingTable[data[i + 2]];
			b4 = decodingTable[data[i + 3]];

			bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[j + 2] = (byte) ((b3 << 6) | b4);
		}

		if (data[data.length - 2] == '=') {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];

			bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
		} else if (data[data.length - 1] == '=') {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];
			b3 = decodingTable[data[data.length - 2]];

			bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
		} else {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];
			b3 = decodingTable[data[data.length - 2]];
			b4 = decodingTable[data[data.length - 1]];

			bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
		}

		return bytes;
	}

	/**
	 * decode the base 64 encoded String data.
	 * 
	 * TODO: Use the byte version to avoid duplication?
	 * 
	 * @return a byte array representing the decoded data.
	 */
	public static byte[] decode(String data) {
		byte[] bytes;
		byte b1, b2, b3, b4;

		data = discardNonBase64Chars(data);

		if (data.charAt(data.length() - 2) == '=') {
			bytes = new byte[(((data.length() / 4) - 1) * 3) + 1];
		} else if (data.charAt(data.length() - 1) == '=') {
			bytes = new byte[(((data.length() / 4) - 1) * 3) + 2];
		} else {
			bytes = new byte[((data.length() / 4) * 3)];
		}

		for (int i = 0, j = 0; i < data.length() - 4; i += 4, j += 3) {
			b1 = decodingTable[data.charAt(i)];
			b2 = decodingTable[data.charAt(i + 1)];
			b3 = decodingTable[data.charAt(i + 2)];
			b4 = decodingTable[data.charAt(i + 3)];

			bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[j + 2] = (byte) ((b3 << 6) | b4);
		}

		if (data.charAt(data.length() - 2) == '=') {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];

			bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
		} else if (data.charAt(data.length() - 1) == '=') {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];
			b3 = decodingTable[data.charAt(data.length() - 2)];

			bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
		} else {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];
			b3 = decodingTable[data.charAt(data.length() - 2)];
			b4 = decodingTable[data.charAt(data.length() - 1)];

			bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
		}

		return bytes;
	}

	/**
	 * Decode the string and convert back the decoded value into a string using
	 * the specified charset. Use default encoding if charset is null or
	 * invalid.
	 */
	public static String decode(String data, String charset) {
		if (charset == null) {
			// use default
			return new String(decode(data));
		}

		try {
			return new String(decode(data), charset);

		} catch (UnsupportedEncodingException ex) {
			return new String(decode(data));
		}
	}

	/**
	 * Decode the string and convert back the decoded value into a string using
	 * the specified charset. Use default encoding if charset is null or
	 * invalid.
	 */
	public static String decode(byte[] data, String charset) {
		if (charset == null) {
			// use default
			return new String(decode(data));
		}

		try {
			return new String(decode(data), charset);

		} catch (UnsupportedEncodingException ex) {
			return new String(decode(data));
		}
	}

	// --------------------------------------------------------- Private Methods

	/**
	 * Discards any characters outside of the base64 alphabet (see page 25 of
	 * RFC 2045) "Any characters outside of the base64 alphabet are to be
	 * ignored in base64 encoded data."
	 * 
	 * @param data
	 *            the base64 encoded data
	 * @return the data, less non-base64 characters.
	 */
	private static byte[] discardNonBase64Bytes(byte[] data) {
		byte temp[] = new byte[data.length];
		int bytesCopied = 0;

		for (int i = 0; i < data.length; i++) {
			if (isValidBase64Byte(data[i])) {
				temp[bytesCopied++] = data[i];
			}
		}

		byte newData[] = new byte[bytesCopied];

		System.arraycopy(temp, 0, newData, 0, bytesCopied);

		return newData;
	}

	/**
	 * Discards any characters outside of the base64 alphabet (see page 25 of
	 * RFC 2045) "Any characters outside of the base64 alphabet are to be
	 * ignored in base64 encoded data."
	 * 
	 * @param data
	 *            the base64 encoded data
	 * @return the data, less non-base64 characters.
	 */
	private static String discardNonBase64Chars(String data) {

		StringBuffer sb = new StringBuffer();

		int length = data.length();

		for (int i = 0; i < length; i++) {
			if (isValidBase64Byte((byte) (data.charAt(i)))) {
				sb.append(data.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * Checks is the given byte is in base64 alphabet
	 * 
	 * @param b
	 *            the byte to check
	 * @return boolean true if the byte is in base64 alphabet
	 */
	private static boolean isValidBase64Byte(byte b) {
		if (b == '=') {
			return true;
		} else if (b < 0 || b >= 128) {
			return false;
		} else if (decodingTable[b] == -1) {
			return false;
		}
		return true;
	}
	
	private static final char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' }; 

	/** 
	* 将字节数组编码为字符串 
	* 
	* @param data 
	*/ 
	public static String encodeB64(byte[] data) { 
		StringBuffer sb = new StringBuffer(); 
		int len = data.length; 
		int i = 0; 
		int b1, b2, b3; 
		while (i < len) { 
			b1 = data[i++] & 0xff; 
			if (i == len) { 
				sb.append(base64EncodeChars[b1 >>> 2]); 
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]); 
				sb.append("=="); 
				break; 
			} 
			b2 = data[i++] & 0xff; 
			if (i == len) { 
				sb.append(base64EncodeChars[b1 >>> 2]); 
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]); 
				sb.append("="); 
				break; 
			} 
			b3 = data[i++] & 0xff; 
			sb.append(base64EncodeChars[b1 >>> 2]); 
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]); 
			sb.append(base64EncodeChars[b3 & 0x3f]); 
		} 
		return sb.toString(); 
	} 
	
	/**
	 * 3DES加密后Base64
	 * @param source
	 * @param key
	 * @return
	 */
	public static String encryptBase643DES(String source, String key) {
		try {
			SecretKey deskey = new SecretKeySpec(key.getBytes("utf-8"), "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] resultData = cipher.doFinal(source.getBytes("utf-8"));
			return new String(encode(resultData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Base643DES解密
	 * @param source
	 * @param key
	 * @return
	 */
	public static String decryptBase643DES(String source, String key) {
		try {
			byte[] a = decode(source);
			if(a == null){
				return null;
			}
			SecretKey deskey = new SecretKeySpec(key.getBytes("utf-8"), "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			byte[] resultData = cipher.doFinal(a);
			return new String(resultData, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
