package timezra.eclipse.codemime.c.ui.commands;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.LanguageManager;
import org.eclipse.cdt.ui.PreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.content.IContentType;

import timezra.eclipse.codemime.c.core.generator.html.CHtmlGenerator;
import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopySimpleResourceAsHtml;

public class CopyCFileAsHtml extends CopySimpleResourceAsHtml {

	@Override
	protected final String getFontPreferenceName() {
		return PreferenceConstants.EDITOR_TEXT_FONT;
	}

	@Override
	protected final CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		final IResource file = getSelection(event);
		final IContentType contentType = CCorePlugin.getContentType(file.getProject(), file.getName());
		return new CHtmlGenerator(getName(event), LanguageManager.getInstance().getLanguage(contentType));
	}
}
