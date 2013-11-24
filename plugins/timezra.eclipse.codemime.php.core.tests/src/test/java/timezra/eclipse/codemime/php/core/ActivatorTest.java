package timezra.eclipse.codemime.php.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import timezra.eclipse.codemime.php.core.Activator;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
