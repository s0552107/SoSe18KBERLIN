package de.htw.ai.kbe.runMeRunner;

import de.htw.ai.kbe.de.PropsFileUtil.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println(PropsFileUtil.readPropsFile("app.properties"));
    }
}
