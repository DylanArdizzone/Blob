import java.util.*;
import java.io.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Blob {
	//static HashMap<String, String> blobs = new HashMap<String, String>();
	static String original;
	static String sha; 
	private static String changeSha(String input)
	{
		try {
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public Blob(String fileName) throws IOException{
		Scanner in = new Scanner(new File(fileName));
		String content= in.nextLine();
		sha = changeSha(content);
		original = content;
		
	}
	public static String getSha() {
		return sha;
	}
}
//Java program to calculate SHA-1 hash value
// creating a new file is not new File. tell it where to look "Path" class and write to that path
/*
 * Paht filePathToWrite = Paths.get(fileName);
 * try{
 * 		
 */

