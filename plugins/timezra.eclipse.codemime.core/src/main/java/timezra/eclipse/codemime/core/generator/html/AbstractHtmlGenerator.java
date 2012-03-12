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

import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.FontData;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;

/**
 * @author Utku Utkan
 */
public abstract class AbstractHtmlGenerator implements CodeMimeGenerator {

	private static final String EOL = System.getProperty("line.separator");

	private boolean whitespaceAllowed;
	private int tabWidth;
	private FontData font;

	@Override
	public void setWhitespaceAllowed(final boolean whitespaceAllowed) {
		this.whitespaceAllowed = whitespaceAllowed;
	}

	@Override
	public void setTabWidth(final int tabWidth) {
		this.tabWidth = tabWidth;
	}

	@Override
	public void setFont(final FontData font) {
		this.font = font;
	}

	@Override
	public final String generate(final IDocument document, final IRegion region) {
		try {
			return generateHTML(document, createStyleRanges(document, region));
		} catch (final BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	private String generateHTML(final IDocument document, final List<StyleRange> styleRanges)
			throws BadLocationException {
		final StringBuilder html = new StringBuilder();

		html.append("<span style=\"");
		html.append("font-family: ");
		html.append(font.getName().toLowerCase());
		html.append("; font-size: ");
		html.append(font.getHeight());
		html.append("pt;\">");

		for (final StyleRange styleRange : styleRanges) {

			String text = document.get(styleRange.start, styleRange.length);

			text = HtmlEncoder.encode(text);

			if (!whitespaceAllowed) {
				text = replaceWhiteSpaceCharacters(text);
			}

			if (styleRange.isUnstyled()) {
				html.append(text);
				continue;
			}

			final StringBuilder builder = new StringBuilder();
			builder.append("<span style=\"");

			switch (styleRange.fontStyle) {
			case SWT.BOLD:
				builder.append("font-weight: bold;");
				break;
			case SWT.ITALIC:
				builder.append("font-style: italic;");
				break;
			case SWT.BOLD | SWT.ITALIC:
				builder.append("font-weight: bold; font-style: italic;");
				break;
			}

			if (styleRange.underline) {
				if (styleRange.fontStyle != 0) {
					builder.append(" ");
				}
				builder.append("text-decoration:underline");
				if (!styleRange.strikeout) {
					builder.append(";");
				}
			}

			if (styleRange.strikeout) {
				if (styleRange.underline) {
					builder.append(" line-through;");
				} else {
					if (styleRange.fontStyle != 0) {
						builder.append(" ");
					}
					builder.append("text-decoration:line-through;");
				}
			}

			if (styleRange.foreground != null) {
				if (styleRange.fontStyle != 0 || styleRange.underline || styleRange.strikeout) {
					builder.append(" ");
				}

				builder.append("color: ");
				builder.append("rgb(");
				builder.append(styleRange.foreground.getRed());
				builder.append(",");
				builder.append(styleRange.foreground.getGreen());
				builder.append(",");
				builder.append(styleRange.foreground.getBlue());
				builder.append(");");
			}

			builder.append("\">");
			builder.append(text);
			builder.append("</span>");

			html.append(builder);
		}

		html.append("</span>");

		return html.toString();
	}

	private String replaceWhiteSpaceCharacters(final String text) {
		final StringBuilder tab = new StringBuilder();

		for (int i = 0; i < tabWidth; i++) {
			tab.append("&nbsp;");
		}

		return text.replaceAll(" ", "&nbsp;").replaceAll("\t", tab.toString()).replaceAll(EOL, "<br />" + EOL);
	}

	protected abstract List<StyleRange> createStyleRanges(final IDocument document, final IRegion region)
			throws BadLocationException;
}
