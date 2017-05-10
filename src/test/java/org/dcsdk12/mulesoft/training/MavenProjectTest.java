package org.dcsdk12.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class MavenProjectTest extends FunctionalTestCase {

	@Override
	protected String getConfigFile() {
		// This should match the application .xml that has the flows to test in this class.
		return "maven-project.xml";
	}
	
	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");
	
	@Test
	public void mavenFlowDefaultResponse() throws Exception {
		// Execute a flow and expect a response.
		runFlowAndExpect("MavenFlow", "This is a simple Maven-configured Mule application.");
	}
	
	@Test
	public void retrieveFlightContentType() throws Exception {
		MuleEvent event = runFlow("RetrieveFlightsFlow");
		String contentType = event.getMessage().getOutboundProperty("Content-Type");
		assertEquals("application/json", contentType);
	}
}
