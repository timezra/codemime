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
package timezra.eclipse.codemime.java.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyResourceAsHtml;
import timezra.eclipse.codemime.java.core.generator.JavaHtmlGenerator;

/**
 * @author Utku Utkan
 */
public class CopyJavaFileAsHtml extends CopyResourceAsHtml<ICompilationUnit> {

	@Override
	protected IPath getPath(final ExecutionEvent event) {
		return getSelection(event).getPath();
	}

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator(final ExecutionEvent event) {
		return new JavaHtmlGenerator();
	}
}
