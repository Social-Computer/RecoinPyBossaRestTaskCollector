package main.java.recoin.mongodb_version.rest;

import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import main.java.sociam.pybossa.rest.sendTaskRun;
import sociam.pybossa.config.Config;
import sociam.pybossa.methods.MongodbMethods;

@Path("/Task")
public class Task {

	private static final Logger logger = Logger.getLogger(Task.class);

	@GET
	@Path("{id}")
	@Produces("application/json" + ";charset=utf-8")
	public Response toJson(@PathParam("id") int id) {
		Document data = new Document();
		try {

			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			data = MongodbMethods.getTaskFromMongoDB(id);
			if (data == null) {
				data = new Document();
				String message = "no task with id " + id;
				data.put("message", "");
			}

			data.put("status", "success");
			return Response.status(200).entity(data.toJson().toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			data.put("status", "error");
			data.put("message", e);
			return Response.status(500).entity(data.toJson().toString()).build();

		}
	}

	@GET
	@Path("{id}")
	@Produces("application/json-ld" + ";charset=utf-8")
	public Response toJsonLD(@PathParam("id") int id) {
		Document data = new Document();
		JSONObject jsonLD = new JSONObject();
		try {

			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			data = MongodbMethods.getTaskFromMongoDB(id);
			jsonLD.put("@context", "http://recoin.cloudapp.net/social-computer/task/");
			jsonLD.put("@type", "task");

			data.put("status", "success");
			return Response.status(200).entity(data.toJson().toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			data.put("status", "error");
			data.put("message", e);
			return Response.status(500).entity(data.toJson().toString()).build();
		}
	}

	@GET
	@Path("{id}/Responses")
	@Produces("application/json" + ";charset=utf-8")
	public Response getTaskRuns(@PathParam("id") int id) {
		Document data = new Document();
		ArrayList<Document> taskRuns = new ArrayList<Document>();
		try {

			InputStream stream = sendTaskRun.class.getResourceAsStream("/log4j.properties");
			PropertyConfigurator.configure(stream);
			Config.reload();

			taskRuns = MongodbMethods.getTaskRunsFromMongoDB(id);
			if (taskRuns == null) {
				data = new Document();
				String message = "no responses for id " + id;
				data.put("message", "");
			}else{
				JSONArray array = new JSONArray();
				for (Document document : taskRuns) {
					array.put(document);
				}
				data.put("taskRuns", array);
			}
			data.put("status", "success");
			return Response.status(200).entity(data.toJson().toString()).build();
		} catch (Exception e) {
			logger.error("error", e);
			data.put("status", "error");
			data.put("message", e);
			return Response.status(500).entity(data.toJson().toString()).build();

		}
	}

}
