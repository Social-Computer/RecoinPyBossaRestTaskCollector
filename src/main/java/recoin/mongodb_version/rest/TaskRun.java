package main.java.recoin.mongodb_version.rest;

import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import main.java.sociam.pybossa.rest.sendTaskRun;
import sociam.pybossa.config.Config;
import sociam.pybossa.methods.MongodbMethods;

/**
 * 
 * @author user Saud Aljaloud
 * @author email sza1g10@ecs.soton.ac.uk
 *
 */
@Path("/TaskRun")
public class TaskRun {

	private static final Logger logger = Logger.getLogger(TaskRun.class);

	@GET
	@Produces("application/json" + ";charset=utf-8")
	public Response toJson(@DefaultValue("0") @QueryParam("offset") int offset,
			@DefaultValue("200") @QueryParam("limit") int limit) {

		JSONObject jsonResponse = new JSONObject();
		try {
			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			jsonResponse = MongodbMethods.getStatsFroRest(Config.taskRunCollection, null, null, offset, limit);

			return Response.status(200).entity(jsonResponse.toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse = new JSONObject();
			jsonResponse.put("status", "error");
			jsonResponse.put("message", e);
			return Response.status(500).entity(jsonResponse.toString()).build();

		}
	}

	@GET
	@Path("task")
	@QueryParam("task_id") 
	@Produces("application/json" + ";charset=utf-8")
	public Response taskRunsByTaskID(@DefaultValue("0") @QueryParam("offset") int offset,
			@DefaultValue("200") @QueryParam("limit") int limit, @QueryParam("task_id") int task_id) {

		JSONObject jsonResponse = new JSONObject();
		try {
			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			jsonResponse = MongodbMethods.getStatsFroRest(Config.taskRunCollection, "task_id", task_id, offset, limit);

			return Response.status(200).entity(jsonResponse.toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse = new JSONObject();
			jsonResponse.put("status", "error");
			jsonResponse.put("message", e);
			return Response.status(500).entity(jsonResponse.toString()).build();

		}
	}
	
	@GET
	@Path("project")
	@Produces("application/json" + ";charset=utf-8")
	public Response taskRunsByProjectID(@DefaultValue("0") @QueryParam("offset") int offset,
			@DefaultValue("200") @QueryParam("limit") int limit, @QueryParam("project_id") int project_id) {

		JSONObject jsonResponse = new JSONObject();
		try {
			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			jsonResponse = MongodbMethods.getStatsFroRest(Config.taskRunCollection, "project_id", project_id, offset, limit);

			return Response.status(200).entity(jsonResponse.toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse = new JSONObject();
			jsonResponse.put("status", "error");
			jsonResponse.put("message", e);
			return Response.status(500).entity(jsonResponse.toString()).build();

		}
	}

	@GET
	@Path("{id}")
	@Produces("application/json" + ";charset=utf-8")
	public Response taskRunsToJson(@PathParam("id") Integer id, @DefaultValue("0") @QueryParam("offset") int offset,
			@DefaultValue("200") @QueryParam("limit") int limit) {

		JSONObject jsonResponse = new JSONObject();
		try {
			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			jsonResponse = MongodbMethods.getStatsFroRest(Config.taskRunCollection, "task_run_id", id, offset, limit);

			return Response.status(200).entity(jsonResponse.toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			jsonResponse = new JSONObject();
			jsonResponse.put("status", "error");
			jsonResponse.put("message", e);
			return Response.status(500).entity(jsonResponse.toString()).build();

		}
	}
}
