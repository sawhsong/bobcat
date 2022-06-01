package zebra.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class CryptoUtil {
	private static final String encoding = "utf-8";
	private static final byte[] h = { 1, 35, 69, 103, -119, -85, -51, -17 };
	private static final String ALGO = "AES"; // Default uses ECB PKCS5Padding

	public static final String encrypt(String s, String s1) throws CryptoException {
		if ((s1 == null) || (s == null)) {
			throw new CryptoException("string : null or keystring : null in encrypt(String, String)");
		}

		CipherParameters cipherparameters = cipher(s);
		return encrypt(cipherparameters, s1);
	}

	private static String encrypt(CipherParameters cipherparameters, String s) throws CryptoException {
		byte[] abyte0;

		if ((s == null) || (cipherparameters == null)) {
			throw new CryptoException("string : null or keystring : null in encrypt(CipherParameters, String)");
		}
		try {
			abyte0 = ("^" + s + "$").getBytes(encoding);
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			throw new CryptoException("Unsupported utf-8", unsupportedencodingexception);
		}

		PaddedBufferedBlockCipher paddedbufferedblockcipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESEngine()));
		paddedbufferedblockcipher.init(true, cipherparameters);
		byte[] abyte1 = new byte[paddedbufferedblockcipher.getOutputSize(abyte0.length)];
		try {
			int i1 = paddedbufferedblockcipher.processBytes(abyte0, 0, abyte0.length, abyte1, 0);
			paddedbufferedblockcipher.doFinal(abyte1, i1);
		} catch (Exception exception) {
			throw new CryptoException("Encrypt Error", exception);
		}
		return URL64.encode(abyte1);
	}

	public static final String decrypt(String s, String s1) throws CryptoException {
		if ((s1 == null) || (s == null)) {
			throw new CryptoException("string : null or keystring : null in decrypt(String, String)");
		}

		CipherParameters cipherparameters = cipher(s);
		return decrypt(cipherparameters, s1);
	}

	private static String decrypt(CipherParameters cipherparameters, String s) throws CryptoException {
		if ((s == null) || (cipherparameters == null))
			throw new CryptoException("string : null or keystring : null in decrypt(CipherParameters, String)");
		PaddedBufferedBlockCipher paddedbufferedblockcipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESEngine()));
		byte[] abyte0 = URL64.decode(s);
		if (abyte0 == null)
			throw new CryptoException("URL64 uncompatiable in decrypt()");
		paddedbufferedblockcipher.init(false, cipherparameters);
		byte[] abyte1 = new byte[paddedbufferedblockcipher.getOutputSize(abyte0.length)];
		int i1 = 0;
		int j1 = 0;
		try {
			i1 = paddedbufferedblockcipher.processBytes(abyte0, 0, abyte0.length, abyte1, 0);
			j1 = paddedbufferedblockcipher.doFinal(abyte1, i1);
		} catch (Exception exception) {
			throw new CryptoException("Decrypt Error ", exception);
		}
		if ((abyte1[0] == 94) && (abyte1[(i1 + j1 - 1)] == 36)) {
			try {
				return new String(abyte1, 1, i1 + j1 - 2, encoding);
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
				throw new CryptoException("Unsupported utf-8", unsupportedencodingexception);
			}
		}
		return null;
	}

	private static CipherParameters cipher(String s) throws CryptoException {
		byte[] abyte0;
		byte[] abyte1 = new byte[8];

		try {
			abyte0 = s.getBytes(encoding);
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			throw new CryptoException("Unsupported utf-8", unsupportedencodingexception);
		}

		int i1 = abyte0.length > 8 ? 8 : abyte0.length;
		for (int j1 = 0; j1 < 8; j1++) {
			if (j1 < i1)
				abyte1[j1] = abyte0[j1];
			else
				abyte1[j1] = 0;
		}
		return new ParametersWithIV(new KeyParameter(abyte1), h);
	}

	public static final String XOREncrypt(String key, String value) {
		String result = "";
		char gubun = '\b';
		char[] keys = new char[key.length()];
		char[] values = new char[value.length()];
		key.getChars(0, key.length(), keys, 0);
		value.getChars(0, value.length(), values, 0);
		int keyCount = 0;
		for (int i = 0; i < values.length; i++) {
			result = result + (values[i] ^ keys[keyCount]);
			result = result + gubun;
			if (keyCount == keys.length - 1)
				keyCount = 0;
			else {
				keyCount++;
			}
		}
		try {
			result = URLEncoder.encode(result, "utf-8");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return result;
	}

	public static final String XORDecrypt(String key, String value) {
		String result = "";
		char[] gubun = { '\b' };

		char[] keys = new char[key.length()];
		try {
			value = URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		key.getChars(0, key.length(), keys, 0);
		String[] values = value.split(new String(gubun));
		int keyCount = 0;
		for (int i = 0; i < values.length; i++) {
			result = result + (char) (Integer.parseInt(values[i]) ^ keys[keyCount]);
			if (keyCount == keys.length - 1)
				keyCount = 0;
			else {
				keyCount++;
			}
		}
		return result;
	}

	/*!
	 * javascript <-> java enc / dec begin
	 */
	public static String encryptInput(String data, String secret) throws Exception {
		Key key = generateKey(secret);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	public static String decryptInput(String strToDecrypt, String secret) {
		try {
			Key key = generateKey(secret);
			Cipher cipher = Cipher.getInstance(ALGO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private static Key generateKey(String secret) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
		Key key = new SecretKeySpec(decoded, ALGO);
		return key;
	}

	public static String decodeKey(String str) {
		byte[] decoded = Base64.getDecoder().decode(str.getBytes());
		return new String(decoded);
	}

	public static String encodeKey(String str) {
		byte[] encoded = Base64.getEncoder().encode(str.getBytes());
		return new String(encoded);
	}
	/*!
	 * javascript <-> java enc / dec end
	 */
}