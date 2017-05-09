package org.dcsdk12.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;

public class MavenProjectTest extends FunctionalTestCase {

	@Override
	protected String getConfigFile() {
		// This should match the application .xml that has the flows to test in this class.
		return "maven-project.xml";
	}

	@Test
	public void mavenFlowDefaultResponse() throws Exception {
		// Execute a flow and expect a response.
		runFlowAndExpect("MavenFlow", "This is a simple Maven-configured Mule application.");
	}
	
	@Test
	public void retrieveFlightsAddsAppropriateHeader() throws Exception {
		MuleEvent event = runFlow("RetrieveFlightsFlow");
		String contentType = event.getMessage().getOutboundProperty("Content-Type");
		assertEquals("application/json", contentType);
	}
}
