package timezra.eclipse.codemime.js.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopySimpleResourceAsHtml;
import timezra.eclipse.codemime.js.core.generator.JsHtmlGenerator;

public class CopyJsFileAsHtml extends CopySimpleResourceAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.jsdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator(final ExecutionEvent event) {
		return new JsHtmlGenerator();
	}
}
