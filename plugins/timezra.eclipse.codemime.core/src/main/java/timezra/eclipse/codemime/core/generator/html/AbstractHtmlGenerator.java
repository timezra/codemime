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

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
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

	private final String title;

	protected AbstractHtmlGenerator(final String title) {
		this.title = title;
	}

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

	private String encodeText(final IDocument document, final StyleRange styleRange) throws BadLocationException {
		return HtmlEncoder.encode(document.get(styleRange.start, styleRange.length));
	}

	private Iterable<Span> collectSpans(final IDocument document, final List<StyleRange> styleRanges)
			throws BadLocationException {
		final Collection<Span> spans = new ArrayList<>();

		for (final StyleRange styleRange : styleRanges) {

			final String text = whitespaceAllowed ? encodeText(document, styleRange)
					: replaceWhiteSpaceCharacters(encodeText(document, styleRange));

			final SpanClass cssClass;
			if (styleRange.isUnstyled()) {
				cssClass = null;
			} else {
				final FontWeight fw = new FontWeight((styleRange.fontStyle & SWT.BOLD) != 0);
				final FontStyle fs = new FontStyle((styleRange.fontStyle & SWT.ITALIC) != 0);
				final TextDecoration td = new TextDecoration(styleRange.underline, styleRange.strikeout);
				final Color fg = new Color(styleRange.foreground.getRed(), styleRange.foreground.getGreen(),
						styleRange.foreground.getBlue());
				cssClass = new SpanClass(fw, fs, td, fg);
			}
			spans.add(new Span(cssClass, text));
		}

		return spans;
	}

	private Iterable<SpanClass> collectCssClasses(final Iterable<Span> spans) {
		final Collection<SpanClass> classes = new LinkedHashSet<>();
		for (final Span span : spans) {
			if (span.spanClass != null) {
				classes.add(span.spanClass);
			}
		}
		return classes;
	}

	private String generateHTML(final IDocument document, final List<StyleRange> styleRanges)
			throws BadLocationException {

		final Iterable<Span> spans = collectSpans(document, styleRanges);
		final Iterable<SpanClass> classes = collectCssClasses(spans);
		final SourceDiv sourceDiv = new SourceDiv(new SourceDivClass("cm_source", font), spans);
		final SourceHtml sourceHtml = new SourceHtml(title, classes, sourceDiv);

		return sourceHtml.buildHtml();
	}

	private String replaceWhiteSpaceCharacters(final String text) {
		final String tab = String.format("%" + tabWidth + "s", "");

		return text.replaceAll("\t", tab).replaceAll(" ", "&nbsp;").replaceAll(EOL, "<br />" + EOL + "  ");
	}

	protected abstract List<StyleRange> createStyleRanges(final IDocument document, final IRegion region)
			throws BadLocationException;

	private static final class SourceHtml {
		private final SourceDiv sourceDiv;
		private final Iterable<SpanClass> classes;
		private final String title;

		SourceHtml(final String title, final Iterable<SpanClass> classes, final SourceDiv sourceDiv) {
			this.title = title;
			this.classes = classes;
			this.sourceDiv = sourceDiv;
		}

		String buildHtml() {
			final StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>").append(EOL) // @formatter:off
			.append("<html>").append(EOL)
			.append("<head>").append(EOL)
			.append("<meta charset=\"UTF-8\">").append(EOL)
			.append("<title>").append(title).append("</title>").append(EOL)
			.append("<style type=\"text/css\">").append(EOL);
			sourceDiv.sourceDivClass.buildStyle(html).append(EOL);
			for (final SpanClass spanClass : classes) {
				spanClass.buildStyle(html).append(EOL);
			}
			html.append("</style>").append(EOL)
			.append("</head>").append(EOL)
			.append("<body>").append(EOL);
			
			sourceDiv.buildHtml(html).append(EOL)
			
			.append("</body>").append(EOL)
			.append("</html>").append(EOL); // @formatter:on

			return html.toString();
		}
	}

	private static final class SourceDiv {
		final SourceDivClass sourceDivClass;
		private final Iterable<Span> spans;

		SourceDiv(final SourceDivClass sourceDivClass, final Iterable<Span> spans) {
			this.sourceDivClass = sourceDivClass;
			this.spans = spans;
		}

		StringBuilder buildHtml(final StringBuilder b) {
			b.append("<div class=\"").append(sourceDivClass.name).append("\">").append(EOL);
			for (final Span span : spans) {
				span.buildHtml(b);
			}
			b.append(EOL).append("</div>");
			return b;
		}
	}

	private static final class Span {
		final SpanClass spanClass;
		private final String text;

		Span(final SpanClass cssClass, final String text) {
			spanClass = cssClass;
			this.text = text;
		}

		StringBuilder buildHtml(final StringBuilder b) {
			return spanClass == null ? b.append(text) : b.append("<span class=\"").append(spanClass.name())
					.append("\">").append(text).append("</span>");
		}
	}

	private static final class TextDecoration {
		private final boolean underline;
		private final boolean lineThrough;

		TextDecoration(final boolean underline, final boolean lineThrough) {
			this.underline = underline;
			this.lineThrough = lineThrough;
		}

		String encodeName() {
			if (underline && lineThrough) {
				return "ul";
			}
			if (underline) {
				return "u";
			}
			if (lineThrough) {
				return "l";
			}
			return "n";
		}

		boolean hasStyle() {
			return underline || lineThrough;
		}

		StringBuilder buildStyle(final StringBuilder b) {
			if (underline && lineThrough) {
				b.append("text-decoration: line-through underline");
			}
			if (underline) {
				b.append("text-decoration: underline");
			}
			if (lineThrough) {
				b.append("text-decoration: line-through");
			}
			return b;
		}

		@Override
		public boolean equals(final Object that) {
			return reflectionEquals(this, that);
		}

		@Override
		public int hashCode() {
			return reflectionHashCode(this);
		}
	}

	private static final class Color {
		final int color;

		Color(final int red, final int green, final int blue) {
			color = red << 16 | green << 8 | blue;
		}

		String encodeName() {
			return Integer.toHexString(color).toUpperCase();
		}

		StringBuilder buildStyle(final StringBuilder b) {
			return b.append("color: #").append(String.format("%6s", encodeName()).replace(' ', '0'));
		}

		@Override
		public boolean equals(final Object that) {
			return reflectionEquals(this, that);
		}

		@Override
		public int hashCode() {
			return reflectionHashCode(this);
		}
	}

	private static final class FontWeight {
		private final boolean bold;

		FontWeight(final boolean bold) {
			this.bold = bold;
		}

		String encodeName() {
			return bold ? "b" : "n";
		}

		boolean hasStyle() {
			return bold;
		}

		StringBuilder buildStyle(final StringBuilder b) {
			if (bold) {
				b.append("font-weight: bold");
			}
			return b;
		}

		@Override
		public boolean equals(final Object that) {
			return reflectionEquals(this, that);
		}

		@Override
		public int hashCode() {
			return reflectionHashCode(this);
		}
	}

	private static final class FontStyle {
		private final boolean italic;

		FontStyle(final boolean italic) {
			this.italic = italic;
		}

		String encodeName() {
			return italic ? "i" : "n";
		}

		boolean hasStyle() {
			return italic;
		}

		StringBuilder buildStyle(final StringBuilder b) {
			if (italic) {
				b.append("font-style: italic");
			}
			return b;
		}

		@Override
		public boolean equals(final Object that) {
			return reflectionEquals(this, that);
		}

		@Override
		public int hashCode() {
			return reflectionHashCode(this);
		}
	}

	private static final class SourceDivClass {

		private final FontData font;
		final String name;

		SourceDivClass(final String name, final FontData font) {
			this.name = name;
			this.font = font;
		}

		StringBuilder buildStyle(final StringBuilder b) {
			final String tab = "  ";
			b.append("div.").append(name).append(" {").append(EOL);
			b.append(tab).append("overflow: auto;").append(EOL);
			b.append(tab).append("padding: 1em;").append(EOL);
			b.append(tab).append("background-color: white;").append(EOL);
			b.append(tab).append("border-style: dashed;").append(EOL);
			b.append(tab).append("border-width: thin;").append(EOL);
			b.append(tab).append("font-family: ").append(font.getName().toLowerCase()).append(";").append(EOL);
			b.append(tab).append("font-size: ").append(font.getHeight()).append("pt;").append(EOL);
			b.append("}");
			return b;
		}
	}

	private static final class SpanClass {
		private final FontWeight fontWeight;
		private final FontStyle fontStyle;
		private final TextDecoration textDecoration;
		private final Color foreground;

		SpanClass(final FontWeight fontWeight, final FontStyle fontStyle, final TextDecoration textDecoration,
				final Color foreground) {
			this.fontWeight = fontWeight;
			this.fontStyle = fontStyle;
			this.textDecoration = textDecoration;
			this.foreground = foreground;
		}

		String name() {
			return join(
					asList("cm", fontWeight.encodeName(), fontStyle.encodeName(), textDecoration.encodeName(),
							foreground.encodeName()), "_");
		}

		StringBuilder buildStyle(final StringBuilder b) {
			final String tab = "  ";
			b.append("span.").append(name()).append(" {").append(EOL);
			if (fontWeight.hasStyle()) {
				b.append(tab);
				fontWeight.buildStyle(b).append(";").append(EOL);
			}
			if (fontStyle.hasStyle()) {
				b.append(tab);
				fontStyle.buildStyle(b).append(";").append(EOL);
			}
			if (textDecoration.hasStyle()) {
				b.append(tab);
				textDecoration.buildStyle(b).append(";").append(EOL);
			}
			b.append(tab);
			foreground.buildStyle(b).append(";").append(EOL);
			b.append("}");
			return b;
		}

		@Override
		public boolean equals(final Object that) {
			return reflectionEquals(this, that);
		}

		@Override
		public int hashCode() {
			return reflectionHashCode(this);
		}
	}
}
