package timezra.eclipse.codemime.js.ui.actions;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromSimpleResourceAction;
import timezra.eclipse.codemime.js.core.generator.JsHtmlGenerator;

public class CopyToClipboardJSFileAction extends CopyFromSimpleResourceAction {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.jsdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		return new JsHtmlGenerator();
	}
}
