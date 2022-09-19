import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.io.*;
public class Commit {
	private static String parent = null;
	private static String child = null;
	private static String pTree; 
	private static String author;
	private static String summary;
	private static String date;
	public Commit(String pt, String summ, String a, String pointer) {
		pTree = pt;
		summary = summ;
		author = a;
		parent = pointer;
		
	}
	public static String getDate() {
		// from javatpoint
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		return strDate; 
	}
	
}
