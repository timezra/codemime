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
package timezra.eclipse.codemime.core.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Utku Utkan
 */
public abstract class CopyResourceAsHtml<T> extends CopyAsHtml {

	@SuppressWarnings("unchecked")
	protected final T getSelection(final ExecutionEvent event) {
		return (T) ((IStructuredSelection) HandlerUtil.getCurrentSelection(event)).getFirstElement();
	}

	@Override
	protected final IDocument getDocument(final ExecutionEvent event) {
		final ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		final IPath path = getPath(event);
		try {
			manager.connect(path, LocationKind.IFILE, null);
		} catch (final CoreException e) {
			throw new RuntimeException(e);
		}
		return manager.getTextFileBuffer(path, LocationKind.IFILE).getDocument();
	}

	@Override
	protected final IRegion getRegion(final ExecutionEvent event, final IDocument document) {
		return new Region(0, setup(getDocument(event)).getLength());
	}

	protected abstract IPath getPath(final ExecutionEvent event);

	protected abstract String getName(final ExecutionEvent event);

	protected IDocument setup(final IDocument document) {
		return document;
	}
}
