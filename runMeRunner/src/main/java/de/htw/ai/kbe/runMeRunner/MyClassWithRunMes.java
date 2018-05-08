package de.htw.ai.kbe.runMeRunner;



public class MyClassWithRunMes {

	@RunMe(
			)
	public static void eins () {
		System.out.println("eins");
	}
	
	@RunMe(
			)
	static void zwei () {
		System.out.println("zwei");
	}

	public static void drei () {
		System.out.println("drei");
	}
	
	@RunMe(
			)
	public static void vier (String vier) {
		System.out.println(vier);
	}
	
	@RunMe(
			)
	private static void fuenf () {
		System.out.println("fuenf");
	}

	
}
