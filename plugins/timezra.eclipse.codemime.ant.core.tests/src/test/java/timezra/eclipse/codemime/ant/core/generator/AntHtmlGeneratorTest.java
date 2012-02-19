package timezra.eclipse.codemime.ant.core.generator;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class AntHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String ANT_FILE_CONTENTS = "" 
			+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + EOL
			+ "<!-- ====================================================================== " + EOL
			+ "     My Project                                                                " + EOL
			+ "     ====================================================================== -->" + EOL
			+ "<project name=\"aProject\">" + EOL 
			+ "	" + EOL 
			+ "    <target name=\"default\">" + EOL
			+ "        <echo>Hello World!</echo>" + EOL 
			+ "    </target>" + EOL 
			+ "	" + EOL 
			+ "</project>" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String ANT_FILE_AS_HTML = "" 
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(128,128,128);\">&lt;?xml&nbsp;version=&quot;1.0&quot;&nbsp;encoding=&quot;UTF-8&quot;?&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(128,0,0);\">&lt;!--&nbsp;======================================================================&nbsp;<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;My&nbsp;Project&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;======================================================================&nbsp;--&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(0,0,128);\">&lt;project</span>&nbsp;<span style=\"color: rgb(0,0,128);\">name=</span><span style=\"color: rgb(0,128,0);\">&quot;aProject&quot;</span><span style=\"color: rgb(0,0,128);\">&gt;</span><br />" + EOL
			+ "<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,128);\">&lt;target</span>&nbsp;<span style=\"color: rgb(0,0,128);\">name=</span><span style=\"color: rgb(0,128,0);\">&quot;default&quot;</span><span style=\"color: rgb(0,0,128);\">&gt;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,128);\">&lt;echo&gt;</span><span style=\"color: rgb(0,0,0);\">Hello</span>&nbsp;<span style=\"color: rgb(0,0,0);\">World!</span><span style=\"color: rgb(0,0,128);\">&lt;/echo&gt;</span><br />" + EOL 
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,128);\">&lt;/target&gt;</span><br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"color: rgb(0,0,128);\">&lt;/project&gt;</span><br />" + EOL 
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "build.xml";
	}

	@Override
	protected HtmlGenerator createTheGenerator() {
		return new AntHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return ANT_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return ANT_FILE_AS_HTML;
	}
}
