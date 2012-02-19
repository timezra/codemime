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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.Activator;
import timezra.eclipse.codemime.core.ui.preferences.PreferenceConstants;

/**
 * @author Utku Utkan
 */
abstract class CodeMimeAction implements IActionDelegate {

	@Override
	public final void run(final IAction action) {
		final IDocument document = getDocument();
		final IRegion region = getRegion(document);

		final IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		@SuppressWarnings("restriction")
		final IPreferenceStore editorsPs = org.eclipse.ui.internal.editors.text.EditorsPlugin.getDefault()
				.getPreferenceStore();
		@SuppressWarnings("restriction")
		final IPreferenceStore workbenchPs = org.eclipse.ui.internal.WorkbenchPlugin.getDefault().getPreferenceStore();

		final HtmlGenerator generator = createGenerator();
		generator.setWhitespaceAllowed(ps.getBoolean(PreferenceConstants.ALLOW_WHITESPACES));
		generator.setTabWidth(editorsPs.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH));
		generator.setFont(PreferenceConverter.getFontData(workbenchPs, getFontPreferenceName()));

		final String mimedCode = generator.generate(document, region);

		final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		final StringSelection copy = new StringSelection(mimedCode);
		clipboard.setContents(copy, copy);
	}

	protected abstract IDocument getDocument();

	protected abstract IRegion getRegion(final IDocument document);

	protected abstract String getFontPreferenceName();

	protected abstract HtmlGenerator createGenerator();
}
