package timezra.eclipse.codemime.python.core.generator.html;

import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.python.pydev.core.docutils.PyPartitionScanner;
import org.python.pydev.editor.PyCodeScanner;
import org.python.pydev.editor.PyColoredScanner;
import org.python.pydev.editor.PyStringScanner;
import org.python.pydev.plugin.PydevPlugin;
import org.python.pydev.plugin.preferences.AbstractPydevPrefs;
import org.python.pydev.ui.ColorAndStyleCache;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

public class PythonHtmlGenerator extends RepairingHtmlGenerator {

	public PythonHtmlGenerator(final String title) {
		super(title, PyPartitionScanner.PYTHON_PARTITION_TYPE);

		final ColorAndStyleCache colorAndStyleCache = new ColorAndStyleCache(PydevPlugin.getDefault()
				.getPreferenceStore());

		final PyColoredScanner commentScanner = new PyColoredScanner(colorAndStyleCache,
				AbstractPydevPrefs.COMMENT_COLOR);
		addRepairer(PyPartitionScanner.PY_COMMENT, new DefaultDamagerRepairer(commentScanner));

		final PyColoredScanner backquotesScanner = new PyColoredScanner(colorAndStyleCache,
				AbstractPydevPrefs.BACKQUOTES_COLOR);
		addRepairer(PyPartitionScanner.PY_BACKQUOTES, new DefaultDamagerRepairer(backquotesScanner));

		final PyStringScanner singleLineStringScanner1 = new PyStringScanner(colorAndStyleCache);
		addRepairer(PyPartitionScanner.PY_SINGLELINE_STRING1, new DefaultDamagerRepairer(singleLineStringScanner1));

		final PyStringScanner singleLineStringScanner2 = new PyStringScanner(colorAndStyleCache);
		addRepairer(PyPartitionScanner.PY_SINGLELINE_STRING2, new DefaultDamagerRepairer(singleLineStringScanner2));

		final PyStringScanner multiLineStringScanner1 = new PyStringScanner(colorAndStyleCache);
		addRepairer(PyPartitionScanner.PY_MULTILINE_STRING1, new DefaultDamagerRepairer(multiLineStringScanner1));

		final PyStringScanner multiLineStringScanner2 = new PyStringScanner(colorAndStyleCache);
		addRepairer(PyPartitionScanner.PY_MULTILINE_STRING2, new DefaultDamagerRepairer(multiLineStringScanner2));

		final String[] codeScannerKeywords = PyCodeScanner.DEFAULT_KEYWORDS; // TODO: handle CYTHON_KEYWORDS
		final PyCodeScanner defaultScanner = new PyCodeScanner(colorAndStyleCache, codeScannerKeywords);
		addRepairer(PyPartitionScanner.PY_DEFAULT, new DefaultDamagerRepairer(defaultScanner));
	}
}
