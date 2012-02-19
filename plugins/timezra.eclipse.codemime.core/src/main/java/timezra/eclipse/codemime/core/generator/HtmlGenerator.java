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
package timezra.eclipse.codemime.core.generator;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.graphics.FontData;

/**
 * 
 * @author Utku Utkan
 * 
 */
public interface HtmlGenerator {

	String generate(final IDocument document, final IRegion region);

	void setFont(final FontData font);

	void setWhitespaceAllowed(final boolean b);

	void setTabWidth(final int width);

}
