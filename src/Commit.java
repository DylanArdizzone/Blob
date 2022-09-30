import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Commit {
	private static Commit parent;
	private static Commit child;
	private static String author;
	private static String summary;
	private static String date;
	private static String filename;
	private String absoluteFileName;
	private static Tree rTree;
	
	public Commit(String summ, String a, Commit pointer) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		summary = summ;
		author = a;
		parent = null;
		child = null;
		rTree = new Tree();
		ArrayList<String> k = new ArrayList<String>();
		File f = new File("index");
		BufferedReader fr = new BufferedReader(new FileReader(f));
		while(fr.ready()) {
			String s = fr.readLine();
			k.add("blob : " + s.substring(s.indexOf(":")+2) +" " + s.substring(0,s.indexOf(":")));
		}
		fr.close();
		
		
		if(pointer == null)
			parent= null;
		else {
			parent = pointer;
			File lk = new File("objects/" + pointer.getFileName());
			BufferedReader br = new BufferedReader(new FileReader(lk));
			String s = br.readLine();
			br.close();
			k.add("tree : " + s);
		} //change the constructer from "String pointer" to "commit pointer" so when setting parent to pointer can set this one as child???
		
		rTree.monkeyAround(k);
		
		child = null;
		date = getDate();
		String temp = summary + date+author+parent;
		temp = sha1Code(temp);
		filename = temp;
		absoluteFileName = temp;
		writeFile(); 
		clearIndex();
		
	}
	
	public void clearIndex() {
		  try{
			    FileWriter fw = new FileWriter("Index", false);
			    PrintWriter pw = new PrintWriter(fw, false);
			    pw.flush();
			    pw.close();
			    fw.close();
			    }catch(Exception exception){
			        System.out.println("Exception have been caught");
			    }
	}
	
	public String getFileName() {
		return absoluteFileName;
	}
	public void setChild(Commit c) throws IOException {
		child = c;
		
		File f = new File("objects/"+absoluteFileName);
		ArrayList<String> bigFile = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while(br.ready()) {
			bigFile.add(br.readLine());
		}
		br.close();
		PrintWriter pw = new PrintWriter(new FileWriter(f));
		for(int i=0; i<bigFile.size(); i++) {
			if(i==2) {
				pw.write(c.getFileName()+"\n");
			}
			else {
				pw.write(bigFile.get(i)+"\n");
			}
		}
		pw.close();
	}
	private static String sha1Code(String str) {
		String value = str;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}

		return sha1;
	}
	
	
	/* THIS IS THE OLD SHA1
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
    }*/
	public static String getDate() {
		// from javatpoint
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		return strDate; 
	}
	public static void writeFile() throws FileNotFoundException {
		String print = "";
		File file = new File(filename);
		PrintWriter out = new PrintWriter("objects/" + file);
		print += rTree.getName() + "\n";
		if(parent == null) {
			print+="\n";
		}else {
			//print+= sha1Code(parent) + "\n";
			print += parent.getFileName()+"\n";
		}
		
		if(child == null) {
			print+="\n";
		}else {
			//print+=sha1Code(child) +"\n";
			print+=child.getFileName();
		}
		out.append(print + author  + "\n" + date  + "\n" + summary + "\n");
		out.close();
		
		/*
		 * Every time you make a commit with a parent, that parent gets a child
		 * When the child is made, it has its own location (parent already exists)
		 * Using the files that you wrote to (in test/object) change the information on the files
		 * for example for the parent, in the spot where its supposed to have child (is null) -> child name 
		 * for child, regular parent function which you already have 
		 */
	}
	
}
