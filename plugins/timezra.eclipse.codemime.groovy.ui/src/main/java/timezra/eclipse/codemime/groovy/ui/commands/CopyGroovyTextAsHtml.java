package timezra.eclipse.codemime.groovy.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.groovy.core.generator.html.GroovyHtmlGenerator;

public class CopyGroovyTextAsHtml extends CopyTextAsHtml {

	private IProject getProject(final IEditorInput editorInput) {
		return editorInput instanceof FileEditorInput ? ((FileEditorInput) editorInput).getFile().getProject() : null;
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new GroovyHtmlGenerator(getTitle(event), getProject(getEditorInput(event)));
	}
}
