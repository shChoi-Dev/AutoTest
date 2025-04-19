package dgu.autotest;

import org.sikuli.script.FindFailed;

public class Main {

	public static void main(String[] args) throws InterruptedException, FindFailed {

		int iteration = 3;
		int jarVersion = 2;

		Autotest sikuli = new Autotest(iteration, jarVersion);
		sikuli.run();
	}
}
