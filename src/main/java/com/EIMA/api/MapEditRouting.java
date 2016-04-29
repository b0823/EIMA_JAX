package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIMA.Database.DBQueries;
import com.EIMA.auth.AuthUtils;
import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;
import com.EIMA.models.EIMAPolygon;
import com.EIMA.outputs.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/mapedit")
public class MapEditRouting {
	@POST
	@Path("/addassetinfo")
	public ResultBase addAssetInfo(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.mapEditor)) {
				
				EIMAAsset[] assets = DBQueries.getAssets(token);
				EIMACircle[] circ = DBQueries.getCircles(token);
				EIMAPolygon[] poly = DBQueries.getPolygons(token);
				//PARSE MAGIC GOES HERE
				
//				DBQueries.updateMapAsset(null, token);
//				DBQueries.deleteMapAsset(null, token);
//				DBQueries.addMapAsset(null, token);
				
				return new ResultBase(true);

			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);

	}
}
