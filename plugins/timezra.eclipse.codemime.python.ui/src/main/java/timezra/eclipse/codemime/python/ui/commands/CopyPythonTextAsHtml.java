package timezra.eclipse.codemime.python.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.python.core.generator.html.PythonHtmlGenerator;

public class CopyPythonTextAsHtml extends CopyTextAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jface.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new PythonHtmlGenerator(getTitle(event));
	}
}
