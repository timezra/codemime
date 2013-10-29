package timezra.eclipse.codemime.jsp.core.generator.html;

import org.eclipse.jst.jsp.ui.StructuredTextViewerConfigurationJSP;

import timezra.eclipse.codemime.sse.core.generator.html.StructuredTextHtmlGenerator;

public class JspHtmlGenerator extends StructuredTextHtmlGenerator {

	public JspHtmlGenerator(final String title) {
		super(title, new StructuredTextViewerConfigurationJSP());
	}
}
