package dgu.autotest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.sikuli.basics.Settings;
import org.sikuli.script.Env;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

@SuppressWarnings("deprecation")
public class SikuliSteps {

	public Process p;
	public Screen screen = new Screen();

	public boolean click(String pictureUrl, Double similarity, int retires) {
		boolean found = false;
		int i = 0;
		while (!click(pictureUrl, similarity) && i < retires) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Could not find " + pictureUrl);
				found = false;
			}
			System.out.println("Could not find the image. Checking for " + i+ " time");
			found = true;
		}
		return found;
	}

	public boolean click(String pictureUrl, Double similarity) {
		Settings.MinSimilarity = similarity;

		try {
			screen.exists(("src/main/resources/" + pictureUrl)).click();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Could not find " + pictureUrl);
			return false;
		}
		System.out.println("Clicked on " + pictureUrl);
		return true;
	}

	public boolean click(String pictureUrl) {
		return click(pictureUrl, 0.85);
	}

	public void exitJava() {
		p.destroyForcibly();
	}

	public void rightClick() {
		screen.rightClick();
		screen.keyUp();
	}

	public void openJavaJar(int version) throws InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("java", "-jar","src/main/resources/software" + version + ".jar");
		System.out.println("Trying to open the jar...");
		try {
			p = pb.start();
		} catch (IOException e) {
			System.err.println("Could not open the process");
			e.printStackTrace();
		}
		Thread.sleep(TimeUnit.SECONDS.toMillis(5));
		if (p.isAlive()) {
			System.out.println("Process has been launched");
		} else {
			System.err.println("Could not open the process! Check the path");
		}

	}

	public boolean compareTextToClipboards(String textToCompare) {
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String textFromSikuli = Env.getClipboard();
		if (textFromSikuli.equals(textToCompare)) {
			System.out.println("Text is the same");
			return true;
		} else {
			System.out.println("Text is not the same :\n'" + textToCompare+ "' " + "\nvs");
			System.out.println("'" + textFromSikuli + "'");
			return false;
		}
	}

	public boolean write(String pictureUrl, String text) {
		if (click(pictureUrl)) {
			screen.doubleClick();
			screen.type(text);
			return true;
		} else {
			System.out.println("Could not type text");
			return false;
		}

	}

	public boolean verifyIfExists(String pictureUrl){
		screen.mouseUp();
		if (screen.exists("src/main/resources/" + pictureUrl) != null) {
			// click(pictureUrl);
			System.out.println(pictureUrl + " exists");
			return true;
		} else {
			System.out.println("Could not find : " + pictureUrl);
			return false;
		}

	}

	public boolean verifyIfExistsReTried(String pictureUrl, int retries) {
		screen.mouseUp();
		boolean found = false;
		int i = 0;
		while (i <= retries && found == false) {
			if (screen.exists("src/main/resources/" + pictureUrl) != null) {
				System.out.println(pictureUrl + " exists");
				found = true;
			} else {
				System.out.println("Could not find : " + pictureUrl);
				found = false;
			}
			i++;

		}
		return found;
	}

}
