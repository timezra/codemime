package timezra.eclipse.codemime.groovy.core.generator.html;

import org.codehaus.groovy.eclipse.core.model.GroovyRuntime;
import org.codehaus.jdt.groovy.integration.LanguageSupportFactory;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.java.tests.generator.JavaFileHtmlGeneratorTest;

public class GroovyHtmlGeneratorTest extends JavaFileHtmlGeneratorTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		LanguageSupportFactory.getEventHandler(); // ensures the plugin gets loaded before the editor is opened
	}

	// @formatter:off
	private static final String GROOVY_FILE_CONTENTS = "" 
			+ "package " + PACKAGE_NAME + EOL 
			+ EOL 
			+ "class " + CLASS_NAME + " {" + EOL 
			+ "	static void main(args) {" + EOL
			+ "		def name = args[0].replaceAll(/\\w+/, { it[0].toUpperCase() + ((it.size() > 1) ? it[1..-1] : '') })" + EOL 
			+ "		println \"Hello, ${name}!\"" + EOL 
			+ "	}" + EOL 
			+ "}" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String GROOVY_FILE_AS_HTML = "" 
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"font-weight: bold; color: rgb(151,44,120);\">package</span>&nbsp;<span style=\"color: rgb(0,0,0);\">" + PACKAGE_NAME + "</span><br />" + EOL
			+ "<br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(151,44,120);\">class</span>&nbsp;<span style=\"color: rgb(0,0,0);\">" + CLASS_NAME + "</span>&nbsp;<span style=\"color: rgb(0,0,0);\">{</span><br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(151,44,120);\">static</span>&nbsp;<span style=\"font-weight: bold; color: rgb(151,44,120);\">void</span>&nbsp;<span style=\"color: rgb(0,0,0);\">main(args)</span>&nbsp;<span style=\"color: rgb(0,0,0);\">{</span><br />" + EOL
			+ "<span style=\"font-weight: bold; color: rgb(151,44,120);\">def</span>&nbsp;<span style=\"color: rgb(0,0,0);\">name</span>&nbsp;<span style=\"color: rgb(0,0,0);\">=</span>&nbsp;<span style=\"color: rgb(0,0,0);\">args[</span><span style=\"color: rgb(205,50,0);\">0</span><span style=\"color: rgb(0,0,0);\">].replaceAll(/\\w+/,</span>&nbsp;<span style=\"color: rgb(0,0,0);\">{</span>&nbsp;<span style=\"color: rgb(102,204,255);\">it</span><span style=\"color: rgb(0,0,0);\">[</span><span style=\"color: rgb(205,50,0);\">0</span><span style=\"color: rgb(0,0,0);\">].toUpperCase()</span>&nbsp;<span style=\"color: rgb(0,0,0);\">+</span>&nbsp;<span style=\"color: rgb(0,0,0);\">((</span><span style=\"color: rgb(102,204,255);\">it</span><span style=\"color: rgb(0,0,0);\">.</span><span style=\"color: rgb(102,204,255);\">size</span><span style=\"color: rgb(0,0,0);\">()</span>&nbsp;<span style=\"color: rgb(0,0,0);\">&gt;</span>&nbsp;<span style=\"color: rgb(205,50,0);\">1</span><span style=\"color: rgb(0,0,0);\">)</span>&nbsp;<span style=\"color: rgb(0,0,0);\">?</span>&nbsp;<span style=\"color: rgb(102,204,255);\">it</span><span style=\"color: rgb(0,0,0);\">[</span><span style=\"color: rgb(205,50,0);\">1</span><span style=\"color: rgb(0,0,0);\">..-</span><span style=\"color: rgb(205,50,0);\">1</span><span style=\"color: rgb(0,0,0);\">]</span>&nbsp;<span style=\"color: rgb(0,0,0);\">:</span>&nbsp;<span style=\"color: rgb(255,0,204);\">''</span><span style=\"color: rgb(0,0,0);\">)</span>&nbsp;<span style=\"color: rgb(0,0,0);\">})</span><br />" + EOL
			+ "<span style=\"color: rgb(102,204,255);\">println</span>&nbsp;<span style=\"color: rgb(255,0,204);\">&quot;Hello,&nbsp;${name}!&quot;</span><br />" + EOL 
			+ "<span style=\"color: rgb(0,0,0);\">}</span><br />" + EOL
			+ "<span style=\"color: rgb(0,0,0);\">}</span><br />" + EOL 
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheType() {
		return "groovy";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new GroovyHtmlGenerator(getTheProject());
	}

	@Override
	protected String getTheFileContents() {
		return GROOVY_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return GROOVY_FILE_AS_HTML;
	}

	@Override
	protected void configureTheProject(final String sourceFolder) throws CoreException {
		super.configureTheProject(sourceFolder);

		GroovyRuntime.addGroovyRuntime(getTheProject());
		getTheProject().build(IncrementalProjectBuilder.FULL_BUILD, null);
	}
}
