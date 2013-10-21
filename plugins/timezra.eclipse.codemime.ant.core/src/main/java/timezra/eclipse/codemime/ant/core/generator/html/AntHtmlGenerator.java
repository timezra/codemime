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
package timezra.eclipse.codemime.ant.core.generator.html;

import org.eclipse.ant.internal.ui.AntUIPlugin;
import org.eclipse.ant.internal.ui.editor.text.AntDocumentSetupParticipant;
import org.eclipse.ant.internal.ui.editor.text.AntEditorPartitionScanner;
import org.eclipse.ant.internal.ui.editor.text.AntEditorProcInstrScanner;
import org.eclipse.ant.internal.ui.editor.text.AntEditorTagScanner;
import org.eclipse.ant.internal.ui.editor.text.IAntEditorColorConstants;
import org.eclipse.ant.internal.ui.editor.text.MultilineDamagerRepairer;
import org.eclipse.ant.internal.ui.preferences.AntEditorPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
@SuppressWarnings("restriction")
public class AntHtmlGenerator extends RepairingHtmlGenerator {

	public AntHtmlGenerator(final String title) {
		super(title, AntDocumentSetupParticipant.ANT_PARTITIONING);

		final AntEditorProcInstrScanner instructionScanner = new AntEditorProcInstrScanner();
		MultilineDamagerRepairer repairer = new MultilineDamagerRepairer(instructionScanner);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, repairer);

		final AntEditorTagScanner tagScanner = new AntEditorTagScanner();
		repairer = new MultilineDamagerRepairer(tagScanner);
		addRepairer(AntEditorPartitionScanner.XML_TAG, repairer);

		final IPreferenceStore ps = AntUIPlugin.getDefault().getCombinedPreferenceStore();

		int style = getStyle(ps, IAntEditorColorConstants.XML_COMMENT_COLOR);
		final TextAttribute xmlCommentAttribute = new TextAttribute(
				AntUIPlugin.getPreferenceColor(IAntEditorColorConstants.XML_COMMENT_COLOR), null, style);
		repairer = new MultilineDamagerRepairer(null, xmlCommentAttribute);
		addRepairer(AntEditorPartitionScanner.XML_COMMENT, repairer);

		style = getStyle(ps, IAntEditorColorConstants.XML_DTD_COLOR);
		final TextAttribute xmlDtdAttribute = new TextAttribute(
				AntUIPlugin.getPreferenceColor(IAntEditorColorConstants.XML_DTD_COLOR), null, style);
		final MultilineDamagerRepairer dtdDamageRepairer = new MultilineDamagerRepairer(null, xmlDtdAttribute);
		addRepairer(AntEditorPartitionScanner.XML_DTD, dtdDamageRepairer);
	}

	private int getStyle(final IPreferenceStore ps, final String pref) {
		int style = SWT.NORMAL;
		if (ps.getBoolean(pref + AntEditorPreferenceConstants.EDITOR_BOLD_SUFFIX)) {
			style |= SWT.BOLD;
		}
		if (ps.getBoolean(pref + AntEditorPreferenceConstants.EDITOR_ITALIC_SUFFIX)) {
			style |= SWT.ITALIC;
		}
		return style;
	}

}
