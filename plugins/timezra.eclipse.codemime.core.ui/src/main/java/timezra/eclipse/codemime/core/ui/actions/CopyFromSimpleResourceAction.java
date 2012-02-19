package timezra.eclipse.codemime.core.ui.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public abstract class CopyFromSimpleResourceAction extends CopyFromResourceAction<IResource> {
	@Override
	protected final IPath getPath() {
		return getSelection().getFullPath();
	}
}
