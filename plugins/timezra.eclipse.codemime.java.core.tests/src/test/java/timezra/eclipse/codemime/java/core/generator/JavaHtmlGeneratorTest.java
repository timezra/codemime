package timezra.eclipse.codemime.java.core.generator;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.java.tests.generator.JavaFileHtmlGeneratorTest;

public class JavaHtmlGeneratorTest extends JavaFileHtmlGeneratorTest {

	// @formatter:off
	private static final String JAVA_FILE_CONTENTS = "" +
			"package " + PACKAGE_NAME + ";" + EOL 
			+ EOL
			+ "public abstract class " + CLASS_NAME + " {" + EOL 
			+ EOL 
			+ "	String name;" + EOL 
			+ "" + EOL 
			+ "	public static void main(final String[] args) {" + EOL
			+ "		final HelloWorld helloWorld = new HelloWorld() {" + EOL
			+ "			@Override void sayHello() { System.out.println(\"Hello, \" + name); }" + EOL 
			+ "		};" + EOL
			+ "		helloWorld.name = System.getProperty(\"user.name\");" + EOL 
			+ "		helloWorld.sayHello();" + EOL
			+ "	}" + EOL 
			+ EOL 
			+ "	abstract void sayHello();" + EOL 
			+ "}" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String JAVA_FILE_AS_HTML = "" + "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"font-weight: bold; color: rgb(127,0,85);\">package</span><span style=\"color: rgb(0,0,0);\">&nbsp;" + PACKAGE_NAME + ";<br />" + EOL
			+ "<br />" + EOL
			+ "</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">public</span><span style=\"color: rgb(0,0,0);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">abstract</span><span style=\"color: rgb(0,0,0);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">class</span><span style=\"color: rgb(0,0,0);\">&nbsp;" + CLASS_NAME + "&nbsp;{<br />" + EOL
			+ "<br />" + EOL
			+ "String&nbsp;name;<br />" + EOL
			+ "<br />" + EOL
			+ "</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">public</span><span style=\"color: rgb(0,0,0);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">static</span><span style=\"color: rgb(0,0,0);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">void</span><span style=\"color: rgb(0,0,0);\">&nbsp;main(</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">final</span><span style=\"color: rgb(0,0,0);\">&nbsp;String[]&nbsp;args)&nbsp;{<br />" + EOL
			+ "</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">final</span><span style=\"color: rgb(0,0,0);\">&nbsp;" + CLASS_NAME + "&nbsp;helloWorld&nbsp;=&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">new</span><span style=\"color: rgb(0,0,0);\">&nbsp;" + CLASS_NAME + "()&nbsp;{<br />" + EOL
			+ "@Override&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">void</span><span style=\"color: rgb(0,0,0);\">&nbsp;sayHello()&nbsp;{&nbsp;System.out.println(</span><span style=\"color: rgb(42,0,255);\">&quot;Hello,&nbsp;&quot;</span><span style=\"color: rgb(0,0,0);\">&nbsp;+&nbsp;name);&nbsp;}<br />" + EOL
			+ "};<br />" + EOL
			+ "helloWorld.name&nbsp;=&nbsp;System.getProperty(</span><span style=\"color: rgb(42,0,255);\">&quot;user.name&quot;</span><span style=\"color: rgb(0,0,0);\">);<br />" + EOL
			+ "helloWorld.sayHello();<br />" + EOL
			+ "}<br />" + EOL
			+ "<br />" + EOL
			+ "</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">abstract</span><span style=\"color: rgb(0,0,0);\">&nbsp;</span><span style=\"font-weight: bold; color: rgb(127,0,85);\">void</span><span style=\"color: rgb(0,0,0);\">&nbsp;sayHello();<br />" + EOL 
			+ "}<br />" + EOL 
			+ "</span></span>";
	// @formatter:on

	@Override
	protected String getTheType() {
		return "java";
	}

	@Override
	protected HtmlGenerator createTheGenerator() {
		return new JavaHtmlGenerator();
	}

	@Override
	protected String getTheFileContents() {
		return JAVA_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return JAVA_FILE_AS_HTML;
	}
}
