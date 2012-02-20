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
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Region;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Utku Utkan
 */
public abstract class CopyTextAsHtml extends CopyAsHtml {

	@Override
	protected final IDocument getDocument(final ExecutionEvent event) {
		final ITextEditor editor = getEditor(event);
		return editor.getDocumentProvider().getDocument(getEditorInput(event));
	}

	@Override
	protected final IRegion getRegion(final ExecutionEvent event, final IDocument document) {
		final ITextSelection selection = getSelection(event);
		return new Region(selection.getOffset(), selection.getLength());
	}

	private ITextEditor getEditor(final ExecutionEvent event) {
		return (ITextEditor) HandlerUtil.getActiveEditor(event).getAdapter(ITextEditor.class);
	}

	private ITextSelection getSelection(final ExecutionEvent event) {
		return (ITextSelection) HandlerUtil.getCurrentSelection(event);
	}

	protected final IEditorInput getEditorInput(final ExecutionEvent event) {
		return HandlerUtil.getActiveEditorInput(event);
	}
}
