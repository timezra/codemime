package timezra.eclipse.codemime.sql.core.generator.html;

import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCodeScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

public class SqlHtmlGenerator extends RepairingHtmlGenerator {

	public SqlHtmlGenerator(final String title) {
		super(title, ISQLPartitions.SQL_PARTITIONING);

		final SQLColorProvider colorProvider = SQLEditorPlugin.getDefault().getSQLColorProvider();
		final SQLCodeScanner sqlCodeScanner = new SQLCodeScanner(colorProvider);

		// TODO: we need a way to get vendor-specific connection info
		final SQLDevToolsConfiguration factory = SQLToolsFacade
				.getConfigurationByVendorIdentifier(SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO
						.getDatabaseVendorDefinitionId());
		if (factory != null) {
			sqlCodeScanner.setSQLSyntax(factory.getSQLService().getSQLSyntax());
		}
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, new DefaultDamagerRepairer(sqlCodeScanner));

		addRepairer(ISQLPartitions.SQL_MULTILINE_COMMENT, new DefaultDamagerRepairer(new SingleTokenScanner(
				colorProvider.createTextAttribute(SQLColorProvider.SQL_MULTILINE_COMMENT))));

		addRepairer(
				ISQLPartitions.SQL_COMMENT,
				new DefaultDamagerRepairer(new SingleTokenScanner(colorProvider
						.createTextAttribute(SQLColorProvider.SQL_COMMENT))));

		addRepairer(
				ISQLPartitions.SQL_STRING,
				new DefaultDamagerRepairer(new SingleTokenScanner(colorProvider
						.createTextAttribute(SQLColorProvider.SQL_QUOTED_LITERAL))));

		addRepairer(ISQLPartitions.SQL_DOUBLE_QUOTES_IDENTIFIER, new DefaultDamagerRepairer(new SingleTokenScanner(
				colorProvider.createTextAttribute(SQLColorProvider.SQL_DELIMITED_IDENTIFIER))));
	}

	static class SingleTokenScanner extends BufferedRuleBasedScanner {
		public SingleTokenScanner(final TextAttribute attribute) {
			setDefaultReturnToken(new Token(attribute));
		}
	}
}
