package com.EIMA.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.PathParam;
import java.util.TimeZone;

import com.EIMA.models.Time;
import com.EIMA.models.Validate;

@Path("/EIMA")
@Produces(MediaType.APPLICATION_JSON)
public class EIMAService {

	@GET
	public Time get() {
		return new Time();
	}

	@GET
	@Path("/{timezone}")
	public Time get(@PathParam("timezone") String timezone) {
		return new Time(TimeZone.getTimeZone(timezone.toUpperCase()));
	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/validate")
	public Validate putExample(String json) throws JSONException {				
		return new Validate(json);
	}
}
