package timezra.eclipse.codemime.css.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.css.core.generator.html.CssHtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class CssHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String CSS_FILE_CONTENTS = "" 
			+ "@CHARSET \"UTF-8\";" + EOL 
			+ EOL 
			+ "/* a comment */" + EOL 
			+ "html.no-cssgradients #logo a:link {" + EOL 
			+ "	background-color: #aaa;" + EOL 
			+ "}" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String CSS_FILE_AS_HTML = ""
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(63,127,127);\">@CHARSET</span>&nbsp;<span style=\"font-style: italic; color: rgb(42,0,225);\">&quot;UTF-8&quot;</span>;<br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(63,95,191);\">/*&nbsp;a&nbsp;comment&nbsp;*/</span><br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(63,127,127);\">html</span><span style=\"font-style: italic; color: rgb(63,127,127);\">.no-cssgradients</span><span style=\"color: rgb(63,127,127);\">&nbsp;</span><span style=\"font-style: italic; color: rgb(63,127,127);\">#logo</span><span style=\"color: rgb(63,127,127);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(63,127,127);\">a</span><span style=\"font-style: italic; color: rgb(63,127,127);\">:link</span>&nbsp;{<br />" + EOL
			+ "<span style=\"color: rgb(127,0,127);\">background-color</span>:&nbsp;<span style=\"font-style: italic; color: rgb(42,0,225);\">#aaa</span>;<br />" + EOL 
			+ "}<br />" + EOL 
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "style.css";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new CssHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return CSS_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return CSS_FILE_AS_HTML;
	}
}
