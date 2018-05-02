package de.htw.ai.kbe.runMeRunner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


import de.htw.ai.kbe.de.PropsFileUtil.PropsFileUtil;

public class RunMeRunner {
	int methodCount = 0;
	


	List<String> methodNamesWithRunMe = new ArrayList<String>();
	List<String> methodNamesNotInvoke = new ArrayList<String>();
	

	public void loadMethods() throws Exception{
		//System.out.println(PropsFileUtil.readPropsFile("app.properties").getProperty("classToLoad"));
		String className = PropsFileUtil.readPropsFile("app.properties").getProperty("classToLoad");
		Class<?> classToLoad = Class.forName(className);
		Method [] methodsToLoad = classToLoad.getDeclaredMethods();
		for (Method m : methodsToLoad) {
			
			//System.out.println("method name:  " + m.getName());
		}
		
		
		Method [] me = classToLoad.newInstance().getClass().getDeclaredMethods();
		for (Method m : me) {
			this.methodCount += 1;
			if (m.isAnnotationPresent(RunMe.class)) {
				this.methodNamesWithRunMe.add(m.toString());
				m.invoke(classToLoad.newInstance());
			}
			else {
				this.methodNamesNotInvoke.add(m.toString());
			}
			
		}
	}
	
	
	
	public void writeMethods () throws IOException {
		
		FileWriter fw = new FileWriter("runMeReport.txt");
	    BufferedWriter bw = new BufferedWriter(fw);

	    bw.write(String.valueOf(this.methodCount));
	    bw.write("\n");
	    bw.write("\n");
	    for (String s : this.methodNamesWithRunMe) {
	    	bw.write(s);
	    }
	    bw.write("\n");
	    bw.write("\n");
	    for (String s : this.methodNamesNotInvoke) {
	    	bw.write(s);
	    }

	    bw.close();
		
	}
	
	
	
	
	
	public int getMethodCount() {
		return methodCount;
	}

	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}
	public List<String> getMethodNamesWithRunMe() {
		return methodNamesWithRunMe;
	}

	public void setMethodNamesWithRunMe(List<String> methodNamesWithRunMe) {
		this.methodNamesWithRunMe = methodNamesWithRunMe;
	}

	public List<String> getMethodNamesNotInvoke() {
		return methodNamesNotInvoke;
	}

	public void setMethodNamesNotInvoke(List<String> methodNamesNotInvoke) {
		this.methodNamesNotInvoke = methodNamesNotInvoke;
	}
}
