package timezra.eclipse.codemime.scala.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import timezra.eclipse.codemime.scala.core.Activator;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
