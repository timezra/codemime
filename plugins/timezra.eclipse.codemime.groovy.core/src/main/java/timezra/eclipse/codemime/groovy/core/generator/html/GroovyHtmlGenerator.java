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
package timezra.eclipse.codemime.groovy.core.generator.html;

import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.core.preferences.PreferenceConstants;
import org.codehaus.groovy.eclipse.editor.GroovyTagScanner;
import org.codehaus.groovy.eclipse.editor.highlighting.HighlightingExtenderRegistry;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.ui.text.JavaCommentScanner;
import org.eclipse.jdt.internal.ui.text.SingleTokenJavaScanner;
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDocScanner;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaColorConstants;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
@SuppressWarnings("restriction")
public class GroovyHtmlGenerator extends RepairingHtmlGenerator {

	public GroovyHtmlGenerator(final IProject project) {
		super(IJavaPartitions.JAVA_PARTITIONING);

		final IColorManager colorManager = GroovyPlugin.getDefault().getTextTools().getColorManager();
		final IPreferenceStore preferenceStore = GroovyPlugin.getDefault().getPreferenceStore();

		addRepairer(IDocument.DEFAULT_CONTENT_TYPE,
				new DefaultDamagerRepairer(createTagScanner(project, colorManager, getHighlightingExtenderRegistry())));

		addRepairer(IJavaPartitions.JAVA_DOC, new DefaultDamagerRepairer(new JavaDocScanner(colorManager,
				preferenceStore)));

		addRepairer(IJavaPartitions.JAVA_MULTI_LINE_COMMENT, new DefaultDamagerRepairer(new JavaCommentScanner(
				colorManager, preferenceStore, IJavaColorConstants.JAVA_MULTI_LINE_COMMENT)));

		addRepairer(IJavaPartitions.JAVA_SINGLE_LINE_COMMENT, new DefaultDamagerRepairer(new JavaCommentScanner(
				colorManager, preferenceStore, IJavaColorConstants.JAVA_SINGLE_LINE_COMMENT)));

		final SingleTokenJavaScanner stringScanner = new SingleTokenJavaScanner(colorManager, preferenceStore,
				PreferenceConstants.GROOVY_EDITOR_HIGHLIGHT_STRINGS_COLOR);
		addRepairer(IJavaPartitions.JAVA_STRING, new DefaultDamagerRepairer(stringScanner));

		addRepairer(IJavaPartitions.JAVA_CHARACTER, new DefaultDamagerRepairer(stringScanner));
	}

	private GroovyTagScanner createTagScanner(final IProject project, final IColorManager colorManager,
			final HighlightingExtenderRegistry registry) {
		return new GroovyTagScanner(colorManager, registry.getAdditionalRulesForProject(project),
				registry.getExtraGroovyKeywordsForProject(project), registry.getExtraGJDKKeywordsForProject(project));
	}

	private HighlightingExtenderRegistry getHighlightingExtenderRegistry() {
		return GroovyPlugin.getDefault().getTextTools().getHighlightingExtenderRegistry();
	}
}
