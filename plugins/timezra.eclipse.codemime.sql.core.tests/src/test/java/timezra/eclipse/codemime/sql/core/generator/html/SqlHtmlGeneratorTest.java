package timezra.eclipse.codemime.sql.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class SqlHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "HelloWorld.sql";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new SqlHtmlGenerator(getTheFileName());
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
