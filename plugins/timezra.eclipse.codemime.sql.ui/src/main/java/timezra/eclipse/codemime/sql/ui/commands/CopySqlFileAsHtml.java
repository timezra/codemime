package timezra.eclipse.codemime.sql.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopySimpleResourceAsHtml;
import timezra.eclipse.codemime.sql.core.generator.html.SqlHtmlGenerator;

public class CopySqlFileAsHtml extends CopySimpleResourceAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jface.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new SqlHtmlGenerator(getName(event));
	}
}
