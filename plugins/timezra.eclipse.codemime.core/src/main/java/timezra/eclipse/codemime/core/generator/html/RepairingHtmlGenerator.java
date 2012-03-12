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
package timezra.eclipse.codemime.core.generator.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;

/**
 * 
 * @author Utku Utkan
 * 
 */
public abstract class RepairingHtmlGenerator extends AbstractHtmlGenerator {

	protected String partitioning;
	protected Map<String, IPresentationRepairer> repairers;

	public RepairingHtmlGenerator(final String partitioning) {
		this.partitioning = partitioning;
		repairers = new HashMap<String, IPresentationRepairer>();
	}

	public void addRepairer(final String contentType, final IPresentationRepairer repairer) {
		repairers.put(contentType, repairer);
	}

	@Override
	protected final List<StyleRange> createStyleRanges(final IDocument document, final IRegion region)
			throws BadLocationException {
		for (final IPresentationRepairer repairer : repairers.values()) {
			repairer.setDocument(document);
		}

		final TextPresentation presentation = createPresentation(document, region);

		@SuppressWarnings("unchecked")
		final Iterator<StyleRange> styleRangeItr = presentation.getAllStyleRangeIterator();
		final List<StyleRange> styleRanges = new ArrayList<StyleRange>();

		while (styleRangeItr.hasNext()) {
			styleRanges.add(styleRangeItr.next());
		}

		return styleRanges;
	}

	private TextPresentation createPresentation(final IDocument document, final IRegion region)
			throws BadLocationException {
		final TextPresentation presentation = new TextPresentation(region, 1000);
		final ITypedRegion[] partitions = TextUtilities.computePartitioning(document, partitioning, region.getOffset(),
				region.getLength(), false);

		for (final ITypedRegion partition : partitions) {
			final IPresentationRepairer repairer = repairers.get(partition.getType());

			if (repairer != null) {
				repairer.createPresentation(presentation, partition);
			}
		}
		return presentation;
	}
}
