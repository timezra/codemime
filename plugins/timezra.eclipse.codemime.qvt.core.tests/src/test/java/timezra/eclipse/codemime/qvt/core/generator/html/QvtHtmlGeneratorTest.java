package timezra.eclipse.codemime.qvt.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class QvtHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "test.qvto";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new QvtHtmlGenerator(getTheFileName());
	}

	@Override
	protected String getTheFileContents() {
		return getTheTestResourceContents(getTheFileName());
	}

	@Override
	protected String getTheExpectedHtml() {
		return getTheTestResourceContents(getTheFileName() + ".html");
	}

	// private void configureTheProject(final String sourceFolder) throws CoreException {
	// addQvtNature(getTheProject());
	// }
	//
	// private static void addQvtNature(final IProject project) throws CoreException {
	// final IProjectDescription description = project.getDescription();
	// final String[] prevNatures = description.getNatureIds();
	// final String[] newNatures = new String[prevNatures.length + 1];
	// System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
	// newNatures[prevNatures.length] = Activator.PLUGIN_ID;
	// description.setNatureIds(newNatures);
	// project.setDescription(description, NULL_PROGRESS_MONITOR);
	// }
}
