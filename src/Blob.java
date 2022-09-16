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
	public Blob(String fileName) throws IOException,  FileNotFoundException, NoSuchAlgorithmException{
		File originalf = new File(fileName);
		String content = fileContents(fileName, StandardCharsets.US_ASCII);
		String shaHash = changeSha(content);
		sha = shaHash;
		original = content;
		File nf = new File("objects/"+ shaHash + ".txt");
		
		copy(nf, originalf);
		
	}
	
	//from geeksforgeeks
	public static void copy(File a, File b)throws IOException  {
		FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);
  
        try {
  
            int n;
  
            // read() function to read the
            // byte of data
            while ((n = in.read()) != -1) {
                // write() function to write
                // the byte of data
                out.write(n);
            }
        }
        finally {
            if (in != null) {
  
                // close() function to close the
                // stream
                in.close();
            }
            // close() function to close
            // the stream
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }

	public String fileContents(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
	public static String getSha() {
		return sha;
	}
}
