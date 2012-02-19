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
package timezra.eclipse.codemime.ant.ui.actions;

import timezra.eclipse.codemime.ant.core.generator.AntHtmlGenerator;
import timezra.eclipse.codemime.core.generator.HtmlGenerator;
import timezra.eclipse.codemime.core.ui.actions.CopyFromSimpleResourceAction;

/**
 * @author Utku Utkan
 */
public class CopyToClipboardAntFileAction extends CopyFromSimpleResourceAction {

	@Override
	protected String getFontPreferenceName() {
		return "org.eclipse.jdt.ui.editors.textfont";
	}

	@Override
	protected HtmlGenerator createGenerator() {
		return new AntHtmlGenerator();
	}
}
