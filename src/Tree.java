import java.util.*; 
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest; 
public class Tree {

	public static void main (String [] args) throws FileNotFoundException {
		File folder = new File ("objects"); 
		folder.mkdir(); 
		
		//TEST 1
		ArrayList<String> list = new ArrayList<String>(); 
		list.add("f : oo"); 
		list.add("b : ar"); 
		list.add("foo : bar"); 
		
		//TEST 2
		ArrayList<String> list2 = new ArrayList<String>(); 
		String s = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f\n"
				+ "blob : 01d82591292494afd1602d175e165f94992f6f5f\n"
				+ "blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83\n"
				+ "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b\n"
				+ "tree: e7d79898d3342fd15daf6ec36f4cb095b52fd976\n"; 
		String[] arr = s.split("\n"); 
		for (int i = 0; i < arr.length; i++) {
			list2.add(arr[i]); 
			System.out.println (arr[i]); 
		}
		
		
		
		//METHOD CALLS
		monkeyAround(list); 
		monkeyAround(list2); 
	}
	
	public Tree() throws FileNotFoundException {
		
		
		
	}
	
	public static void monkeyAround(ArrayList<String> arr) throws FileNotFoundException {
		String s = ""; 
		for (int i = 0; i < arr.size(); i++) {
			s += arr.get(i); 
		}
		String name = getSHA1(s); //for file name
		
		
		File file = new File ("objects/" + name + ".txt"); 
		PrintWriter pw = new PrintWriter (file); 
		for (int i = 0; i < arr.size(); i++) {
			pw.append(arr.get(i)); 
			pw.append("\n"); 
		}
		pw.close(); 
		
		
		
	}
	
	
	private static String getSHA1 (String s1){
		String s = s1;
		String ret = "";

		//process
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(s.getBytes("utf8"));
			ret = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){

			e.printStackTrace();
		}

		return ret; 
	}
}
