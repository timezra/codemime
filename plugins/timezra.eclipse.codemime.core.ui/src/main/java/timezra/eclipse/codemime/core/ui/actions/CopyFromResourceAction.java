/*******************************************************************************
 * Copyright (c) 2007 Hoydaa Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Utku Utkan (Hoydaa Inc.) - initial API and implementation
 *******************************************************************************/
package timezra.eclipse.codemime.core.ui.actions;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Utku Utkan
 */
public abstract class CopyFromResourceAction<T> extends CodeMimeAction implements IObjectActionDelegate {

	private T selection;

	@Override
	public final void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void selectionChanged(final IAction action, final ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) {
			this.selection = null;
			return;
		}
		final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		this.selection = (T) structuredSelection.getFirstElement();
	}

	protected final T getSelection() {
		return selection;
	}

	@Override
	protected final IDocument getDocument() {
		final ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		final IPath path = getPath();
		try {
			manager.connect(path, LocationKind.IFILE, null);
		} catch (final CoreException e) {
			throw new RuntimeException(e);
		}
		return manager.getTextFileBuffer(path, LocationKind.IFILE).getDocument();
	}

	@Override
	protected final IRegion getRegion(final IDocument document) {
		return new Region(0, document.getLength());
	}

	protected abstract IPath getPath();
}
