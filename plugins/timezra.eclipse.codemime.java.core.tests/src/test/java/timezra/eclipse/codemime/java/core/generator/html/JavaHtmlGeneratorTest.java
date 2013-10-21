package timezra.eclipse.codemime.java.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.java.tests.generator.JavaFileHtmlGeneratorTest;

public class JavaHtmlGeneratorTest extends JavaFileHtmlGeneratorTest {

	@Override
	protected String getTheType() {
		return "java";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new JavaHtmlGenerator(getTheFile().getName());
	}

	@Override
	protected String getTheFileContents() {
		return getTheTestResourceContents(getTheFileName());
	}

	@Override
	protected String getTheExpectedHtml() {
		return getTheTestResourceContents(getTheFileName() + ".html");
	}
}
