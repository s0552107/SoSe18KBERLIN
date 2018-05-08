package de.htw.ai.kbe.runMeRunner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.FileNotFoundException;
import de.htw.ai.kbe.de.PropsFileUtil.PropsFileUtil;

public class RunMeRunnerTest {
	
	@Test
	public void construct(){
		RunMeRunner tester = new RunMeRunner();
	}
	
	@Test (expected = Exception.class)
	public void notAPropFile() throws Exception {
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("properties");
	}
	
	@Test (expected = Exception.class)
	public void emptyFileName() throws Exception {
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("");
	}

}
