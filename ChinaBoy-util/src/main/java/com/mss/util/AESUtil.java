package com.mss.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * AES加密工具
 * 
 * @author zt
 * @version 20151123
 *
 */
public class AESUtil {
	
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String ENCODING = "UTF-8";
	private static final String IV = "JSNXSJYHJSNXSJYH";

	/**
	 * 初始化
	 * @return
	 */
	public static byte[] initSecretKey() {
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
		kg.init(128);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 生成加密key
	 * @param key
	 * @return
	 */
	private static Key toKey(byte[] key) {
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	public static String encrypt(String data, Key key) throws Exception {
		return Base64Util.encode(encrypt(data, key, DEFAULT_CIPHER_ALGORITHM));
	}

	public static String encrypt(String data, String key) throws Exception {
		return Base64Util.encode(encrypt(data, key, DEFAULT_CIPHER_ALGORITHM));
	}

	private static byte[] encrypt(String data, String key, String cipherAlgorithm)
			throws Exception {
		Key k = toKey(key.getBytes(ENCODING));
		return encrypt(data, k, cipherAlgorithm);
	}

	private static byte[] encrypt(String data, Key key, String cipherAlgorithm)
			throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(ENCODING)));
		return cipher.doFinal(data.getBytes(ENCODING));
	}

	public static String decrypt(String data, String key) throws Exception {
		data = data.replace(" ", "+");
		return new String(decrypt(Base64Util.decode(data), key, DEFAULT_CIPHER_ALGORITHM),ENCODING);
	}

	public static String decrypt(String data, Key key) throws Exception {
		data = data.replace(" ", "+");
		return new String(decrypt(Base64Util.decode(data), key, DEFAULT_CIPHER_ALGORITHM),ENCODING);
	}

	private static byte[] decrypt(byte[] data, String key, String cipherAlgorithm)
			throws Exception {
		Key k = toKey(key.getBytes(ENCODING));
		return decrypt(data, k, cipherAlgorithm);
	}

	private static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(ENCODING)));
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String data = "1000000000|001|03|20150923102300||20150928|20151128";
		String key = "UTh6Ia7BiZuLQ7WI";
		String sign = encrypt(data,key);
		System.out.println("签名：" + sign);
		System.out.println(decrypt(sign,key));
	}
	
	
}
