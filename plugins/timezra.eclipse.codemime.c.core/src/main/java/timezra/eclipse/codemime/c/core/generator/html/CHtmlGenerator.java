package timezra.eclipse.codemime.c.core.generator.html;

import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.model.ICLanguageKeywords;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.internal.ui.text.CCodeScanner;
import org.eclipse.cdt.internal.ui.text.CCommentScanner;
import org.eclipse.cdt.internal.ui.text.CPreprocessorScanner;
import org.eclipse.cdt.internal.ui.text.SingleTokenCScanner;
import org.eclipse.cdt.internal.ui.text.TokenStore;
import org.eclipse.cdt.internal.ui.text.doctools.DocCommentOwnerManager;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.cdt.ui.ILanguageUI;
import org.eclipse.cdt.ui.text.ICColorConstants;
import org.eclipse.cdt.ui.text.ICPartitions;
import org.eclipse.cdt.ui.text.IColorManager;
import org.eclipse.cdt.ui.text.ITokenStore;
import org.eclipse.cdt.ui.text.ITokenStoreFactory;
import org.eclipse.cdt.ui.text.doctools.IDocCommentViewerConfiguration;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

@SuppressWarnings("restriction")
public class CHtmlGenerator extends RepairingHtmlGenerator {
	public CHtmlGenerator(final String title, final ILanguage language) {
		super(title, CUIPlugin.getDefault().getTextTools().getDocumentPartitioning());

		final IPreferenceStore store = CUIPlugin.getDefault().getCombinedPreferenceStore();
		final IColorManager colorManager = CUIPlugin.getDefault().getTextTools().getColorManager();

		final ITokenStoreFactory tokenStoreFactory = getTokenStoreFactory(store, colorManager);

		final RuleBasedScanner scanner = getCodeScanner(language, tokenStoreFactory);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, new DefaultDamagerRepairer(scanner));

		addRepairer(ICPartitions.C_SINGLE_LINE_COMMENT, new DefaultDamagerRepairer(
				getSinglelineCommentScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_MULTI_LINE_COMMENT, new DefaultDamagerRepairer(
				getMultilineCommentScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_SINGLE_LINE_DOC_COMMENT, new DefaultDamagerRepairer(
				getSinglelineDocCommentScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_MULTI_LINE_DOC_COMMENT, new DefaultDamagerRepairer(
				getMultilineDocCommentScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_STRING, new DefaultDamagerRepairer(getStringScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_CHARACTER, new DefaultDamagerRepairer(getStringScanner(tokenStoreFactory)));

		addRepairer(ICPartitions.C_PREPROCESSOR,
				new DefaultDamagerRepairer(getPreprocessorScanner(language, tokenStoreFactory)));
	}

	private ITokenScanner getStringScanner(final ITokenStoreFactory tokenStoreFactory) {
		return new SingleTokenCScanner(tokenStoreFactory, ICColorConstants.C_STRING);
	}

	private ITokenScanner getMultilineCommentScanner(final ITokenStoreFactory tokenStoreFactory) {
		return new CCommentScanner(tokenStoreFactory, ICColorConstants.C_MULTI_LINE_COMMENT);
	}

	private ITokenScanner getSinglelineCommentScanner(final ITokenStoreFactory tokenStoreFactory) {
		return new CCommentScanner(tokenStoreFactory, ICColorConstants.C_SINGLE_LINE_COMMENT);
	}

	private RuleBasedScanner getCodeScanner(final ILanguage language, final ITokenStoreFactory tokenStoreFactory) {
		final ICLanguageKeywords keywords = (ICLanguageKeywords) language.getAdapter(ICLanguageKeywords.class);
		if (keywords != null) {
			return new CCodeScanner(tokenStoreFactory, keywords);
		}
		final ILanguageUI languageUI = (ILanguageUI) language.getAdapter(ILanguageUI.class);
		return languageUI == null ? new CCodeScanner(tokenStoreFactory, GPPLanguage.getDefault()) : languageUI
				.getCodeScanner();
	}

	private ITokenScanner getSinglelineDocCommentScanner(final ITokenStoreFactory tokenStoreFactory) {
		final IDocCommentViewerConfiguration owner = DocCommentOwnerManager.getInstance()
				.getCommentOwner(ResourcesPlugin.getWorkspace().getRoot()).getSinglelineConfiguration();
		final ITokenScanner singlelineDocCommentScanner = owner.createCommentScanner(tokenStoreFactory);
		return singlelineDocCommentScanner == null ? getSinglelineCommentScanner(tokenStoreFactory)
				: singlelineDocCommentScanner;
	}

	private ITokenScanner getMultilineDocCommentScanner(final ITokenStoreFactory tokenStoreFactory) {
		final IDocCommentViewerConfiguration owner = DocCommentOwnerManager.getInstance()
				.getCommentOwner(ResourcesPlugin.getWorkspace().getRoot()).getMultilineConfiguration();
		final ITokenScanner multilineDocCommentScanner = owner.createCommentScanner(tokenStoreFactory);
		return multilineDocCommentScanner == null ? getMultilineCommentScanner(tokenStoreFactory)
				: multilineDocCommentScanner;
	}

	private RuleBasedScanner getPreprocessorScanner(final ILanguage language, final ITokenStoreFactory tokenStoreFactory) {
		final ICLanguageKeywords keywords = (ICLanguageKeywords) language.getAdapter(ICLanguageKeywords.class);
		return new CPreprocessorScanner(tokenStoreFactory, keywords == null ? (ICLanguageKeywords) GPPLanguage
				.getDefault().getAdapter(ICLanguageKeywords.class) : keywords);
	}

	private ITokenStoreFactory getTokenStoreFactory(final IPreferenceStore store, final IColorManager colorManager) {
		return new ITokenStoreFactory() {
			@Override
			public ITokenStore createTokenStore(final String[] propertyColorNames) {
				return new TokenStore(colorManager, store, propertyColorNames);
			}
		};
	}
}
