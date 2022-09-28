import java.util.*; 
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 
public class Tree {
	private String nomen;
//	public static void main (String [] args) throws FileNotFoundException, NoSuchAlgorithmException {
//		File folder = new File ("objects"); 
//		folder.mkdir(); 
//
//		//TEST 1
//		ArrayList<String> list = new ArrayList<String>(); 
//		list.add("f : oo"); 
//		list.add("b : ar"); 
//		list.add("foo : bar"); 
//
//		//TEST 2
//		ArrayList<String> list2 = new ArrayList<String>(); 
////		String s = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f\n"
////				+ "blob : 01d82591292494afd1602d175e165f94992f6f5f\n"
////				+ "blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83\n"
////				+ "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b\n"
////				+ "tree: e7d79898d3342fd15daf6ec36f4cb095b52fd976\n"; 
////		String[] arr = s.split("\n"); 
////		for (int i = 0; i < arr.length; i++) {
////			list2.add(arr[i]); 
////		}
//		ArrayList<String> alisted = new ArrayList<String>();
//		alisted.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		alisted.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		alisted.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		alisted.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		alisted.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		
//
//
//		//METHOD CALLS
//		
//		monkeyAround(list); 
//		monkeyAround(alisted); 
//	}

	public Tree() throws FileNotFoundException {

		

	}

	public void monkeyAround(ArrayList<String> arr) throws FileNotFoundException, NoSuchAlgorithmException {
		String s = ""; 
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i) + "\n"; 
		}
		s = s.substring(0,s.length()-1); 
		String name = GenerateHash(s); //for file name
		nomen = name;
		//System.out.println ("F:" + name); 
		File file = new File ("objects/" + name); 
		PrintWriter pw = new PrintWriter (file); 
		for (int i = 0; i < arr.size(); i++) {
			pw.append(arr.get(i)); 
			pw.append("\n"); 
		}
		pw.close();	
	}
	
	public String getName() {
		return nomen;
	}
	
	private static String GenerateHash(String input) throws NoSuchAlgorithmException {
		MessageDigest objSHA = MessageDigest.getInstance("SHA-1");
		byte[] bytSHA = objSHA.digest(input.getBytes());
		BigInteger intNumber = new BigInteger(1, bytSHA);
		String strHashCode = intNumber.toString(16);
		// pad with 0 if the hexa digits are less then 40.
		while (strHashCode.length() < 40) {
			strHashCode = "0" + strHashCode;
		}
		return strHashCode;
	}


}
