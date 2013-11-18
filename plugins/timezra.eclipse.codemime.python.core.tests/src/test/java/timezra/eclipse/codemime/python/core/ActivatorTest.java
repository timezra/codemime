package timezra.eclipse.codemime.python.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import timezra.eclipse.codemime.python.core.Activator;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
