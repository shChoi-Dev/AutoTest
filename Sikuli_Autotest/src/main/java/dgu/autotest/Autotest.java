package dgu.autotest;

import java.io.FileNotFoundException;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.reporting.ColumnDataTypesReport;

import org.sikuli.script.FindFailed;

/**
 * SikuliX test for Autotest class.
 *
 * @author shChoi
 */

public class Autotest implements Runnable {

	private SikuliSteps sikuli;
	private DRDataSource reportFile;
	private int iteration;
	private int jarVersion;

	public Autotest(int iteration, int jarVersion) {
		this.iteration = iteration;
		this.jarVersion = jarVersion;
	}

	public void run() {

		reportFile = new DRDataSource("result", "tab", "comment");
		sikuli = new SikuliSteps();

		openSoftware();

		if (iteration == 1 || iteration == 2) {
			try {
				tab1();
				tab2();
				tab3();
				tab4();
			} catch (FindFailed e) {
				e.printStackTrace();
			}
		}

		if (iteration == 3) {
			try {
				tab1();
				tab2();
				tab3();
				tab4();
				tab5();
			} catch (FindFailed e) {
				e.printStackTrace();
			}
		}

		ColumnDataTypesReport report = new ColumnDataTypesReport(reportFile, "Report" + iteration + ".pdf");
		try {
			report.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void tab1() throws FindFailed {
		boolean succeeded;
		succeeded = sikuli.click("koalaTab.png");
		if (succeeded) {
			succeeded = sikuli.verifyIfExists("koalaPicture.png");
		}
		if (succeeded) {
			succeeded = sikuli.click("textEditorTab.png");
			succeeded = sikuli.click("LoremIpsumTab.png");
			succeeded = sikuli.click("colorPickerTab.png");
			succeeded = sikuli.click("LoremIpsumTab.png");
			succeeded = sikuli.click("textEditorTab.png");
			succeeded = sikuli.click("koalaTab.png");
			succeeded = sikuli.verifyIfExists("koalaPicture.png");
			if (succeeded) {
				reportFile.add("PASSED", "Tab 1", "Koala picture is the same");
			} else {
				reportFile.add("FAILED", "Tab 1", "Koala picture is not the same");
			}
		} else {
			reportFile.add("FAILED", "Tab 1", "Koala picture is not the same");
		}
	}

	private void tab2() throws FindFailed {
		boolean succeeded;

		succeeded = sikuli.click("textEditorTab.png");
		sikuli.write("writeSomething.png", "The text format must remain the same.");

		sikuli.click("LoremIpsumTab.png");
		sikuli.click("textEditorTab.png");

		succeeded = sikuli.compareTextToClipboards("The text format must remain the same.");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 2", "Entered text is the same");
		} else {
			reportFile.add("FAILED", "Tab 2", "Entered text is not the same");
		}

		if (iteration == 1 || iteration == 2) {
			succeeded = sikuli.verifyIfExists("textEditorElements.png");
		} else {
			succeeded = sikuli.verifyIfExists("textEditorElementsUpdate.png");
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 2", "HTML editor contain all the elements");
		} else {
			reportFile.add("FAILED", "Tab 2", "HTML editor contain did not all the elements");
		}

		succeeded = sikuli.verifyIfExists("checkTab1.png");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 2", "Entered text format is the same");
		} else {
			reportFile.add("FAILED", "Tab 2", "Entered text format is not the same");
		}

		succeeded = sikuli.verifyIfExists("textFont.png");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 2", "The text font is the same");
		} else {
			reportFile.add("FAILED", "Tab 2", "The text font is not the same");
		}
	}

	private void tab3() throws FindFailed {
		boolean succeeded;
		succeeded = sikuli.click("LoremIpsumTab.png");
		succeeded = sikuli.click("koalaTab.png");
		succeeded = sikuli.click("LoremIpsumTab.png");
		if (succeeded) {
			succeeded = sikuli.click("loremIpsumText.png");
		}
		if (succeeded) {
			succeeded = sikuli.compareTextToClipboards(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 3", "Text length is the same");
		} else {
			reportFile.add("FAILED", "Tab 3", "Text length is not the same");
			succeeded = true;
		}
		if (succeeded) {
			sikuli.rightClick();
			succeeded = sikuli.verifyIfExists("rightClick.png");
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 3", "Context menu is present");
		} else {
			reportFile.add("FAILED", "Tab 3", "Context menu is not present");
		}
	}

	private void tab4() throws FindFailed {
		boolean succeeded;

		succeeded = sikuli.click("colorPickerTab.png");
		if (succeeded) {
			succeeded = sikuli.click("koalaTab.png");
		}
		if (succeeded) {
			succeeded = sikuli.click("colorPickerTab.png");
		}
		if (succeeded) {
			succeeded = sikuli.click("colorPickerCollapsable.png");
		}
		if (succeeded) {
			succeeded = sikuli.click("colorPickerUncollapsed.png");
		}
		if (succeeded) {
			succeeded = sikuli.click("colorPickerCollapsable.png");
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 4", "Color Picker Tab is collapsable");
		} else {
			reportFile.add("FAILED", "Tab 4", "Color Picker Tab is not collapsable");
		}

		if (succeeded) {
			succeeded = sikuli.verifyIfExists("colorIsWhite.png");
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 4", "Default color is white");
		} else {
			reportFile.add("FAILED", "Tab 4", "Default color is not white");
		}
	}

	private void tab5() throws FindFailed {
		boolean succeeded = sikuli.click("progressTab.png");

		succeeded = sikuli.verifyIfExists("loaderInfinite.png");
		int i = 0;
		while (!succeeded && i < 10) {
			succeeded = sikuli.verifyIfExists("loaderInfinite.png");
			System.out.println(i);
			i++;
		}
		if (succeeded) {
			reportFile.add("PASSED", "Tab 5", "Infinite loader bar is present");
		} else {
			reportFile.add("FAILED", "Tab 5", "Infinite loader bar not present");
		}
	}

	private void openSoftware() {
		try {
			sikuli.openJavaJar(jarVersion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
