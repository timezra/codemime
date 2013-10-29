package timezra.eclipse.codemime.jsp.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopySimpleResourceAsHtml;
import timezra.eclipse.codemime.jsp.core.generator.html.JspHtmlGenerator;

public class CopyJspFileAsHtml extends CopySimpleResourceAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.sse.ui.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new JspHtmlGenerator(getName(event));
	}
}
