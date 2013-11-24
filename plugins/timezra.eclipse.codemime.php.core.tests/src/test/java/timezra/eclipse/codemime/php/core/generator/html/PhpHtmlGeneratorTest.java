package timezra.eclipse.codemime.php.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class PhpHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "HelloWorld.php";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new PhpHtmlGenerator(getTheFileName());
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
