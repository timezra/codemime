package timezra.eclipse.codemime.core.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public abstract class CopySimpleResourceAsHtml extends CopyResourceAsHtml<IResource> {
	@Override
	protected final IPath getPath(final ExecutionEvent event) {
		return getSelection(event).getFullPath();
	}

	@Override
	protected final String getName(final ExecutionEvent event) {
		return getSelection(event).getName();
	}
}
