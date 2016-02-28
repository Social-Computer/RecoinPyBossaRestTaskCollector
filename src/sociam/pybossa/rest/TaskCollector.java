package sociam.pybossa.rest;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;

import sociam.pybossa.config.Config;

@Path("/taskCollector")
public class TaskCollector {
	private static final Logger logger = Logger.getLogger(TaskCollector.class);

	@GET
	@Produces("application/json")
	public Response testing(@QueryParam("text") String text, @QueryParam("task_id") Integer task_id,
			@QueryParam("project_id") Integer project_id, @QueryParam("contribution_id") String contribution_id,
			@QueryParam("contributor_name") String contributor_name, @QueryParam("source") String source)
			throws JSONException {
		try (InputStream stream = TaskCollector.class.getResourceAsStream("/log4j.properties")) {
			PropertyConfigurator.configure(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Config.reload();
		JSONObject data = new JSONObject();
		JSONObject status = new JSONObject();
		if (text != null && task_id != null && project_id != null && contribution_id != null && contributor_name != null
				&& source != null) {
			logger.debug("receiving a GET request with the following data + text=" + text + " task_id=" + task_id
					+ " project_id=" + project_id + " contribution_id=" + contribution_id + " contributor_name="
					+ contributor_name + " source=" + source);
			Boolean isInserted = sociam.pybossa.TaskCollector.insertTaskRun(text, task_id, project_id, contribution_id,
					contributor_name, source);
			if (isInserted) {
				logger.info("Inserting a task run with the contribution_id=" + contribution_id);
				status.put("status", "success");
				data.put("text", text);
				data.put("task_id", task_id);
				data.put("project_id", project_id);
				data.put("contribution_id", contribution_id);
				data.put("contributor_name", contributor_name);
				data.put("source", source);
				status.put("data", data);
				String result = status.toString();
				return Response.status(200).entity(result).build();
			} else {
				logger.error("Task run with contribution_id =" + contribution_id + " was not inserted");
				status.put("status", "error");

				return Response.status(500).entity(status.toString()).build();
			}

		} else {
			logger.error("All parameters should be provided");
			status.put("status", "internal error");
			return Response.status(500).entity(status.toString()).build();
		}

	}

}
