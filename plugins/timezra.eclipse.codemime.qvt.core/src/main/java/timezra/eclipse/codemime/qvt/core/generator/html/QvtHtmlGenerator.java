package timezra.eclipse.codemime.qvt.core.generator.html;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.Activator;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.NonRuleBasedDamagerRepairer;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.QvtPartitionScanner;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.QvtScanner;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.colorer.IQVTColors;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.colorer.QVTColorManager;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

@SuppressWarnings("restriction")
public class QvtHtmlGenerator extends RepairingHtmlGenerator {

	public QvtHtmlGenerator(final String title) {
		super(title, QvtPartitionScanner.QVT_PARTITIONING);

		final Activator qvtPluginActivator = Activator.getDefault();
		final QVTColorManager myColorManager = new QVTColorManager(qvtPluginActivator.getPreferenceStore(),
				qvtPluginActivator.getColorManager());

		final QvtScanner scanner = new QvtScanner(myColorManager);
		scanner.setDefaultReturnToken(myColorManager.getColor(IQVTColors.DEFAULT).createToken());
		final DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, dr);

		final NonRuleBasedDamagerRepairer slCommentRepairer = new NonRuleBasedDamagerRepairer(myColorManager.getColor(
				IQVTColors.LINE_COMMENT).createToken());
		addRepairer(QvtPartitionScanner.QVT_SL_COMMENT, slCommentRepairer);

		final NonRuleBasedDamagerRepairer mlCommentRepairer = new NonRuleBasedDamagerRepairer(myColorManager.getColor(
				IQVTColors.MULTILINE_COMMENT).createToken());
		addRepairer(QvtPartitionScanner.QVT_ML_COMMENT, mlCommentRepairer);

		final DefaultDamagerRepairer mlc = new DefaultDamagerRepairer(new CommentScanner(myColorManager,
				IQVTColors.MULTILINE_COMMENT));
		addRepairer(QvtPartitionScanner.QVT_ML_COMMENT, mlc);

		final DefaultDamagerRepairer slc = new DefaultDamagerRepairer(new CommentScanner(myColorManager,
				IQVTColors.MULTILINE_COMMENT));
		addRepairer(QvtPartitionScanner.QVT_SL_COMMENT, slc);

		final NonRuleBasedDamagerRepairer docRepairer = new NonRuleBasedDamagerRepairer(myColorManager.getColor(
				IQVTColors.DOC_OTHERS).createToken());
		addRepairer(QvtPartitionScanner.QVT_DOCUMENTATION, docRepairer);

		final DefaultDamagerRepairer d = new DefaultDamagerRepairer(new CommentScanner(myColorManager,
				IQVTColors.DOC_OTHERS));
		addRepairer(QvtPartitionScanner.QVT_DOCUMENTATION, d);

		final NonRuleBasedDamagerRepairer stringRepairer = new NonRuleBasedDamagerRepairer(myColorManager.getColor(
				IQVTColors.STRING).createToken());
		addRepairer(QvtPartitionScanner.QVT_STRING, stringRepairer);

		// ColorDescriptor[] semanticHighlightings = myColorManager.getSemanticHighlightings();
		// for (int i = 0; i < semanticHighlightings.length; i++) {
		// semanticHighlightings[i].createHighlighting();
		// }
		// SemanticHighlightingPresenter presenter = new SemanticHighlightingPresenter();
		// presenter.install(sourceViewer, presentationReconciler);
		// QvtReconcilingStrategy strategy = new QvtReconcilingStrategy(myEditor);
		// QvtReconciler reconciler = new QvtReconciler(myEditor, strategy, false);
		// reconciler.setDelay(RECONCILER_DELAY);
		// fReconciler.install(null, sourceViewer, presenter, semanticHighlightings);
	}
}
