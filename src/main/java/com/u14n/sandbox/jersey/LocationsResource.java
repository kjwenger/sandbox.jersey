package com.u14n.sandbox.jersey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;

import com.u14n.sandbox.model.DAOException;
import com.u14n.sandbox.model.Location;
import com.u14n.sandbox.model.LocationDAO;

/**
 * Root resource (exposed at "locations" path)
 */
@Path("locations")
public class LocationsResource {

	/* package */ Location redHatAmphitheater;
	/* package */ Location redHatHeadquarters;
	/* package */ LocationDAO locationDAO = new LocationDAO.ConcurrentMemory();

	public LocationsResource() {
		try {
			redHatAmphitheater = new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"500 S McDowell St");
			redHatHeadquarters = new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"100 E Davie Street");
			this.locationDAO.insert(redHatAmphitheater);
			this.locationDAO.insert(redHatHeadquarters);
		} catch (DAOException e) {
			// Ignore
		}
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Location> getAll() throws DAOException {
		List<Location> list = this.locationDAO.findAll();
		/* trace */ System.out.println("LocationsResource.getAll() list.size()=" + list.size());
		return list;
	}

	@Path("locations.json")
	@Produces(MediaType.APPLICATION_JSON)
	public static class JSON extends LocationsResource {
		@GET
		public List<Location> getAllJSON() throws DAOException {
			/* trace */ System.out.println("LocationsResource$JSON.getAllJSON()");
			return getAll();
		}
	}
	@Path("locations.xml")
	public static class XML extends LocationsResource {
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public List<Location> getAllXML() throws DAOException {
			/* trace */ System.out.println("LocationsResource$XML.getAllXML()");
			return getAll();
		}
	}
}
