package timezra.eclipse.codemime.groovy.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyResourceAsHtml;
import timezra.eclipse.codemime.groovy.core.generator.html.GroovyHtmlGenerator;

public class CopyGroovyFileAsHtml extends CopyResourceAsHtml<ICompilationUnit> {

	@Override
	protected IPath getPath(final ExecutionEvent event) {
		return getSelection(event).getPath();
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected CodeMimeGenerator createGenerator(final ExecutionEvent event) {
		final ICompilationUnit theSelection = getSelection(event);
		return new GroovyHtmlGenerator(theSelection.getJavaProject() == null ? null : theSelection.getJavaProject()
				.getProject());
	}
}
