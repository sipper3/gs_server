package kr.fingate.gs.core.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
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

	public static String aes256Encode(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(StringUtils.isEmpty(str)) return "";

		String aes256key = key;
		if (key.length() > 32){
			aes256key = key.substring(0, 32);
		}

		byte[] textBytes = str.getBytes("UTF-8");

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);
		SecretKeySpec newKey = new SecretKeySpec(aes256key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

		String enStr = new String(Base64.encodeBase64(cipher.doFinal(textBytes)));
		return EncryptionUtil.charEnMapping(enStr);
	}



	public static String aes256Decode(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		if(StringUtils.isEmpty(str)) return "";
		str = EncryptionUtil.charDeMapping(str);

		String aes256key = key;
		if (key.length() > 32){
			aes256key = key.substring(0, 32);
		}

		byte[] textBytes = Base64.decodeBase64(str.getBytes());

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);

		SecretKeySpec newKey = new SecretKeySpec(aes256key.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);

		String deStr = new String(cipher.doFinal(textBytes), "UTF-8");
		return deStr;
	}

	public static String charEnMapping(String enStr) {

		enStr = enStr.replaceAll("[/]", "_slash_");
		enStr = enStr.replaceAll("[+]", "_plus_");
		enStr = enStr.replaceAll("[:]", "_col_");
		enStr = enStr.replaceAll("[;]", "_smcol_");
		enStr = enStr.replaceAll("[&]", "_amper_");
		enStr = enStr.replaceAll("[$]", "_dol_");
		enStr = enStr.replaceAll("[']", "_grave_");
		enStr = enStr.replaceAll("[~]", "_tilde_");
		enStr = enStr.replaceAll("[!]", "_exmark_");
		enStr = enStr.replaceAll("[@]", "_at_");
		enStr = enStr.replaceAll("[#]", "_sharp_");
		enStr = enStr.replaceAll("[%]", "_per_");
		enStr = enStr.replaceAll("[*]", "_ast_");
		enStr = enStr.replaceAll("[\"]", "_quot_");
		enStr = enStr.replaceAll("[']", "_apost_");
		enStr = enStr.replaceAll("[?]", "_quest_");
		enStr = enStr.replaceAll("[-]", "_min_");
		enStr = enStr.replaceAll("[=]", "_eq_");

		return enStr;
	}

	public static String charDeMapping(String enStr) {

		enStr = enStr.replaceAll("_slash_", "/");
		enStr = enStr.replaceAll("_plus_", "+");
		enStr = enStr.replaceAll("_col_", ":");
		enStr = enStr.replaceAll("_smcol_", ";");
		enStr = enStr.replaceAll("_amper_", "&");
		enStr = enStr.replaceAll("_dol_", "$");
		enStr = enStr.replaceAll("_grave_", "'");
		enStr = enStr.replaceAll("_tilde_", "~");
		enStr = enStr.replaceAll("_exmark_", "!");
		enStr = enStr.replaceAll("_at_", "@");
		enStr = enStr.replaceAll("_sharp_", "#");
		enStr = enStr.replaceAll("_per_", "%");
		enStr = enStr.replaceAll("_ast_", "*");
		enStr = enStr.replaceAll("_quot_", "\"");
		enStr = enStr.replaceAll("_apost_", "'");
		enStr = enStr.replaceAll("_quest_", "?");
		enStr = enStr.replaceAll("_min_", "-");
		enStr = enStr.replaceAll("_eq_", "=");

		return enStr;
	}


}