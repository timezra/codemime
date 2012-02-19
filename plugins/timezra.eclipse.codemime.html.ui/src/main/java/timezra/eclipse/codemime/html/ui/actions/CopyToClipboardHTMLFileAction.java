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
package timezra.eclipse.codemime.html.ui.actions;

import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromSimpleResourceAction;
import timezra.eclipse.codemime.html.core.generator.HtmlHtmlGenerator;

/**
 * @author Utku Utkan
 */
public class CopyToClipboardHTMLFileAction extends CopyFromSimpleResourceAction {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.wst.sse.ui.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		return new HtmlHtmlGenerator();
	}
}
