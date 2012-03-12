package timezra.eclipse.codemime.dtd.core.generator.html;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.dtd.core.generator.html.DtdHtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class DtdHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String DTD_FILE_CONTENTS = "" 
			+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + EOL
			+ "<!DOCTYPE REPORT [" + EOL 
			+ EOL 
			+ "<!ELEMENT BRANCH (%STEM;,BRANCH*)>" + EOL
			+ "<!ELEMENT LEAF	EMPTY>" + EOL 
			+ EOL 
			+ "<!ATTLIST BRANCH	bark (smooth | rough) \"rough\">" + EOL 
			+ "<!ATTLIST LEAF	color ENTITY #REQUIRED>" + EOL 
			+ EOL
			+ "<!ENTITY xml	 \"Extensible Markup Language\">" + EOL 
			+ "<!ENTITY % STEM	\"(#PCDATA)+\">" + EOL 
			+ EOL 
			+ "<!NOTATION ELM	 SYSTEM \"\">" + EOL 
			+ "]>" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String DTD_FILE_AS_HTML = "" 
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(63,63,191);\">&lt;</span>?xml&nbsp;version=&quot;1.0&quot;&nbsp;encoding=&quot;UTF-8&quot;?<span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span>DOCTYPE&nbsp;REPORT&nbsp;[<br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ELEMENT</span>&nbsp;<span style=\"color: rgb(191,95,95);\">BRANCH</span>&nbsp;(<span style=\"color: rgb(191,95,95);\">%STEM;</span><span style=\"color: rgb(128,0,0);\">,</span><span style=\"color: rgb(191,95,95);\">BRANCH</span><span style=\"color: rgb(128,0,0);\">*</span>)<span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ELEMENT</span>&nbsp;<span style=\"color: rgb(191,95,95);\">LEAF</span><span style=\"color: rgb(191,95,95);\">EMPTY</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ATTLIST</span>&nbsp;<span style=\"color: rgb(191,95,95);\">BRANCH</span>bark&nbsp;(<span style=\"color: rgb(191,95,95);\">smooth</span>&nbsp;|&nbsp;<span style=\"color: rgb(191,95,95);\">rough</span>)&nbsp;<span style=\"font-style: italic; color: rgb(63,159,95);\">&quot;rough&quot;</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ATTLIST</span>&nbsp;<span style=\"color: rgb(191,95,95);\">LEAF</span>color&nbsp;<span style=\"color: rgb(128,0,0);\">ENTITY</span>&nbsp;<span style=\"color: rgb(128,0,0);\">#REQUIRED</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ENTITY</span>&nbsp;<span style=\"color: rgb(191,95,95);\">xml</span>&nbsp;<span style=\"font-style: italic; color: rgb(63,159,95);\">&quot;Extensible&nbsp;Markup&nbsp;Language&quot;</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">ENTITY</span>&nbsp;%&nbsp;<span style=\"color: rgb(191,95,95);\">STEM</span><span style=\"font-style: italic; color: rgb(63,159,95);\">&quot;(#PCDATA)+&quot;</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(63,63,191);\">&lt;</span><span style=\"color: rgb(63,63,191);\">!</span><span style=\"color: rgb(63,63,191);\">NOTATION</span>&nbsp;<span style=\"color: rgb(191,95,95);\">ELM</span>&nbsp;<span style=\"color: rgb(128,0,0);\">SYSTEM</span>&nbsp;<span style=\"font-style: italic; color: rgb(63,159,95);\">&quot;&quot;</span><span style=\"color: rgb(63,63,191);\">&gt;</span><br />" + EOL 
			+ "]<span style=\"color: rgb(63,63,191);\">&gt;</span></span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "test.dtd";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new DtdHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return DTD_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return DTD_FILE_AS_HTML;
	}
}
