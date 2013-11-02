package timezra.eclipse.codemime.sql.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;

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

	@Override
	protected IDocument setup(final IDocument document) {
		new SQLEditorDocumentSetupParticipant().setup(document);
		return super.setup(document);
	}
}
