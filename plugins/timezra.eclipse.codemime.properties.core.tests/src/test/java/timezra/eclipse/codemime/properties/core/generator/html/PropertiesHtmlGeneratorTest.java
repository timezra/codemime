package timezra.eclipse.codemime.properties.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.properties.core.generator.html.PropertiesHtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class PropertiesHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String PROPERTIES_FILE_CONTENTS = ""
			+ "# A Comment" + EOL
			+ "key=value" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String PROPERTIES_FILE_AS_HTML = ""
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(63,127,95);\">#&nbsp;A&nbsp;Comment<br />" + EOL
			+ "</span><span style=\"color: rgb(0,0,0);\">key</span><span style=\"color: rgb(0,0,0);\">=</span><span style=\"color: rgb(42,0,255);\">value</span><br />" + EOL
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "test.properties";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new PropertiesHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return PROPERTIES_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return PROPERTIES_FILE_AS_HTML;
	}
}
