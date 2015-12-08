package com.u14n.sandbox.jersey.junit;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.u14n.sandbox.jersey.Main;

public class LocationsResourceTest {

	private static HttpServer server;
	private static WebTarget target;

	@BeforeClass
	public static void SetUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(Main.BASE_URI);
	}

	@AfterClass
	public static void TearDown() throws Exception {
		server.shutdownNow();
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Ignore @Test
	public void testGetIt() {
		String responseMsg = target.path("locations").request().get(String.class);
		assertNotNull(responseMsg);
	}
}
