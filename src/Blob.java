import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class Blob {
	//static HashMap<String, String> blobs = new HashMap<String, String>();
	static String original;
	static String sha; 
	/*
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
	*/
	public static String sha1Code(String filePath) throws IOException, NoSuchAlgorithmException, FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
        byte[] bytes = new byte[1024];
        while (digestInputStream.read(bytes) > 0);
        byte[] resultByteArry = digest.digest();
        return bytesToHexString(resultByteArry);
    }
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF;
            if (value < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(value).toUpperCase());
        }
        return sb.toString();
    }
    /*
	public Blob(String fileName) throws IOException,  FileNotFoundException, NoSuchAlgorithmException{
		File originalf = new File(fileName);
		sha = sha1Code(fileName);
		
		File nf = new File("objects/" + sha);
		String content = fileContents(fileName, StandardCharsets.US_ASCII);
		
		original = content;
		
		copyFileUsingStream(nf, originalf);
		
	}
	*/
    
    public Blob(String filename) throws IOException,  FileNotFoundException, NoSuchAlgorithmException{
    	File f = new File(filename);
    	sha = sha1Code (filename);
	
		Scanner in = new Scanner(f);
		PrintWriter out = new PrintWriter (new FileWriter("./objects/"+ sha));
		while(in.hasNextLine()) {
	        String s = in.nextLine();
	        out.write(s);
	    }
		//if(in != null) {
			in.close();  
		//}
		//if(out != null) {
			out.flush();
			out.close();
		//}
    }
    /*
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}

	public String fileContents(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    */
	public static String getSha() {
		return sha;
	}
}
