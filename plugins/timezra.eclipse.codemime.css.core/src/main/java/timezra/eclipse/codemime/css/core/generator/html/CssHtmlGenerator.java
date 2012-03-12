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
package timezra.eclipse.codemime.css.core.generator.html;

import org.eclipse.wst.css.ui.StructuredTextViewerConfigurationCSS;

import timezra.eclipse.codemime.sse.core.generator.html.StructuredTextHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
public class CssHtmlGenerator extends StructuredTextHtmlGenerator {

	public CssHtmlGenerator() {
		super(new StructuredTextViewerConfigurationCSS());
	}

}
