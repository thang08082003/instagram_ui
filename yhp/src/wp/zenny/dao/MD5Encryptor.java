package wp.zenny.dao;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor {
	public String encrypt(String pwd) {
		String result = "";
		try {
			MessageDigest msd = MessageDigest.getInstance("MD5");
			byte[] data = pwd.getBytes("UTF-8");
			byte[] resultData = msd.digest(data);
			BigInteger bi = new BigInteger(1, resultData);
			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(UnsupportedEncodingException ue) {
			ue.printStackTrace();
		}
		
		return result;
	}
}
