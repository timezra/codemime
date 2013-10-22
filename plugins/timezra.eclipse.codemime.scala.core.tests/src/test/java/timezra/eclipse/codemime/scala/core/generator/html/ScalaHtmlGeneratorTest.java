package timezra.eclipse.codemime.scala.core.generator.html;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.junit.After;

import scala.tools.eclipse.ScalaPlugin;
import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.java.tests.generator.JavaFileHtmlGeneratorTest;

public class ScalaHtmlGeneratorTest extends JavaFileHtmlGeneratorTest {

	@After
	public void closeDiagnosticWindows() throws PartInitException {
		final Display theDisplay = Display.getDefault();
		theDisplay.asyncExec(new Runnable() {
			@Override
			public void run() {
				final Shell[] shells = theDisplay.getShells();
				OUTER: for (final Shell s : shells) {
					if (!s.isDisposed()) {
						final String title = s.getText();
						if ("Run Scala Setup Diagnostics?".equals(title) || "Setup Diagnostics".equals(title)) {
							final Button defaultButton = s.getDefaultButton();
							final Control[] children = defaultButton.getParent().getChildren();
							for (final Control child : children) {
								if (child instanceof Button && child != defaultButton) {
									child.notifyListeners(SWT.Selection, new Event());
									break OUTER;
								}
							}
						}
					}
				}
			}
		});
	}

	// private static void doCloseDiagnosticWindows() {
	// final Shell[] shells = Display.getDefault().getShells();
	// for (final Shell s : shells) {
	// if (!s.isDisposed()) {
	// final String title = s.getText();
	// if ("Run Scala Setup Diagnostics?".equals(title)) {
	// final Button defaultButton = s.getDefaultButton();
	// final Control[] children = defaultButton.getParent().getChildren();
	// for (final Control child : children) {
	// if (child instanceof Button && "Never".equals(((Button) child).getText())) {
	// child.notifyListeners(SWT.Selection, new Event());
	// return;
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// @AfterClass
	// public static void closeDiagnosticsWindows() {
	// doCloseDiagnosticWindows();
	// }
	//
	// @After
	// public void reallyCloseDiagnosticsWindows() {
	// doCloseDiagnosticWindows();
	// }
	//
	// @Before
	// public void preCloseDiagnosticsWindows() {
	// doCloseDiagnosticWindows();
	// }

	@Override
	protected String getTheType() {
		return "scala";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new ScalaHtmlGenerator(getTheFileName(), getTheProject());
	}

	@Override
	protected String getTheFileContents() {
		return getTheTestResourceContents(getTheFileName());
	}

	@Override
	protected String getTheExpectedHtml() {
		return getTheTestResourceContents(getTheFileName() + ".html");
	}

	@Override
	protected void configureTheProject(final String sourceFolder) throws CoreException {
		super.configureTheProject(sourceFolder);
		addScalaNature(getTheProject());
	}

	private static void addScalaNature(final IProject project) throws CoreException {
		final IProjectDescription description = project.getDescription();
		final String[] prevNatures = description.getNatureIds();
		final String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = ScalaPlugin.plugin().natureId();
		description.setNatureIds(newNatures);
		project.setDescription(description, NULL_PROGRESS_MONITOR);
	}
}
