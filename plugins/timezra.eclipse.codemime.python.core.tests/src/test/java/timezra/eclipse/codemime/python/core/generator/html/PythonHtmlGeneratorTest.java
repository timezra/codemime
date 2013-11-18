package timezra.eclipse.codemime.python.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class PythonHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "HelloWorld.py";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new PythonHtmlGenerator(getTheFileName());
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
