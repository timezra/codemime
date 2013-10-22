package timezra.eclipse.codemime.scala.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.scala.core.generator.html.ScalaHtmlGenerator;

public class CopyScalaTextAsHtml extends CopyTextAsHtml {

	private IProject getProject(final IEditorInput editorInput) {
		return editorInput instanceof FileEditorInput ? ((FileEditorInput) editorInput).getFile().getProject() : null;
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		return new ScalaHtmlGenerator(getTitle(event), getProject(getEditorInput(event)));
	}
}
