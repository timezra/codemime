package timezra.eclipse.codemime.scala.core.generator.html;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.JavaCommentScanner;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaColorConstants;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;

import scala.tools.eclipse.ScalaPlugin;
import scala.tools.eclipse.lexical.ScalaCodeScanner;
import scala.tools.eclipse.lexical.ScalaPartitions;
import scala.tools.eclipse.lexical.ScaladocTokenScanner;
import scala.tools.eclipse.lexical.SingleTokenScanner;
import scala.tools.eclipse.lexical.StringTokenScanner;
import scala.tools.eclipse.lexical.XmlCDATAScanner;
import scala.tools.eclipse.lexical.XmlCommentScanner;
import scala.tools.eclipse.lexical.XmlPIScanner;
import scala.tools.eclipse.lexical.XmlTagScanner;
import scala.tools.eclipse.properties.syntaxcolouring.ScalaSyntaxClasses;
import scalariform.ScalaVersions;
import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

@SuppressWarnings("restriction")
public class ScalaHtmlGenerator extends RepairingHtmlGenerator {

	public ScalaHtmlGenerator(final String title, final IProject project) {
		super(title, IJavaPartitions.JAVA_PARTITIONING);

		final IColorManager colorManager = JavaPlugin.getDefault().getJavaTextTools().getColorManager();
		final IPreferenceStore scalaPreferenceStore = ScalaPlugin.plugin().getPreferenceStore();
		final IPreferenceStore javaPreferenceStore = JavaPlugin.getDefault().getPreferenceStore();

		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, new DefaultDamagerRepairer(new ScalaCodeScanner(
				scalaPreferenceStore, ScalaVersions.DEFAULT())));

		addRepairer(
				IJavaPartitions.JAVA_DOC,
				new DefaultDamagerRepairer(new ScaladocTokenScanner(ScalaSyntaxClasses.SCALADOC(), ScalaSyntaxClasses
						.SCALADOC_ANNOTATION(), ScalaSyntaxClasses.SCALADOC_MACRO(), scalaPreferenceStore)));

		addRepairer(ScalaPartitions.SCALADOC_CODE_BLOCK(), new DefaultDamagerRepairer(new SingleTokenScanner(
				ScalaSyntaxClasses.SCALADOC_CODE_BLOCK(), scalaPreferenceStore)));

		addRepairer(IJavaPartitions.JAVA_SINGLE_LINE_COMMENT, new DefaultDamagerRepairer(new JavaCommentScanner(
				colorManager, javaPreferenceStore, IJavaColorConstants.JAVA_SINGLE_LINE_COMMENT)));

		addRepairer(IJavaPartitions.JAVA_MULTI_LINE_COMMENT, new DefaultDamagerRepairer(new JavaCommentScanner(
				colorManager, javaPreferenceStore, IJavaColorConstants.JAVA_MULTI_LINE_COMMENT)));

		addRepairer(IJavaPartitions.JAVA_STRING,
				new DefaultDamagerRepairer(new StringTokenScanner(ScalaSyntaxClasses.ESCAPE_SEQUENCE(),
						ScalaSyntaxClasses.STRING(), scalaPreferenceStore)));

		addRepairer(IJavaPartitions.JAVA_CHARACTER, new DefaultDamagerRepairer(new StringTokenScanner(
				ScalaSyntaxClasses.ESCAPE_SEQUENCE(), ScalaSyntaxClasses.CHARACTER(), scalaPreferenceStore)));

		addRepairer(ScalaPartitions.SCALA_MULTI_LINE_STRING(), new DefaultDamagerRepairer(new SingleTokenScanner(
				ScalaSyntaxClasses.MULTI_LINE_STRING(), scalaPreferenceStore)));

		addRepairer(ScalaPartitions.XML_TAG(), new DefaultDamagerRepairer(new XmlTagScanner(scalaPreferenceStore)));

		addRepairer(ScalaPartitions.XML_CDATA(), new DefaultDamagerRepairer(new XmlCDATAScanner(scalaPreferenceStore)));

		addRepairer(ScalaPartitions.XML_COMMENT(), new DefaultDamagerRepairer(new XmlCommentScanner(
				scalaPreferenceStore)));

		addRepairer(ScalaPartitions.XML_PI(), new DefaultDamagerRepairer(new XmlPIScanner(scalaPreferenceStore)));

		addRepairer(ScalaPartitions.XML_PCDATA(),
				new DefaultDamagerRepairer(new SingleTokenScanner(ScalaSyntaxClasses.DEFAULT(), scalaPreferenceStore)));
	}
}
