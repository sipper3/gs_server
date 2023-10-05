package kr.fingate.gs.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

/**
 * <pre>
 * 암호화 utility class
 * 암호화, 복호화 처리
 * </pre>
 *
 */

@Slf4j
public class EncryptionUtil {
	private static String ALG = "AES/CBC/PKCS5Padding";
	private static String KEY = "20A3B00A12H397E1228C2233J33D311C";
	private static String IV = "sDaOgZhI4412lkJb";

	public static String aesEncode(String str) throws Exception {
		return EncryptionUtil.aesEncode(str, EncryptionUtil.KEY);
	}

	public static String aesDecode(String str) throws Exception {
		return EncryptionUtil.aesDecode(str, EncryptionUtil.KEY);
	}

	public static String aesEncode(String str, String key) throws Exception {
		if(StringUtils.isEmpty(str)) return "";

		byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);

		String aes256iv = EncryptionUtil.IV.substring(0, 16);
		byte[] aes256IvBytes = aes256iv.getBytes(StandardCharsets.UTF_8);


		String aes256key = key.substring(0, 32);
		byte[] aes256KeyBytes = aes256key.getBytes(StandardCharsets.UTF_8);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);
		SecretKeySpec keySpec = new SecretKeySpec(aes256KeyBytes, "AES");

		Cipher cipher = Cipher.getInstance(EncryptionUtil.ALG);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		return new String(Base64.encodeBase64(cipher.doFinal(textBytes)));

	}

	public static String aesDecode(String str, String key) throws Exception {

		if(StringUtils.isEmpty(str)) return "";

		byte[] textBytes = Base64.decodeBase64(str.getBytes());

		String aes256iv = EncryptionUtil.IV.substring(0, 16);
		byte[] aes256IvBytes = aes256iv.getBytes(StandardCharsets.UTF_8);

		String aes256key = key.substring(0, 32);
		byte[] aes256KeyBytes = aes256key.getBytes(StandardCharsets.UTF_8);


		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);
		SecretKeySpec keySpec = new SecretKeySpec(aes256KeyBytes, "AES");

		Cipher cipher = Cipher.getInstance(EncryptionUtil.ALG);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
	}

//	public static void main(String[] args) {
//		try {
//			log.info(EncryptionUtil.aesEncode("TEST1123232323223232323"));
//			log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			log.info(EncryptionUtil.aesDecode("G85W4XefOqKNt2CwbGTHpi+x5p2Io29ttOBzC+26dlM="));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}