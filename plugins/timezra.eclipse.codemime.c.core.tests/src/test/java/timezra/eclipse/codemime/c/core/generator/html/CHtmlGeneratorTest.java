package timezra.eclipse.codemime.c.core.generator.html;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.LanguageManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.content.IContentType;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.tests.generator.SimpleFileHtmlGeneratorTest;

public abstract class CHtmlGeneratorTest extends SimpleFileHtmlGeneratorTest {
	@Override
	protected final CodeMimeGenerator createTheGenerator() {
		final IFile theFile = getTheFile();
		final IContentType contentType = CCorePlugin.getContentType(theFile.getProject(), theFile.getName());
		return new CHtmlGenerator(getTheFileName(), LanguageManager.getInstance().getLanguage(contentType));
	}

	@Override
	protected final String getTheFileContents() {
		return getTheTestResourceContents(getTheFileName());
	}

	@Override
	protected final String getTheExpectedHtml() {
		return getTheTestResourceContents(getTheFileName() + ".html");
	}
}