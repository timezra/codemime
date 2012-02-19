package timezra.eclipse.codemime.groovy.ui.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromResourceAction;
import timezra.eclipse.codemime.groovy.core.generator.GroovyHtmlGenerator;

public class CopyToClipboardGroovyFileAction extends CopyFromResourceAction<ICompilationUnit> {

	@Override
	protected IPath getPath() {
		return getSelection().getPath();
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		final ICompilationUnit theSelection = getSelection();
		return new GroovyHtmlGenerator(theSelection.getJavaProject() == null ? null : theSelection
				.getJavaProject().getProject());
	}
}
