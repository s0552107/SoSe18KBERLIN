package de.htw.ai.kbe.de.PropsFileUtil;

import java.util.Properties;

/**s
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties properties = new Properties();
        try {
			properties = PropsFileUtil.readPropsFile("app.properties");
		} catch (PropsFileReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(properties);
    }
}
