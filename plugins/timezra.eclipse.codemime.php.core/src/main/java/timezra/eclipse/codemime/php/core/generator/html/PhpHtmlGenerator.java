package timezra.eclipse.codemime.php.core.generator.html;

import org.eclipse.php.internal.ui.editor.configuration.PHPStructuredTextViewerConfiguration;

import timezra.eclipse.codemime.sse.core.generator.html.StructuredTextHtmlGenerator;

@SuppressWarnings("restriction")
public class PhpHtmlGenerator extends StructuredTextHtmlGenerator {
	public PhpHtmlGenerator(final String title) {
		super(title, new PHPStructuredTextViewerConfiguration());
	}
}
