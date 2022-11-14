package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encryption {
	public String generateSalt() {
		Random rand = new SecureRandom();
		byte[] saltArr = new byte[15];
		rand.nextBytes(saltArr);
		return Base64.getEncoder().encodeToString(saltArr);
	}

	public String generateEncryptedPassword(String password, String salt) {
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 10000, 256);
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] result = keyFactory.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error encrypting password: " + e.getMessage());
		} finally {
			spec.clearPassword();
		}
	}

	public boolean verifyPassword(String encryptedPassword, String salt, String testPassword) {
		String submittedPassword = generateEncryptedPassword(testPassword, salt);
		return submittedPassword.equals(encryptedPassword);
	}
}
}
