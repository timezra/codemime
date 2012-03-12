package timezra.eclipse.codemime.js.core.generator.html;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.wst.jsdt.internal.ui.JavaScriptPlugin;
import org.eclipse.wst.jsdt.internal.ui.text.JavaCommentScanner;
import org.eclipse.wst.jsdt.internal.ui.text.SingleTokenJavaScanner;
import org.eclipse.wst.jsdt.internal.ui.text.java.JavaCodeScanner;
import org.eclipse.wst.jsdt.internal.ui.text.javadoc.JavaDocScanner;
import org.eclipse.wst.jsdt.ui.text.IColorManager;
import org.eclipse.wst.jsdt.ui.text.IJavaScriptColorConstants;
import org.eclipse.wst.jsdt.ui.text.IJavaScriptPartitions;
import org.eclipse.wst.jsdt.ui.text.JavaScriptTextTools;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

@SuppressWarnings("restriction")
public class JsHtmlGenerator extends RepairingHtmlGenerator {

	public JsHtmlGenerator() {
		super(IJavaScriptPartitions.JAVA_PARTITIONING);

		final JavaScriptTextTools tools = JavaScriptPlugin.getDefault().getJavaTextTools();

		final IColorManager cm = tools.getColorManager();
		final IPreferenceStore ps = JavaScriptPlugin.getDefault().getPreferenceStore();

		final JavaCodeScanner javaCodeScanner = new JavaCodeScanner(cm, ps);
		DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(javaCodeScanner);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, repairer);

		final JavaDocScanner javaDocScanner = new JavaDocScanner(cm, ps);
		repairer = new DefaultDamagerRepairer(javaDocScanner);
		addRepairer(IJavaScriptPartitions.JAVA_DOC, repairer);

		final JavaCommentScanner mlCommentScanner = new JavaCommentScanner(cm, ps,
				IJavaScriptColorConstants.JAVA_MULTI_LINE_COMMENT);
		repairer = new DefaultDamagerRepairer(mlCommentScanner);
		addRepairer(IJavaScriptPartitions.JAVA_MULTI_LINE_COMMENT, repairer);

		final JavaCommentScanner slCommentScanner = new JavaCommentScanner(cm, ps,
				IJavaScriptColorConstants.JAVA_SINGLE_LINE_COMMENT);
		repairer = new DefaultDamagerRepairer(slCommentScanner);
		addRepairer(IJavaScriptPartitions.JAVA_SINGLE_LINE_COMMENT, repairer);

		final SingleTokenJavaScanner stringScanner = new SingleTokenJavaScanner(cm, ps,
				IJavaScriptColorConstants.JAVA_STRING);
		repairer = new DefaultDamagerRepairer(stringScanner);
		addRepairer(IJavaScriptPartitions.JAVA_STRING, repairer);

		repairer = new DefaultDamagerRepairer(stringScanner);
		addRepairer(IJavaScriptPartitions.JAVA_CHARACTER, repairer);
	}
}