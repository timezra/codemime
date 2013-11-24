package timezra.eclipse.codemime.php.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopySimpleResourceAsHtml;
import timezra.eclipse.codemime.php.core.generator.html.PhpHtmlGenerator;

public class CopyPhpFileAsHtml extends CopySimpleResourceAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jface.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new PhpHtmlGenerator(getName(event));
	}
}
