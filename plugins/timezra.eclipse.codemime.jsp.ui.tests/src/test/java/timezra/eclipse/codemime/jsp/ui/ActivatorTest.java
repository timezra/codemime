package timezra.eclipse.codemime.jsp.ui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ActivatorTest {

	@Test
	public void thePluginShouldBeActivated() {
		assertNotNull(Activator.getDefault());
	}
}
