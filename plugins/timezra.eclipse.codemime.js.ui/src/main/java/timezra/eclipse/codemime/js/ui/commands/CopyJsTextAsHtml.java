package timezra.eclipse.codemime.js.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.js.core.generator.html.JsHtmlGenerator;

public class CopyJsTextAsHtml extends CopyTextAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.jsdt.ui.editors.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new JsHtmlGenerator(getTitle(event));
	}
}
