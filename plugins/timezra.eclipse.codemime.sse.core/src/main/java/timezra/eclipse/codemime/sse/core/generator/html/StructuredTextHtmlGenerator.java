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
package timezra.eclipse.codemime.sse.core.generator.html;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.provisional.style.Highlighter;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;

import timezra.eclipse.codemime.core.generator.html.AbstractHtmlGenerator;

/**
 * @author Utku Utkan
 */
@SuppressWarnings("restriction")
public abstract class StructuredTextHtmlGenerator extends AbstractHtmlGenerator {

	private final StructuredTextViewerConfiguration config;

	public StructuredTextHtmlGenerator(final String title, final StructuredTextViewerConfiguration config) {
		super(title);
		this.config = config;
	}

	@Override
	protected final List<StyleRange> createStyleRanges(final IDocument document, final IRegion region) {
		final Highlighter highlighter = new Highlighter();
		final String[] fConfiguredContentTypes = config.getConfiguredContentTypes(null);

		for (final String t : fConfiguredContentTypes) {
			final LineStyleProvider[] providers = config.getLineStyleProviders(null, t);

			if (providers != null) {
				for (final LineStyleProvider provider : providers) {
					highlighter.addProvider(t, provider);
				}
			}
		}

		if (highlighter != null) {
			highlighter.setDocumentPartitioning(config.getConfiguredDocumentPartitioning(null));
			highlighter.setDocument((IStructuredDocument) document);
		}

		final StyleRange[] styleRanges = highlighter.lineGetStyle(region.getOffset(), region.getLength());
		final List<StyleRange> styleRangeList = new ArrayList<StyleRange>();

		for (int i = 0, expectedOffset = region.getOffset(); i < styleRanges.length; i++) {
			final StyleRange styleRange = styleRanges[i];

			if (styleRange.start > expectedOffset) {
				styleRangeList.add(new StyleRange(expectedOffset, styleRange.start - expectedOffset, null, null));
			}

			expectedOffset = styleRange.start + styleRange.length;

			styleRangeList.add(styleRange);
		}

		return styleRangeList;
	}
}
