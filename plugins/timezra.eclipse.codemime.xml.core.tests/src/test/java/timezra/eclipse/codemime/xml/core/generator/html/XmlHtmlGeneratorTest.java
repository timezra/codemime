package timezra.eclipse.codemime.xml.core.generator.html;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public class XmlHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {

	@Override
	protected String getTheFileName() {
		return "test.xml";
	}

	@Override
	protected CodeMimeGenerator createTheGenerator() {
		return new XmlHtmlGenerator(getTheFileName());
	}

	@Override
	protected IDocumentProvider getTheDocumentProvider(final IEditorPart editorPart) {
		@SuppressWarnings("restriction")
		final org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart theEditor = (org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart) editorPart;
		return super.getTheDocumentProvider(theEditor.findEditors(editorPart.getEditorInput())[0]);
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
