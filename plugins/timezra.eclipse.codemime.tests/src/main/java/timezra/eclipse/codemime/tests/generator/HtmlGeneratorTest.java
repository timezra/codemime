package timezra.eclipse.codemime.tests.generator;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.Region;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import timezra.eclipse.codemime.core.generator.CodeMimeGenerator;

public abstract class HtmlGeneratorTest {

	protected static final String EOL = System.getProperty("line.separator");
	protected static final int FONT_HEIGHT = 10;
	protected static final String FONT_NAME = "monospace";
	protected static final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();
	private IProject theProject;

	@Before
	public final void setUp() throws CoreException {
		theProject = createAProject("aProject");
	}

	@After
	public final void tearDown() throws CoreException {
		theProject.delete(true, NULL_PROGRESS_MONITOR);
	}

	@Test
	public final void shouldFormatFileAsHtml() throws PartInitException {
		final IEditorPart theEditor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage(), getTheFile());

		final CodeMimeGenerator theGenerator = createTheGenerator();
		theGenerator.setFont(new FontData(FONT_NAME, FONT_HEIGHT, SWT.NORMAL));
		theGenerator.setTabWidth(4);
		final IDocumentProvider theDocumentProvider = getTheDocumentProvider(theEditor);
		final String theActualHtml = theGenerator.generate(theDocumentProvider.getDocument(theEditor.getEditorInput()),
				new Region(0, getTheFileContents().length()));

		final String theExpectedHtml = getTheExpectedHtml();

		assertThat(theActualHtml, is(theExpectedHtml));
	}

	protected IDocumentProvider getTheDocumentProvider(final IEditorPart editorPart) {
		return ((ITextEditor) editorPart).getDocumentProvider();
	}

	protected final IFile createAFile(final IContainer theFolder, final String name) throws CoreException {
		final IFile theFile = theFolder.getFile(Path.fromPortableString(name));
		theFile.create(new ByteArrayInputStream(getTheFileContents().getBytes()), true, NULL_PROGRESS_MONITOR);
		return theFile;
	}

	protected final IProject createAProject(final String name) throws CoreException {
		final IWorkspaceRoot theWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		final IProject theProject = theWorkspaceRoot.getProject(name);
		theProject.create(NULL_PROGRESS_MONITOR);
		theProject.open(NULL_PROGRESS_MONITOR);
		return theProject;
	}

	protected final IProject getTheProject() {
		return theProject;
	}

	protected abstract IFile getTheFile();

	protected abstract CodeMimeGenerator createTheGenerator();

	protected abstract String getTheExpectedHtml();

	protected abstract String getTheFileContents();

	protected final String getTheTestResourceContents(final String fileName) {
		try {
			return readFileToString(new File(new File("src/test/resources"), fileName)).replaceAll("\\r\\n|\\r|\\n",
					EOL);
		} catch (final IOException e) {
			throw new IllegalArgumentException("Unable to open the file " + fileName, e);
		}
	}
}