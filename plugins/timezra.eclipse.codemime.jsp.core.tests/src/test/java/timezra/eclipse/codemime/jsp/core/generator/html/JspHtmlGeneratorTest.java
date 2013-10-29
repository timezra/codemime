package timezra.eclipse.codemime.jsp.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class JspHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "HelloWorld.jsp";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new JspHtmlGenerator(getTheFileName());
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
