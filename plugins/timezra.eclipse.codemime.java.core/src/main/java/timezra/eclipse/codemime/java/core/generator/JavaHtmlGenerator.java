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
package timezra.eclipse.codemime.java.core.generator;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.JavaCommentScanner;
import org.eclipse.jdt.internal.ui.text.SingleTokenJavaScanner;
import org.eclipse.jdt.internal.ui.text.java.JavaCodeScanner;
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDocScanner;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.IJavaColorConstants;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;

import timezra.eclipse.codemime.core.generator.RepairingHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
@SuppressWarnings("restriction")
public class JavaHtmlGenerator extends RepairingHtmlGenerator {

	public JavaHtmlGenerator() {
		super(IJavaPartitions.JAVA_PARTITIONING);

		final JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();

		final IColorManager cm = tools.getColorManager();
		final IPreferenceStore ps = JavaPlugin.getDefault().getPreferenceStore();

		final JavaCodeScanner javaCodeScanner = new JavaCodeScanner(cm, ps);
		DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(javaCodeScanner);
		addRepairer(IDocument.DEFAULT_CONTENT_TYPE, repairer);

		final JavaDocScanner javaDocScanner = new JavaDocScanner(cm, ps);
		repairer = new DefaultDamagerRepairer(javaDocScanner);
		addRepairer(IJavaPartitions.JAVA_DOC, repairer);

		final JavaCommentScanner mlCommentScanner = new JavaCommentScanner(cm, ps,
				IJavaColorConstants.JAVA_MULTI_LINE_COMMENT);
		repairer = new DefaultDamagerRepairer(mlCommentScanner);
		addRepairer(IJavaPartitions.JAVA_MULTI_LINE_COMMENT, repairer);

		final JavaCommentScanner slCommentScanner = new JavaCommentScanner(cm, ps,
				IJavaColorConstants.JAVA_SINGLE_LINE_COMMENT);
		repairer = new DefaultDamagerRepairer(slCommentScanner);
		addRepairer(IJavaPartitions.JAVA_SINGLE_LINE_COMMENT, repairer);

		final SingleTokenJavaScanner stringScanner = new SingleTokenJavaScanner(cm, ps, IJavaColorConstants.JAVA_STRING);
		repairer = new DefaultDamagerRepairer(stringScanner);
		addRepairer(IJavaPartitions.JAVA_STRING, repairer);

		repairer = new DefaultDamagerRepairer(stringScanner);
		addRepairer(IJavaPartitions.JAVA_CHARACTER, repairer);
	}

}
