package timezra.eclipse.codemime.xml.core.generator.html;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;
import timezra.eclipse.codemime.xml.core.generator.html.XmlHtmlGenerator;

public class XmlHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	// @formatter:off
	private static final String XML_FILE_CONTENTS = ""
			+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + EOL
			+ "<!-- A Comment -->" + EOL 
			+ "<mime:content " + EOL
			+ "    xmlns:mime=\"http://schemas.xmlsoap.org/wsdl/mime/\"" + EOL
			+ "    xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\"" + EOL
			+ "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" + EOL
			+ "    xsi:schemaLocation=\"http://schemas.xmlsoap.org/wsdl/mime/ http://schemas.xmlsoap.org/wsdl/mime/ \" />" + EOL;
	// @formatter:on

	// @formatter:off
	private static final String XML_FILE_AS_HTML = ""
			+ "<span style=\"font-family: " + FONT_NAME + "; font-size: " + FONT_HEIGHT + "pt;\"><span style=\"color: rgb(0,128,128);\">&lt;?</span><span style=\"color: rgb(63,127,127);\">xml</span>&nbsp;<span style=\"color: rgb(127,0,127);\">version</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;1.0&quot;</span>&nbsp;<span style=\"color: rgb(127,0,127);\">encoding</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;UTF-8&quot;</span><span style=\"color: rgb(0,128,128);\">?&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(63,95,191);\">&lt;!--</span><span style=\"color: rgb(63,95,191);\">&nbsp;A&nbsp;Comment&nbsp;</span><span style=\"color: rgb(63,95,191);\">--&gt;</span><br />" + EOL
			+ "<span style=\"color: rgb(0,128,128);\">&lt;</span><span style=\"color: rgb(63,127,127);\">mime:content</span>&nbsp;<br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(127,0,127);\">xmlns:mime</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;http://schemas.xmlsoap.org/wsdl/mime/&quot;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(127,0,127);\">xmlns:wsdl</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;http://schemas.xmlsoap.org/wsdl/&quot;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(127,0,127);\">xmlns:xsi</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;http://www.w3.org/2001/XMLSchema-instance&quot;</span><br />" + EOL
			+ "&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(127,0,127);\">xsi:schemaLocation</span>=<span style=\"font-style: italic; color: rgb(42,0,255);\">&quot;http://schemas.xmlsoap.org/wsdl/mime/&nbsp;http://schemas.xmlsoap.org/wsdl/mime/&nbsp;&quot;</span>&nbsp;<span style=\"color: rgb(0,128,128);\">/&gt;</span><br />" + EOL
			+ "</span>";
	// @formatter:on

	@Override
	protected String getTheFileName() {
		return "test.xml";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new XmlHtmlGenerator();
	}

	@Override
	protected IDocumentProvider getTheDocumentProvider(final IEditorPart editorPart) {
		@SuppressWarnings("restriction")
		final org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart theEditor = (org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart) editorPart;
		return super.getTheDocumentProvider(theEditor.findEditors(editorPart.getEditorInput())[0]);
	}

	@Override
	protected String getTheFileContents() {
		return XML_FILE_CONTENTS;
	}

	@Override
	protected String getTheExpectedHtml() {
		return XML_FILE_AS_HTML;
	}
}
