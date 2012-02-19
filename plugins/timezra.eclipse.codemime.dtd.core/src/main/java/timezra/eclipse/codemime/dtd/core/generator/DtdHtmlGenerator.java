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
package timezra.eclipse.codemime.dtd.core.generator;

import org.eclipse.wst.dtd.ui.StructuredTextViewerConfigurationDTD;

import timezra.eclipse.codemime.sse.core.generator.StructuredTextHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
public class DtdHtmlGenerator extends StructuredTextHtmlGenerator {

	public DtdHtmlGenerator() {
		super(new StructuredTextViewerConfigurationDTD());
	}

}
