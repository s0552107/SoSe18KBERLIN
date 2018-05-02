package de.htw.ai.kbe.runMeRunner;

import org.apache.commons.cli.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    		CommandLineParser parser = new DefaultParser();
    		
    		RunMeRunner runMe = new RunMeRunner();
    		runMe.loadMethods();
    		runMe.writeMethods();
    		System.out.println(runMe.getMethodCount());
    		System.out.println(runMe.getMethodNamesNotInvoke());
    		System.out.println(runMe.getMethodNamesWithRunMe());
    }
}