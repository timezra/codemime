package timezra.eclipse.codemime.java.tests.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.junit.Before;

import timezra.eclipse.codemime.tests.generator.HtmlGeneratorTest;

public abstract class JavaFileHtmlGeneratorTest extends HtmlGeneratorTest {

	private static final String SOURCE_ROOT = "src/test";
	protected static final String PACKAGE_NAME = "codemime.test";
	protected static final String CLASS_NAME = "HelloWorld";

	private IFile theJavaFile;

	@Before
	public void createTheJavaFile() throws CoreException {
		final String theSourceFolderName = SOURCE_ROOT + '/' + getTheType();
		configureTheProject(theSourceFolderName);
		final IFolder theSourceFolder = createASourceFolder(theSourceFolderName);
		final IFolder thePackage = createAPackage(theSourceFolder, PACKAGE_NAME.replaceAll("\\.", "/"));
		theJavaFile = createAFile(thePackage, CLASS_NAME + '.' + getTheType());
	}

	protected abstract String getTheType();

	@Override
	protected final IFile getTheFile() {
		return theJavaFile;
	}

	// based on http://www.stateofflow.com/journal/66/creating-java-projects-programmatically
	@SuppressWarnings("restriction")
	protected void configureTheProject(final String sourceFolder) throws CoreException {
		final IProject theProject = getTheProject();
		org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathsBlock.addJavaNature(theProject,
				new SubProgressMonitor(NULL_PROGRESS_MONITOR, 1));
		final IJavaProject theJavaProject = JavaCore.create(theProject);

		final List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		for (final IClasspathEntry entry : theJavaProject.getRawClasspath()) {
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				((org.eclipse.jdt.internal.core.ClasspathEntry) entry).path = Path.fromPortableString(sourceFolder);
			}
			entries.add(entry);
		}
		entries.add(JavaRuntime.getDefaultJREContainerEntry());
		theJavaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), NULL_PROGRESS_MONITOR);
	}

	private IFolder createASourceFolder(final String name) throws CoreException {
		final IFolder aJavaSourceFolder = getTheProject().getFolder(Path.fromPortableString(name));
		create(aJavaSourceFolder);
		return aJavaSourceFolder;
	}

	private IFolder createAPackage(final IFolder aJavaSourceFolder, final String name) throws CoreException {
		final IFolder aJavaPackage = aJavaSourceFolder.getFolder(Path.fromPortableString(name));
		create(aJavaPackage);
		return aJavaPackage;
	}

	private void create(final IFolder folder) throws CoreException {
		final IContainer parent = folder.getParent();
		if (parent.getType() == IResource.FOLDER && !parent.exists()) {
			create((IFolder) parent);
		}
		folder.create(true, true, NULL_PROGRESS_MONITOR);
	}
}
