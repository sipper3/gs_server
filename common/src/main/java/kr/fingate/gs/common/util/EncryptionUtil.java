package kr.fingate.gs.common.util;

import jakarta.annotation.PostConstruct;
import kr.fingate.gs.common.h2.service.H2Service;
import kr.fingate.gs.common.vo.AesKeyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
@Service
@RequiredArgsConstructor
public class EncryptionUtil {

	final H2Service h2Service;

	@PostConstruct
	private void initialize() throws Exception {
		h2Service.createH2Aeskey();

		AesKeyVO aesKeyVO = h2Service.getH2AesKey();
		KEY = aesKeyVO.getAkey();
		IV = aesKeyVO.getIv();
	}

	private static String ALG = "AES/CBC/PKCS5Padding";
	private static String KEY = "";
	private static String IV = "";


	public static String aesEncode(String str) throws Exception {
		return EncryptionUtil.aesEncode(str, EncryptionUtil.KEY);
	}

	public static String aesDecode(String str) throws Exception {
		return EncryptionUtil.aesDecode(str, EncryptionUtil.IV);
	}

	public static String aesEncode(String str, String key) throws Exception {
		if(StringUtils.isEmpty(str)) return "";

		byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);

		String aes256iv = EncryptionUtil.IV.substring(0, 16);
		byte[] aes256IvBytes = aes256iv.getBytes(StandardCharsets.UTF_8);


		String aes256key = EncryptionUtil.KEY.substring(0, 32);
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

		String aes256key = EncryptionUtil.KEY.substring(0, 32);
		byte[] aes256KeyBytes = aes256key.getBytes(StandardCharsets.UTF_8);


		AlgorithmParameterSpec ivSpec = new IvParameterSpec(aes256IvBytes);
		SecretKeySpec keySpec = new SecretKeySpec(aes256KeyBytes, "AES");

		Cipher cipher = Cipher.getInstance(EncryptionUtil.ALG);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
	}

}