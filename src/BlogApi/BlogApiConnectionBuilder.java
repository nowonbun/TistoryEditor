package BlogApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class BlogApiConnectionBuilder {
	private static BlogApiConnectionBuilder singleton = null;

	public static BlogApiConnectionBuilder instance() {
		if (singleton == null) {
			singleton = new BlogApiConnectionBuilder();
		}
		return singleton;
	}

	public BlogApiConnection build(String url, Map<String, String> parameter) {

		String param = null;
		for (String key : parameter.keySet()) {
			if (param == null) {
				param = "";
			} else {
				param += "&";
			}
			param += key + "=" + parameter.get(key);
		}

		try {
			URL obj = new URL(url);
			return new BlogApiConnection(obj.openConnection(), param);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
