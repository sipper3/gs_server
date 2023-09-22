package kr.fingate.gs.core.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

/**
 * <pre>
 * 암호화 utility class
 * 암호화, 복호화 처리
 * </pre>
 *
 */

public class EncryptionUtil {

	public static byte[] aes256IvBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; //aes256 암호화
	private static final int SALT_SIZE = 16;

	public static String aesParamsEncode(String str, String key) throws Exception {
		if(StringUtils.isEmpty(str)) return "";

		byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);

		SecretKeySpec newKey = new SecretKeySpec(EncryptionUtil.sha256(key), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

		return DatatypeConverter.printHexBinary(cipher.doFinal(textBytes));
	}

	public static String aesParamsDecode(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, DecoderException {
		if(StringUtils.isEmpty(str)) return "";

		char[] toCharArray = str.toCharArray();
		byte[] textBytes = Hex.decodeHex(toCharArray);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);
		SecretKeySpec newKey = new SecretKeySpec(EncryptionUtil.sha256(key), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);

		return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
	}

	public static byte[] sha256(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());
		return md.digest();
	}
	public static String password(String str, String key) throws NoSuchAlgorithmException {
		if(StringUtils.isEmpty(str)) return null;

		String preKey = key.substring(0, 3);
		String tailKey = key.substring(3, 3);
		String temp = preKey + str + tailKey + key;

		return byteToString(EncryptionUtil.sha256(temp));


	}

	private static String byteToString(byte[] temp) {
		StringBuilder sb = new StringBuilder();

		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}

		return sb.toString();
	}

	public static String getSalt() {
		SecureRandom sr = new SecureRandom();

		byte[] salt = new byte[SALT_SIZE];

		sr.nextBytes(salt);
		return byteToString(salt);
	}

	public static String getRandomNumberKey(int size) {
		StringBuilder rtnKey = new StringBuilder();
		Random random = new Random(); // 랜덤 객체 생성

		// 난수 생성
		for(int i = 0; i < size; i++) {
			rtnKey.append(random.nextInt(10));
		}

		return rtnKey.toString();
	}

	public static String getRandomKey(int size) {
		StringBuffer buf =new StringBuffer();
		Random rnd =new Random();

		for(int i=0;i<size;i++){

			if(rnd.nextBoolean()){
				buf.append((char)((int)(rnd.nextInt(26))+97));
			}else{
				buf.append((rnd.nextInt(10)));
			}
		}

		return buf.toString();
	}


	public static void main(String[] args) throws Exception {

		String str = "{\"userNo\":1,\"mertNo\":\"10\",\"chrMethodCode\":\"ABC\"}";
		String key = "01234567890123456789012345678999";
		String encrypted = EncryptionUtil.aesParamsEncode(str, key);
		System.out.println(encrypted);

		System.out.println(EncryptionUtil.aesParamsDecode(encrypted, key));

	}


}