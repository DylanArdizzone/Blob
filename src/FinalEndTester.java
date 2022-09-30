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
		
		System.out.println(comm.getFileName());
		System.out.println(comm2.getFileName());
		System.out.println(comm3.getFileName());
	}

}
