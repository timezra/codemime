package timezra.eclipse.codemime.qvt.ui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
