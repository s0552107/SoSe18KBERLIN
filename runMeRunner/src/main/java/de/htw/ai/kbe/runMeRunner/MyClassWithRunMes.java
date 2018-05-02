package de.htw.ai.kbe.runMeRunner;



public class MyClassWithRunMes {

	@RunMe(
			)
	public static void eins () {
		System.out.println("1");
	}
	@RunMe(
			)
	public static void zwei () {
		System.out.println("2");
	}

	public static void drei () {
		System.out.println("3");
	}
	@RunMe(
			)
	public static void vier () {
		System.out.println("4");
	}

	
}
