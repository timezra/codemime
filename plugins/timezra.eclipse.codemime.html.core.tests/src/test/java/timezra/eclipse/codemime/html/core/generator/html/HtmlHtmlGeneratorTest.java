package timezra.eclipse.codemime.html.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.html.core.generator.html.HtmlHtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class HtmlHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String HTML_FILE_CONTENTS = "" 
			+ "<!DOCTYPE html" + EOL
			+ "  PUBLIC \"-//W3C/DTD/ HTML 4.01 Transitional//EN\"" + EOL
			+ "  \"http://www.w3.org/TR/html4/loose.dtd\">" + EOL 
			+ "<html>" + EOL 
			+ "  <head>" + EOL
			+ "    <meta content=\"text/html\">" + EOL 
			+ "    <title>Hello World</title>" + EOL 
			+ "  </head>" + EOL 
			+ "  <body>" + EOL 
			+ "    <!--" + EOL 
			+ "      Hello World!" + EOL 
			+ "    -->" + EOL
			+ "    <h1 style=\"cursor: pointer\" onclick=\"alert('Hello, World!')\">" + EOL
			+ "      Hello,&nbsp;World!" + EOL 
			+ "    </h1>" + EOL 
			+ "  </body>" + EOL 
			+ "</html>" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String HTML_FILE_AS_HTML = ""
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(0,128,128);\">&lt;!</span><span style=\"color: rgb(63,127,127);\">DOCTYPE</span>&nbsp;<span style=\"color: rgb(0,128,128);\">html</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(128,128,128);\">PUBLIC</span>&nbsp;<span style=\"color: rgb(0,128,128);\">&quot;-//W3C/DTD/&nbsp;HTML&nbsp;4.01&nbsp;Transitional//EN&quot;</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(63,127,95);\">&quot;http://www.w3.org/TR/html4/loose.dtd&quot;</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">html</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">head</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">meta</span>&nbsp;<span style=\"color: rgb(127,0,127);\">content</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;text/html&quot;</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">title</span><span style=\"color: rgb(0,128,128);\">&gt;</span>Hello&nbsp;World<span style=\"color: rgb(0,128,128);\">&lt;/</span><span style=\"color: rgb(63,127,127);\">title</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;/</span><span style=\"color: rgb(63,127,127);\">head</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">body</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(63,95,191);\">&lt;!--</span><span style=\"color: rgb(63,95,191);\"><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello&nbsp;World!<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"color: rgb(63,95,191);\">--&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">h1</span>&nbsp;<span style=\"color: rgb(127,0,127);\">style</span>=&quot;<span style=\"color: rgb(127,0,127);\">cursor</span>:&nbsp;<span style=\"font-style: italic; color: rgb(42,0,225);\">pointer</span>&quot;&nbsp;<span style=\"color: rgb(127,0,127);\">onclick</span>=<span style=\"color: rgb(42,0,255);\">&quot;alert('Hello,&nbsp;World!')&quot;</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello,<span style=\"color: rgb(42,0,255);\">&amp;nbsp;</span>World!<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;/</span><span style=\"color: rgb(63,127,127);\">h1</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;<span style=\"color: rgb(0,128,128);\">&lt;/</span><span style=\"color: rgb(63,127,127);\">body</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(0,128,128);\">&lt;/</span><span style=\"color: rgb(63,127,127);\">html</span><span style=\"color: rgb(0,128,128);\">&gt;</span><br />" + EOL 
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "HelloWorld.html";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new HtmlHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return HTML_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return HTML_FILE_AS_HTML;
	}
}
