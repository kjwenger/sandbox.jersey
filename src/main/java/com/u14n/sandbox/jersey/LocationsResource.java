package com.u14n.sandbox.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.u14n.sandbox.model.DAOException;
import com.u14n.sandbox.model.Location;
import com.u14n.sandbox.model.LocationDAO;

/**
 * Root resource (exposed at "locations" path)
 */
@Path("locations")
public class LocationsResource {

	private LocationDAO locationDAO = new LocationDAO.ConcurrentMemory();

	public LocationsResource() {
		try {
			this.locationDAO.insert(new Location(
				"USA",
				"NC",
				"27601",
				"Raleigh",
				"500 S McDowell St"));
			this.locationDAO.insert(new Location(
				"USA",
				"NC",
				"27601",
				"Raleigh",
				"100 E Davie Street"));
		} catch (DAOException e) {
			// Ignore
		}
	}

	@GET
	@Produces({
		MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_XML
	})
	public List<Location> getAll() throws DAOException {
		List<Location> list = this.locationDAO.findAll();
		/* trace */ System.out.println("LocationsResource.findAll() list.size()=" + list.size());
		return list;
	}
}
