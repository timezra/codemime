package timezra.eclipse.codemime.c.ui.commands;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.model.LanguageManager;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.cdt.ui.PreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.ide.ResourceUtil;

import timezra.eclipse.codemime.c.core.generator.html.CHtmlGenerator;
import timezra.eclipse.codemime.c.ui.Activator;
import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;

public class CopyCTextAsHtml extends CopyTextAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return PreferenceConstants.EDITOR_TEXT_FONT;
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new CHtmlGenerator(getTitle(event), getLanguage(event));
	}

	private IContentType getContentType(final IEditorInput input) {
		if (input instanceof IPathEditorInput) {
			return CCorePlugin.getContentType(((IPathEditorInput) input).getPath().lastSegment());
		}
		final ILocationProvider locationProvider = (ILocationProvider) input.getAdapter(ILocationProvider.class);
		if (locationProvider != null) {
			final IPath path = locationProvider.getPath(input);
			if (path != null) {
				return CCorePlugin.getContentType(path.lastSegment());
			}
		}
		return null;
	}

	private ILanguage getLanguage(final ExecutionEvent event) {
		final IEditorInput input = getEditorInput(event);
		final ICElement element = CUIPlugin.getDefault().getWorkingCopyManager().getWorkingCopy(input);
		if (element instanceof ITranslationUnit) {
			try {
				return ((ITranslationUnit) element).getLanguage();
			} catch (final CoreException e) {
				Activator.getDefault().getLog().log(e.getStatus());
				return GPPLanguage.getDefault();
			}
		}
		final IFile file = ResourceUtil.getFile(input);
		final IContentType contentType = file == null ? getContentType(input) : CCorePlugin.getContentType(
				file.getProject(), file.getName());
		return contentType == null ? GPPLanguage.getDefault() : LanguageManager.getInstance().getLanguage(contentType);
	}
}
