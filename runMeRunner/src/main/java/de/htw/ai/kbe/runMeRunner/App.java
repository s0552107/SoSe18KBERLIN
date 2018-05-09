package de.htw.ai.kbe.runMeRunner;

import org.apache.commons.cli.*;
import java.io.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
		RunMeRunner runMe = new RunMeRunner();


		Options options = new Options();
		options.addOption("p", true, "load property-file"); //—> (name, need parameter?, description)
		options.addOption("o", true, "load txt-file");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		String propName = cmd.getOptionValue("p");
		String txtName = cmd.getOptionValue("o");
		
		if(propName == null)
		{
			//Exception
			System.out.println("no property-file given, try again");
		}
		else
		{
			try 
			{
				
			
				if(txtName == null)
				{
					runMe.loadMethods(propName);
					runMe.writeMethods("runMeReport.txt");//startRunMeRunner(propName) —> default muss TxtName angegeben sein
				}
				else
				{
					runMe.loadMethods(propName);
					runMe.writeMethods(txtName);
					//startRuneMeRunner(prop, txtName)
				}
			}
			catch (ClassNotFoundException e) {
				System.out.println("no executable class");
				
			}
		}



			System.out.println("Ab hier Gesamtausgabe: ");
    		System.out.println("Gesamtmethodenanzahl: "+ runMe.getMethodCount());
    		System.out.println("RunMe Methoden: "+ runMe.getMethodNamesWithRunMe());
    		System.out.println("Not Accesseble: "+ runMe.getMethodNamesNotInvoke());
    		
    }
}