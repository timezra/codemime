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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Utku Utkan
 */
public abstract class CopyFromEditorAction extends CodeMimeAction implements IEditorActionDelegate {

	private ITextEditor editor;

	@Override
	public final void setActiveEditor(final IAction action, final IEditorPart targetEditor) {
		editor = (ITextEditor) targetEditor;
	}

	@Override
	public final void selectionChanged(final IAction action, final ISelection selection) {
	}

	protected final ITextEditor getEditor() {
		return editor;
	}

	@Override
	protected final IDocument getDocument() {
		return editor.getDocumentProvider().getDocument(editor.getEditorInput());
	}

	@Override
	protected final IRegion getRegion(final IDocument document) {
		final ITextSelection selection = (ITextSelection) editor.getEditorSite().getSelectionProvider().getSelection();
		return new Region(selection.getOffset(), selection.getLength());
	}
}
