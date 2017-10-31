package com.test.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/*
	 * SHA-256 암호화
	 * */

public class SHA256Util{
	/* SHA-256 암호화
	  
	  @param salt(String) SALT 값
	  
	*/
	public static String getEncrypt(String source, String salt) throws NoSuchAlgorithmException{
		return getEncrypt(source, salt.getBytes());
	}
	
	/*
	 SHA-256 암호화
	 
	 @param salt(byte[]) SATL 값
	 
	 */
	
	public static String getEncrypt(String source, byte[] salt) throws NoSuchAlgorithmException{
		String result = "";
		
		 MessageDigest sha = MessageDigest.getInstance("SHA-256");
	        sha.update(source.getBytes());
	 
	        byte[] digest = sha.digest();
	        for (int i=0; i<digest.length; i++) {
	        	result += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
	        }


	
		return result;
	}
	/*
	 	SALT를 얻어온다.	 
	 	@return
	*/
	
	public static String generateSalt(){
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<salt.length;i++){
			//byte 값을 hex 갑으로 바꾸기
			sb.append(String.format("%02x", salt[i]));
		}
		return sb.toString();
	}
	
}