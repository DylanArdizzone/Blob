import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FinalEndTester {
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
		Index i = new Index();
		i.add("testAnotherText.txt");
		i.add("textTesterToTest.txt");
		Commit comm = new Commit("this is a commit", "me :)", null);
//		System.out.println(comm.getFileName());
		
		i.add("superMario.txt");
		Commit comm2 = new Commit("second commit", "me again :)", comm);
//		System.out.println(comm.getFileName());
//		
//		System.out.println("1: " + comm.getFileName() + " ---- 2: " + comm2.getFileName());
		comm.setChild(comm2);
		
		i.add("superMarioBrothers2.txt");
		Commit comm3= new Commit("third commit", "me one more time :)", comm2);
		comm2.setChild(comm3);
		
		i.add("fourthtexttester.txt");
		Commit comm4 = new Commit("fourth commit", "me 4", comm3);
		comm3.setChild(comm4);
		
		System.out.println("first commit: " + comm.getFileName());
		System.out.println("second commit: " + comm2.getFileName());
		System.out.println("third commit: " + comm3.getFileName());
		System.out.println("fourth commit: " + comm4.getFileName());
	}

}
