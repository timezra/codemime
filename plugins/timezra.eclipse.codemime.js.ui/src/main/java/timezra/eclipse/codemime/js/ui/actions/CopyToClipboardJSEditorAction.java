package timezra.eclipse.codemime.js.ui.actions;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromEditorAction;
import timezra.eclipse.codemime.js.core.generator.JsHtmlGenerator;

public class CopyToClipboardJSEditorAction extends CopyFromEditorAction {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.jsdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		return new JsHtmlGenerator();
	}
}
