import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeTester {
	static ArrayList<String> list = new ArrayList<String>();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		list.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		list.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		list.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		list.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		list.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File f= new File("test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
		//f.delete();
	}

	@Test
	void TreeTest() throws IOException, NoSuchAlgorithmException {
		Tree tree = new Tree();
		File file = new File("test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
		tree.monkeyAround(list);
		assertTrue(file.exists());
		
		//Check if all lines exist and are correct 
		Scanner in = new Scanner(file);
		
		assertTrue(in.nextLine().equals("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f"));
		assertTrue(in.nextLine().equals("blob : 01d82591292494afd1602d175e165f94992f6f5f"));
		assertTrue(in.nextLine().equals("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83"));
		assertTrue(in.nextLine().equals("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b"));
		assertTrue(in.nextLine().equals("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976"));
		
		//file.delete();
	}
}
