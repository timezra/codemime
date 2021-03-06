package timezra.eclipse.codemime.php.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.php.core.generator.html.PhpHtmlGenerator;

public class CopyPhpTextAsHtml extends CopyTextAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.sse.ui.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new PhpHtmlGenerator(getTitle(event));
	}
}
