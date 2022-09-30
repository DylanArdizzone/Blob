import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
public class Index {
	static HashMap<String, String> blobs = new HashMap<String, String>();
	/*
	public static void main(String[] args) throws IOException{
		Blob git = new Blob();
		git.init();
		git.add("file.txt");
	}
	*/
	public void init() throws IOException {
		
		File ob = new File("objects");
		ob.mkdir();
		if(ob.mkdir()) {
			System.out.println("directory created");
		} else {
			System.out.println("directory not created");
		}
		Path o = Paths.get("objects");
		File nf = new File(o + "/index.txt");
		nf.createNewFile();
		
	}
	public static void add(String filename)  throws IOException, NoSuchAlgorithmException{
 		//Path p = Paths.get(filename);
 		//String fp = p.toAbsolutePath() + ""; 
 		Blob b = new Blob(filename);
 				
 		blobs.put(filename, b.getSha());
 		//Appends that pair to a list in a file named 'index'
 		File agga= new File("index");
 		PrintWriter pw = new PrintWriter(new FileWriter(agga, true));
// 		PrintWriter out = new PrintWriter("index");
// 		for (String file: blobs.keySet()) 
// 		out.println(file + " : " + blobs.get(file));
// 		out.close();
	}
	
	public static void removeBlob(String filename) throws FileNotFoundException {

		String sha = blobs.get(filename);
		blobs.remove(filename);
		File f= new File("objects/"+sha);
		f.delete();
		
		PrintWriter out = new PrintWriter("objects/index.txt");
 		for (String file: blobs.keySet()) 
 			out.append(file + " : " + blobs.get(file));
 	 	
 		out.close();
	 		//return;
		
		//System.out.println("File does not exist");
		
	}
}
