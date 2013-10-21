/*******************************************************************************
 * Copyright (c) 2007 Hoydaa Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Utku Utkan (Hoydaa Inc.) - initial API and implementation
 *******************************************************************************/
package timezra.eclipse.codemime.properties.core.generator.html;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.propertiesfileeditor.IPropertiesFilePartitions;
import org.eclipse.jdt.internal.ui.propertiesfileeditor.PropertyValueScanner;
import org.eclipse.jdt.internal.ui.text.SingleTokenJavaScanner;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;

import timezra.eclipse.codemime.core.generator.html.RepairingHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
@SuppressWarnings("restriction")
public class PropertiesHtmlGenerator extends RepairingHtmlGenerator {

	public PropertiesHtmlGenerator(final String title) {
		super(title, IPropertiesFilePartitions.PROPERTIES_FILE_PARTITIONING);

		final JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();

		final IColorManager cm = tools.getColorManager();
		final IPreferenceStore ps = JavaPlugin.getDefault().getCombinedPreferenceStore();

		ITokenScanner scanner = new SingleTokenJavaScanner(cm, ps, PreferenceConstants.PROPERTIES_FILE_COLORING_KEY);
		DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(scanner);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, repairer);

		scanner = new PropertyValueScanner(cm, ps);
		repairer = new DefaultDamagerRepairer(scanner);
		addRepairer(IPropertiesFilePartitions.PROPERTY_VALUE, repairer);

		scanner = new SingleTokenJavaScanner(cm, ps, PreferenceConstants.PROPERTIES_FILE_COLORING_COMMENT);
		repairer = new DefaultDamagerRepairer(scanner);
		addRepairer(IPropertiesFilePartitions.COMMENT, repairer);
	}

}
