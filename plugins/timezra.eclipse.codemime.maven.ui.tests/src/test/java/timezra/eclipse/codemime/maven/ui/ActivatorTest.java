package timezra.eclipse.codemime.maven.ui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import timezra.eclipse.codemime.maven.ui.Activator;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
