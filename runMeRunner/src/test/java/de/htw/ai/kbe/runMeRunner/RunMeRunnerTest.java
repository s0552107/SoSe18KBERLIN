package de.htw.ai.kbe.runMeRunner;

import static org.junit.Assert.assertEquals;

import de.htw.ai.kbe.de.PropsFileUtil.PropsFileReadException;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.htw.ai.kbe.de.PropsFileUtil.PropsFileUtil;

public class RunMeRunnerTest {
	
	@Test
	public void construct(){
		RunMeRunner tester = new RunMeRunner();
	}
	
	@Test (expected = PropsFileReadException.class)
	public void notAPropFile() throws Exception {
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("properties");
	}
	
	@Test (expected = PropsFileReadException.class)
	public void emptyFileName() throws Exception {
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("");
	}

	@Test (expected = ClassNotFoundException.class)
	public void unknownClassInPopsFile() throws Exception
	{
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("unknownClass.properties");
	}

	@Test (expected = IllegalAccessException.class)
	public void noAccessForLoadedClass() throws Exception
	{
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("noAccessClass.properties");
	}

	@Test (expected = InstantiationException.class)
	public void noInstantiableClass() throws Exception
	{
		RunMeRunner tester = new RunMeRunner();
		tester.loadMethods("noInstantiation.properties");
	}

	/*
	@Test (expected = FileNotFoundException.class)
	public void wrongArgument() throws Exception
	{
		RunMeRunner tester = new RunMeRunner();
		tester.writeMethods(";:-$§41123das3%38721");
	}*/
}
