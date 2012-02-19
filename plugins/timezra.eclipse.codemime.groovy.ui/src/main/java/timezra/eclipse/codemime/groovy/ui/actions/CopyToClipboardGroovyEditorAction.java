package timezra.eclipse.codemime.groovy.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromEditorAction;
import timezra.eclipse.codemime.groovy.core.generator.GroovyHtmlGenerator;

public class CopyToClipboardGroovyEditorAction extends CopyFromEditorAction {

	private IProject getProject(final IEditorInput editorInput) {
		return editorInput instanceof FileEditorInput ? ((FileEditorInput) editorInput).getFile().getProject() : null;
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		return new GroovyHtmlGenerator(getProject(getEditor().getEditorInput()));
	}
}
