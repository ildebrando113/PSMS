package it.enaip.stage.model;

import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class userToJson {
	public static JSONObject funtionUserToJson(User user) throws JSONException {
		JSONObject jobj = new JSONObject();
		String str = "";
		int in = 0;

		in = user.getId();
		jobj.put("id", new Integer(in));

		str = user.getName();
		if (str != null && str.length() > 0) {
			jobj.put("name", str);
		}

		str = user.getSurname();
		if (str != null && str.length() > 0) {
			jobj.put("surname", str);
		}

		Date date = user.getBirthdate();
		if (date != null) {
			jobj.put("birthDate", date);
		}

		in = user.getAge();
		jobj.put("age", new Integer(in));

		str = user.getType().toString();
		if (str != null && str.length() > 0) {
			jobj.put("type", str);
		}

		Timestamp time = user.getCreationtime();
		if (time != null) {
			jobj.put("creationtime", time);
		}
		return jobj;
	}
}
