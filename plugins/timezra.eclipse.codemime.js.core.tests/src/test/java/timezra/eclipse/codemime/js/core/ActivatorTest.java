package timezra.eclipse.codemime.js.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import timezra.eclipse.codemime.js.core.Activator;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
