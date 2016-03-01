package main.java.sociam.pybossa.rest;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import sociam.pybossa.TaskPerformer;
import sociam.pybossa.config.Config;

@Path("/getTasks")
public class GetTasks {
	private static final Logger logger = Logger.getLogger(sendTaskRun.class);

	@GET
	@Produces("application/json" + ";charset=utf-8")
	public Response getTasks(@QueryParam("offset") Integer offset) {
		JSONObject result = new JSONObject();

		try {

			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();
			if (offset == null) {
				offset = 0;
			}
			result = TaskPerformer.getTasks(offset);
			result.put("status", "success");
			return Response.status(200).entity(result.toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			result.put("status", "error");
			result.put("message", e);
			return Response.status(500).entity(result.toString()).build();
		}
	}
}
