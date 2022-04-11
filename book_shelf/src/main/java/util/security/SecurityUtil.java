package util.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import settings.SecuritySettings;

public class SecurityUtil {
	
	public static String generateToken() {
		byte token[] = new byte[SecuritySettings.TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;
		
		try {
			random = SecureRandom.getInstance(SecuritySettings.ENCRYPTION_ALGORITHM);
			random.nextBytes(token);
			
			for(int i=0; i < token.length; i++) {
				buf.append(String.format("%02x", token[i]));
			}
			
			return buf.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
