import java.util.*;
import java.io.*;
public class Commit {
	private static String parentCommit = null;
	private static String otherCommit = null;
	private static String pTree; 
	private static String author;
	private static String summary;
	private static String date;
	public Commit(String pt, String summ, String a, String pointer) {
		pTree = pt;
		summary = summ;
		author = a;
		
	}
	public static String getDate() {
		return date;
	}
	
}
