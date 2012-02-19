package timezra.eclipse.codemime.js.core.generator;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class JsHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String JS_FILE_CONTENTS = "" 
			+ "/**" + EOL 
			+ " * Output Hello, World!" + EOL 
			+ " */" + EOL
			+ "function sayHello() {" + EOL 
			+ "	/*" + EOL 
			+ "	 * The greeting" + EOL 
			+ "	 */" + EOL
			+ "	var greeting = \"Hello World!\";" + EOL 
			+ "	alert(greeting); // show the greeting" + EOL 
			+ "}" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String JS_FILE_AS_HTML = "" 
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(63,95,191);\">/**</span><br />" + EOL
			+ "&nbsp;<span style=\"color: rgb(63,95,191);\">*</span>&nbsp;<span style=\"color: rgb(63,95,191);\">Output</span>&nbsp;<span style=\"color: rgb(63,95,191);\">Hello,</span>&nbsp;<span style=\"color: rgb(63,95,191);\">World!</span><br />" + EOL
			+ "&nbsp;<span style=\"color: rgb(63,95,191);\">*/</span><br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(127,0,85);\">function</span>&nbsp;<span style=\"color: rgb(0,0,0);\">sayHello()</span>&nbsp;<span style=\"color: rgb(0,0,0);\">{</span><br />" + EOL
			+ "<span style=\"color: rgb(63,127,95);\">/*<br />" + EOL
			+ "&nbsp;*&nbsp;The&nbsp;greeting<br />" + EOL
			+ "&nbsp;*/</span><br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(127,0,85);\">var</span>&nbsp;<span style=\"color: rgb(0,0,0);\">greeting</span>&nbsp;<span style=\"color: rgb(0,0,0);\">=</span>&nbsp;<span style=\"color: rgb(42,0,255);\">&quot;Hello&nbsp;World!&quot;</span><span style=\"color: rgb(0,0,0);\">;</span><br />" + EOL
			+ "<span style=\"color: rgb(0,0,0);\">alert(greeting);</span>&nbsp;<span style=\"color: rgb(63,127,95);\">//&nbsp;show&nbsp;the&nbsp;greeting<br />" + EOL 
			+ "</span><span style=\"color: rgb(0,0,0);\">}</span><br />" + EOL 
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "helloworld.js";
	}

	@Override
	protected HtmlGenerator createTheGenerator() {
		return new JsHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return JS_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return JS_FILE_AS_HTML;
	}
}
