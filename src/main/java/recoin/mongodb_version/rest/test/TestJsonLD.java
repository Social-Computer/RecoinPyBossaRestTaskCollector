package main.java.recoin.mongodb_version.rest.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class TestJsonLD {

	public static void main(String[] args) {

		try {
			String jsonString = "{ \"_id\" : { \"$oid\" : \"570ec6cfee42411f3ef67215\" }, \"bin_id_String\" : \"5707bcc1e4b018844a5798d9\", \"facebook_lastPushAt\" : \"2016-04-14 09:36:02\", \"facebook_task_id\" : \"988144717889631\", \"facebook_task_status\" : \"pushed\", \"media_url\" : \"\", \"project_id\" : 6215747, \"publishedAt\" : \"2016-04-13 22:23:11\", \"task_id\" : 16151061, \"task_status\" : \"ready\", \"task_text\" : \"Casos de #Zika suben a 32; dos gestantes ya dieron a luz\\nhttps://t.co/kfClJa5Uzl https://t.co/HXIzXqTv1n\", \"task_type\" : \"validate\", \"twitter_lastPushAt\" : \"2016-04-14 12:51:32\", \"twitter_task_status\" : \"pushed\", \"status\" : \"success\" }";
			JSONObject json = new JSONObject(jsonString);
			System.out.println(json.toString());
			Map<String, String> context = new HashMap<String, String>();
			context.put("task", "http://recoin.cloudapp.net/social-computer/task/");
			JsonLdOptions options = new JsonLdOptions();
			
			Object compact = JsonLdProcessor.compact(json, context, options);
			System.out.println(JsonUtils.toPrettyString(compact));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonLdError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
