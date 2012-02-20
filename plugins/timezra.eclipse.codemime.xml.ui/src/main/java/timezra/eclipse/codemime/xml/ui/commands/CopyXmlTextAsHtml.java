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
package timezra.eclipse.codemime.xml.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.commands.CopyTextAsHtml;
import timezra.eclipse.codemime.xml.core.generator.XmlHtmlGenerator;

/**
 * @author Utku Utkan
 */
public class CopyXmlTextAsHtml extends CopyTextAsHtml {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.sse.ui.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator(final ExecutionEvent event) {
		return new XmlHtmlGenerator();
	}
}
