import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FinalEndTester {
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
		Index i = new Index();
		i.add("testAnotherText.txt");
		i.add("textTesterToTest.txt");
		Commit comm = new Commit("this is a commit", "me :)", null);
		System.out.println(comm.getFileName());
		i.add("superMario.txt");
		i.add("superMarioBrothers2.txt");
		System.out.println(comm.getFileName());
		Commit comm2 = new Commit("second commit", "me again :)", comm.getFileName()); //second commit's tree contains the contents of the first commit can't have that 
		//comm.setChild(comm2); //setting child changes sha1code no longer points to real filename
	}

}
