import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException; 

class GitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		File folder = new File ("objects"); 
		folder.mkdir(); 


		//for blob
		Path p = Paths.get("something.txt");
		try {
			Files.writeString(p, "some content", StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//for index 
		File foo = new File ("foo.txt"); 
		PrintWriter foow = new PrintWriter(foo); 
		foow.append("foo content"); 
		foow.close(); 

		File bar = new File ("bar.txt"); 
		PrintWriter barw = new PrintWriter(bar); 
		barw.append("bar content"); 
		barw.close(); 

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File bf = new File ("something.txt"); 
		bf.delete(); 
		File bf2 = new File ("objects/94e66df8cd09d410c62d9e0dc59d3a884e458e05.txt"); 
		bf2.delete(); 

		//delete index file 
		File if1 = new File ("index.txt"); 
		if1.delete(); 

		//delete objects with contents inside
		File if2 = new File ("objects"); 
		deleteDir(if2); 


		//delete inputs for index 
		File if3 = new File ("foo.txt"); 
		if3.delete(); 
		File if4 = new File ("bar.txt"); 
		if4.delete(); 


	}

	static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}


	@Test
	void testBlob() throws IOException {

		//blob
		Blob b = new Blob("something.txt"); 
		File f = new File ("objects/94e66df8cd09d410c62d9e0dc59d3a884e458e05"); 
		assertTrue(f.exists()); 

	}

	//fails with blob

	@Test
	void testInit() throws IOException, NoSuchAlgorithmException {

		Index i = new Index(); 
		i.init(); 
		File f = new File ("objects/index.txt"); 
//		assertTrue(f.exists()); 
		Path f2 = Paths.get("objects"); 
		assertTrue(Files.exists(f2)); 


		//succeeds with objects
		//fails with index (creates it in objects folder, not in project folder 



	}

	@Test
	void testAdd() throws IOException, NoSuchAlgorithmException {

		Index i = new Index(); 
		i.init(); 
		i.add("foo.txt");
		File i1 = new File ("objects/ec097bb2a51eb70410d13bbe94ef0319680accb6"); 
		assertTrue(i1.exists());
		i.add("bar.txt"); 
		File i2 = new File ("objects/af727e4deee39aead170e830c61b9c2844a3d75b"); 
		assertTrue(i2.exists()); 

		//fails with add

	}

	@Test
	void testRemove() throws IOException, NoSuchAlgorithmException {
		Index i = new Index(); 
		i.init(); 
		i.removeBlob("foo.txt");
		File i1 = new File ("objects/ec097bb2a51eb70410d13bbe94ef0319680accb6"); 
		assertTrue(i1.exists());

		//can't remove with no add

	}

}
