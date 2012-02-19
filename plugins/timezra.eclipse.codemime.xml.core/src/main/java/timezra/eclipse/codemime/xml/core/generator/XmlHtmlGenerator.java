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
package timezra.eclipse.codemime.xml.core.generator;

import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

import timezra.eclipse.codemime.sse.core.generator.StructuredTextHtmlGenerator;

/**
 * 
 * @author Utku Utkan
 * 
 */
public class XmlHtmlGenerator extends StructuredTextHtmlGenerator {

	public XmlHtmlGenerator() {
		super(new StructuredTextViewerConfigurationXML());
	}

}
