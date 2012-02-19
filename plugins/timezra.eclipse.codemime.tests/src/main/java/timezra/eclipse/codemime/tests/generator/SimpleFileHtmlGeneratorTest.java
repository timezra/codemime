package timezra.eclipse.codemime.tests.generator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;

public abstract class SimpleFileHtmlGeneratorTest extends HtmlGeneratorTest {

	private IFile theFile;

	@Before
	public final void createTheFile() throws CoreException {
		theFile = createAFile(getTheProject(), getTheFileName());
	}

	@Override
	protected final IFile getTheFile() {
		return theFile;
	}

	protected abstract String getTheFileName();
}
