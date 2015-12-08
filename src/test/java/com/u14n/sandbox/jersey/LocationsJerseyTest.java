package com.u14n.sandbox.jersey;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.glassfish.grizzly.http.server.HttpServer;

public class LocationsJerseyTest extends JerseyTest {

	private static HttpServer server;

	@BeforeClass
	public static void SetUp() throws Exception {
		// start the server
		server = Main.startServer();
	}

	@AfterClass
	public static void TearDown() throws Exception {
		server.shutdownNow();
	}

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

	@Ignore @Test
	public void testLocations9223372035280100646GetSuccess()
			throws JSONException, URISyntaxException {
		JSONObject json = client()
				.resource("http://localhost:8080/")
				.path("/restapi/locations/9223372035280100646.json")
				.get(JSONObject.class);
		assertEquals("USA"          , json.get("countryCode"));
		assertEquals("NC"           , json.get("regionCode" ));
		assertEquals("27601"        , json.get("postalCode" ));
		assertEquals("Raleigh"      , json.get("city"       ));
		assertEquals("S McDowell St", json.get("street"     ));
		assertEquals("500"          , json.get("number"     ));
	}

	@Ignore @Test(expected = UniformInterfaceException.class)
	public void testLocationsNotFound() {
		JSONObject json = client()
				.resource("http://localhost:8080/")
				.path("/restapi/locations/666.json")
				.get(JSONObject.class);
	}
}