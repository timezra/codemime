package timezra.eclipse.codemime.groovy.core.generator.html;

import org.codehaus.groovy.eclipse.core.model.GroovyRuntime;
import org.codehaus.jdt.groovy.integration.LanguageSupportFactory;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.java.tests.generator.JavaFileHtmlGeneratorTest;

public class GroovyHtmlGeneratorTest extends JavaFileHtmlGeneratorTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		LanguageSupportFactory.getEventHandler(); // ensures the plugin gets loaded before the editor is opened
	}

	@Override
	protected String getTheType() {
		return "groovy";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new GroovyHtmlGenerator(getTheFileName(), getTheProject());
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

		GroovyRuntime.addGroovyRuntime(getTheProject());
		getTheProject().build(IncrementalProjectBuilder.FULL_BUILD, null);
	}
}
