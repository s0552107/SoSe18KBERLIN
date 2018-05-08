package de.htw.ai.kbe.runMeRunner;

import org.apache.commons.cli.*;

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
		}
		else
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




    		System.out.println(runMe.getMethodCount());
    		System.out.println(runMe.getMethodNamesNotInvoke());
    		System.out.println(runMe.getMethodNamesWithRunMe());
    }
}